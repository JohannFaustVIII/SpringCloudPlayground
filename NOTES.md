# Notes

Here is a document where I explain some things (mainly to myself (probably)).

# Content

 - [Why to use @RefreshScope during update of configuration?](#why-to-use-refreshscope-during-update-of-configuration)
 - [How does Circuit Breaker pattern work?](#how-does-circuit-breaker-pattern-work)
 - [How to solve problem with Keycloak hostname in docker?](#how-to-solve-problem-with-keycloak-hostname-in-docker)
 - [What is MIME Sniffing?](#what-is-mime-sniffing)
 - [What is a difference between @Cacheable, @CachePut and @CacheEvict?](#what-is-a-difference-between-cacheable-cacheput-and-cacheevict)

## Why to use @RefreshScope during update of configuration?

Let's look at an example: a simple REST Controller, which reads a property, can look like this:

```java
@RestController
public class SecondServiceApi {

    @Value("${example.property}")
    private String exampleProperty;

    @GetMapping("/")
    public String getHello() {
        return "Hello World!\nExample property is: " + exampleProperty;
    }
}
```

The problem is that the bean created from above code, by default, will have a singleton scope, so will be created only once. 
The value of property will be injected at the creation, and only then. That causes, that each change or refresh of property's value
won't affect the bean, because the value was already set.

The main solution for that is to add @RefreshScope to class declaration. That will cause that the made bean will be updated with
each update of the injected property's value. Simple. (Tested only with "/actuator/busrefresh" endpoint, but it worked.)

Other solution may be changing scope of bean, for example, to @RequestScope. So a new bean would be created with each request, and
that will cause injection of actual property's value each time. Doesn't look like an "elegant" way to solve the problem, because
it deals with a problem of updating a property via a Spring Cloud Bus using Configuration Server, so should be solved using
a way provided by it, and not by a way from a "lower" level, like changing bean scope. But it works (Tested only with "/actuator/busrefresh" 
endpoint), but maybe could be helpful for other situation when a property is changed and there is no provided solution to update 
a bean.

## How does Circuit Breaker pattern work?

In microservice architecture, communication between microservices can describe two types of services: consumers and producers. 
Consumer sends a request to a producer to gain some data. As microservices are meant to be decoupled as independent, there can be
a situation when producer service is down, and consumer doesn't know about that fact until it makes a request to producer. In such
situation, it will wait until timeout or other exception occurs. Still, it requires time to spend. To spend less time, the circuit
breaker pattern is used.

The circuit breaker role in communication is to observe communication between microservices. Two main things to observe are: if 
communication is successful, and how long it takes to communicate. Based on that, the circuit breaker can interrupt the communication,
and early failure will occur. It is used to prevent non-stop trying to reach a down service.

Work of circuit breaker is described by three states:
- CLOSED - It DOESN'T mean that communication is closed, it MEANS that circuit is closed and communication is done without any problems
or they are at acceptable level (below threshold). If number of failures or timeouts is above defined threshold, state is changed to OPEN.
How the threshold is defined? Depends on implementation, later about that.
- OPEN - It means that circuit is open, so the flow is INTERRUPTED and any request will fail early. In this state, any request isn't really
done but failure case is handled, so circuit breaker returns that it is open and executes a provided function for this case.
- HALF_OPEN - So... There is a change from CLOSED to OPEN after reaching a threshold, but how does OPEN changes to CLOSED? That's when 
HALF_OPEN state comes on a stage. After some time in OPEN state, Circuit Breaker decided to check if the producer service is up again.
For this case, it changes its state to HALF_OPEN and lets a next request to be executed on the real service. If that request fails, it comes
back to OPEN state again, if it is a success, it can progress to CLOSED state.

What properties can be set? Depends on implementation. Here is a few examples from Resilience4J (mainly, what I used for now and I had to understand (I hope I did)).

For `CircuitBreakerConfig`:
- `minimumNumberOfCalls` - defines how many calls have to be made before computing a threshold. If there are less calls than set, threshold
isn't computed and circuit breaker is staying in CLOSED state, even if these calls are all failures.
- `slidingWindowSize` - defines how many last calls can be used to compute a threshold. If there are less calls than set, all of them are used,'
for example: if only 4 calls were made, but window size is 10, only 4 calls are used to compute value of threshold.
- `failureRateThreshold` - define a percentage of failed calls to change state from CLOSED to OPEN. Failure rate has to be equal or greater to change
to OPEN state.
- `waitDurationInOpenState` - defines how long a circuit breaker has to be in OPEN state before changing to HALF_OPEN state

For `TimeLimiterConfig`:
- timeoutDuration - defines how long a circuit breaker has to wait for a response before marking the attempt as timeout. 

## How to solve problem with Keycloak hostname in Docker?

First, keycloak can have injected hostname via `--hostname` option (and the whole family of options behind it). That way
Keycloak will know what issuer it has to use, but it will start to redirect rest of calls to provided hostname. That causes
that hostname resolved by Docker's DNS can be not used by calling endpoint via browser, because it is going to redirect to
url with Docker's name, which can't be resolved by a browser. To solve that, hostname can be added to `/etc/hosts` (for Linux) 
and that will tell browser to redirect Docker's hostname to localhost, and then via a proper port.

## What is MIME Sniffing?

Most, if not all, browsers have it. If a server returns a response without set `Content-Type` header, a browser don't know
what type is in the response's body and how to handle it. That was causing crashing and errors on the browser side. To
prevent that, browser got implemented MIME Sniffing, which lets to analyze a response's body to determine a proper MIME
type and to handle it without any problem. Makes life easier. And RISKIER. Why? Because if unchecked resource is put on
a server and is requested by a browser, it can be taken and handled without any problem. And that resource can be a harmful
script. To prevent a situation like that, and to tell a browser that "It's okay to fail with that" is to set 
`X-Content-Type-Options` response's header to `nosniff` value. That will tell a browser to don't try MIME sniffing to handle
a resource, but just fail, which is safer.

## What is a difference between @Cacheable, @CachePut and @CacheEvict?

Worth mentioning: below annotation works as a proxy, so will be respected if a method is called outside a class, but not
from inside (and to be more precise, it wraps object into a proxy layer, which is skipped when call is made from inside).

- `@Cacheable` - the annotation tells "the result of this method can be cached", so if a result of the method is not cached,
then the method is executed. Otherwise, the result is read from a cache and method is not executed. 
- `@CachePut` - the annotation tells "this method updates resource and result has to be cached", so the method is always 
executed (no matter if result is cached or not) and caches its result.
- `@CacheEvict` - the annotation tells "this method remove from a resource and has to remove from cache", so the method is
always executed and cached objects (if exist) are removed (based on a key, cache name etc.).