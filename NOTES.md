# Notes

Here is a document where I explain some things (mainly to myself (probably)).

# Content

 - [Why to use @RefreshScope during update of configuration?](#why-to-use-refreshscope-during-update-of-configuration)
 - [How does Circuit Breaker pattern work?](#how-does-circuit-breaker-pattern-work)
 - [How to solve problem with Keycloak hostname in docker?](#how-to-solve-problem-with-keycloak-hostname-in-docker)
 - [What is MIME Sniffing?](#what-is-mime-sniffing)
 - [What is a difference between @Cacheable, @CachePut and @CacheEvict?](#what-is-a-difference-between-cacheable-cacheput-and-cacheevict)
 - [How multiple cache names work for the same method?](#how-multiple-cache-names-work-for-the-same-method)
 - [How to define multiple caching annotations for a method?](#how-to-define-multiple-caching-annotations-for-a-method)
 - [What is Liquibase?](#what-is-liquibase)
 - [Why use mappedBy in bidirectional relationship?](#why-use-mappedby-in-bidirectional-relationship)
 - [How to use @JoinColumn annotation?](#how-to-use-joincolumn-annotation)
 - [When to use @ControllerAdvice?](#when-to-use-controlleradvice)
 - [What is order of using ExceptionHandler?](#what-is-order-of-using-exceptionhandler)
 - [What is order of advices in AOP?](#what-is-order-of-advices-in-aop)
 - [How to capture arguments in AOP?](#how-to-capture-arguments-in-aop)
 - [How to capture returned value in AOP?](#how-to-capture-returned-value-in-aop)
 - [How to capture thrown exception in AOP?](#how-to-capture-thrown-exception-in-aop)
 - [Does overriding work with AOP?](#does-overriding-work-with-aop)
 - [What is a discovery server?](#what-is-a-discovery-server)
 - [What is a configuration server?](#what-is-a-configuration-server)
 - [What is OpenFeign?](#what-is-openfeign)
 - [What is Spring Boot Actuator?](#what-is-spring-boot-actuator)
 - [What is Kafka](#what-is-kafka)
 - [What is a difference between OAuth and OpenID?](#what-is-a-difference-between-oauth-and-openid)
 - [What is Jaeger?](#what-is-jaeger)
 - [What is Micrometer?](#what-is-micrometer)
 - [What is Kubernetes?](#what-is-kubernetes)
 - [What is a pod in Kubernetes?](#what-is-a-pod-in-kubernetes)
 - [What is a node in Kubernetes?](#what-is-a-node-in-kubernetes)
 - [What is control plane?](#what-is-control-plane)
 - [What is a cluster in Kubernetes?](#what-is-a-cluster-in-kubernetes)
 - [What is Minikube?](#what-is-minikube)
 - [What is Kubectl?](#what-is-kubectl)

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

## How multiple cache names work for the same method?

Based on local testing.

For `@Cacheable`, it looks like it checks each cache name for a value. If the value exists, it is returned, otherwise,
check next cache name. If none of cache names contain a result, the annotated method is executed, and the result is put 
in all declared cache names.

Sooo... If there are two cache names, and method is executed - the result is put in all of them. If the method is called
again, the result will be returned from the first cache name. If the second cache name is updated, but the value is in 
the first one, the first cache name's value is going to be returned. Only evict of the first cache name will cause to read 
value from the second cache name.

If cache names are declared in `CacheConfig` for a class, and in `Cacheable`/`CachePut`/`CacheEvict` for a method, then
only cache names declared directly for the method (in `Cacheable`/`CachePut`/`CacheEvict` ) are used (cache names in
`CacheConfig` are ignored, unless they are declared in `Cacheable`/`CachePut`/`CacheEvict` annotation for the method).

## How to define multiple caching annotations for a method?

Multiple `@Cacheable` can be desired for a situation like (it is just an example), when for one of cache names we want
 to put always result, and to another only when given condition is met. In such case, it can't be defined in a single 
annotation, so two has to be used. The problem is that the same annotation can't be used multiple times for a method. 
Solution is usage of `@Caching` annotation, which lets to use multiple `@Cacheable` (or other annotations) to be declared 
inside. An example of usage can be used online.

```java
@Caching(evict = {
  @Cacheable(“address”), 
  @Cacheable(value=“employees”, condition=”#result.length > 100”)
})
public Employee getEmployee(String name) {
  // some code
}
```

## What is Liquibase?

Liquibase is like a GIT, but for a database - it is a tool to control version of a database and monitor what was added/updated/removed.
By using it, we can observe what, when and by whom was changed in a database. Why is it good? Because it takes responsibility 
from JPA to manipulate database (and it should never be done! The only proper value of `spring.jpa.hibernate.ddlAuto` is 
`validate` to check if fields and tables matches etc.). And it helps to make proper migration of a database on production.
And to add example data to a database for development, but without pushing that to production, because of using different
profiles. And can set proper rollback rule if migration fails.

## Why use mappedBy in bidirectional relationship?

Assume that we have two entities as below.

```java
@Entity
public class Author {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
 private List<Post> posts;
}


@Entity
public class Post {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "author_id")
 private Author author;

}
```

What do we expect in a database? That `Post` table will have a foreign key in column `author_id` referencing to `Author`
table. What about `Author` table? It should only have id. No information about author's posts there. So what way should 
JPA get that data? Here the problem begins. It doesn't have a way, and it needs help. And here mappedBy comes. It is used
to tell what field in other class should be checked to look for reference. Proper terminology in owning and non-owning side
of relation. `Post` is owning side of relation, because it owns data about what author made it. `Author` is non-owning side
because it doesn't contain any data, in its table, about posts. `mappedBy` is used to determine that relation and declare
which side is owning, and which is non-owning. And what problem does it solve? For example, problem with adding new posts.
It is enough to just add new Post object with author inside, WITHOUT updating Author object's list about its posts, because
that side will be updated, and reading Author object will be done without missing data about posts.

## How to use @JoinColumn annotation?

`@JoinColumn` annotation is used to define column in a table which will define column name to contain foreign key to another
table. However, it also defines an owner of relationship in bidirectional relationship. So, if we have two entities, one 
using `@OneToMany` annotation, and another using `@ManyToOne`, expected is to use `@JoinColumn` on `@ManyToOne` annotated
field, and `mappedBy` in `@OneToMany` annotation.

And now some theory (because I didn't check it)... If `@ManyToOne` is expected to be the owner of relationship, it can't define
`mappedBy` and can have defined `@JoinColumn`. Other side, with `@OneToMany` is expected to set `insertable` and `updatable`
properties to false. That way of declaring relation is supposed to produce more `UPDATE` statements.

## When to use @ControllerAdvice?

`@ControllerAdvice` is used to define a class handling exceptions from controllers. In simple words: to define global
exception handler instead of defining exception handling in each controller separately.

## What is order of using ExceptionHandler?

Order is:
1. Exception handler defined in controller in which exception happened.
2. If above doesn't apply, then exception handler in controller advice.
3. If above doesn't apply, then handling is done by response status defined for the exception.
4. If above doesn't apply, then default exception handler is used.

## What is order of advices in AOP?

Order is:
1. `Around` - the most powerful, but requires more handling than other advices. Interrupts the execution of a method, 
and lets to add behavior - before and after the method. Because of that - requires to manually run the interrupted 
method and to return its value.
2. `Before` - executed before a method.
3. `AfterReturning` - executed after a method, but only if method was executed without throwing an exception (because of that, it doesn't combine with AfterThrowing).
4. `AfterThrowing` - executed after a method, but if method threw an exception (because of that, it doesn't combine with AfterReturning).
5. `After` - can be named after finally, because it is executed after a method, doesn't matter if method threw an exception or not. 
It combines with AfterRetuning and AfterThrowing, and is executed after both of them.

## How to capture arguments in AOP?

There are two ways: by defining `args` or by `getArgs()` method.

Defining `args` can be seen below:

``` java

    @Pointcut("execution(* joh.faust.service.SimpleService.*(..)) && args(a, b)")
    public void pointcut2(int a, String[] b) {

    }
    
    @Before("pointcut2(a, b)")
    public void beforeGetArgs(int a, String[] b) {
       // code...
    }
```

Above, the pointcut is defined by `execution` and `args`. Defined `args` define how many arguments are expected to go into
the method, and in `pointcut2` method declaration, types of these arguments are defined, because they have to match to 
types of the pointcut method. Names in `args` and method declaration have to match too. By defining pointcut that way, 
arguments can be read later in advice method.

By using `getArgs()` method, a situation like below can be understood.

```java
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // code...
        Object[] args = joinPoint.getArgs();
        // code...
    }
```

In above situation, values of arguments can be read by using `getArgs()` method of `JoinPoint` interface. It returns an
array with values passed into an interrupted method.

## How to capture returned value in AOP?

There are two situations: `@AfterReturning` and `@Around` advice.

For `@AfterReturning` advice, the returned value can be read by declaring `returning` in the annotation, as below.

```java
    @AfterReturning(pointcut = "pointcut()", returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        // code...
    }
```

The returned value is passed as argument with the same name as declared in `returning`.

In `@Around` case, the value can be read when executing the wrapped method, as below.

```java
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // code...
        try {
            // code...
            Object result = joinPoint.proceed();
            // code...
        } finally {
            // code...
        }
        // code...
    }
```

As shown above, the value returned by wrapped method is a result of `proceed()` method.

## How to capture thrown exception in AOP?

Mainly it focuses on `@AfterThrowing` advice. The way to do it is as below:

```java
    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        // code...
    }
```

By using `throwing` in `@AfterThrowing` annotation, the exception is passed into the method via an argument with the same name
as declared for `throwing`.

The another way is in `@Around` advice, because as it wraps a call, it can catch the exception as below.

```java
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // code...
        try {
            // code...
            joinPoint.proceed();
            // code...
        } catch (Exception ex) {
            // the exception is caught here and can be read
        }
        // code...
    }
```

## Does overriding work with AOP?

If used to declare many pointcuts via the same method name, like below:
```java
    @Pointcut("execution(* joh.faust.service.SimpleService.*(..))")
    public void pointcut() {

    }

    @Pointcut("execution(* joh.faust.service.SimpleService.*()) && args(a, b)")
    public void pointcut(int a, String[] b) {

    }
```

Then it can cause errors.


## What is a discovery server?

Discovery server is a server holding information about running services in an environment. The information is got by 
registration of a service in the server, so it could be read if needed. Multiple instances of a service can be registered.

Example implementation of discovery server: Eureka.

## What is a configuration server?

Configuration server is a server holding configuration which can be read by other services. The configuration is defined
as a resource to which configuration server can get access, for example: as git repository. Because of that, the configuration
server has to be started before any service using it.

## What is OpenFeign?

OpenFeign is a library to send request to other services. Defined by setting url or service name if combined with 
discovery server. It enables to use similar definitions as defining endpoints to define methods connected to other 
services.

## What is Spring Boot Actuator?

Spring Boot Actuator is a module enabling application monitoring. It lets to expose endpoints with built-in statistics
like app's health, info, metrics etc., and add user-defined metrics and endpoints.

## What is Kafka?

Kafka is event streaming platform, so it is a pipeline to transport events, emitted by publishers, and sent to registered
subscribers. Used in microservice architecture, as it is an external service.

## What is a difference between OAuth and OpenID?

OAuth is used for authorization, when OpenID is used to for authentication. So to simplify, use OpenID when you want to
confirm your identity, and use OAuth when you want to confirm that you are permitted to access a resource.

## What is Jaeger?

Jaeger is a tool to for distributed tracing. It is useful in microservice environment as it can trace communication
between services, showing interactions between them and required trace to fulfill a single function by environment.

## What is Micrometer?

It is a library used as a facade between the application and distributed tracing tool.

## What is Kubernetes?

Kubernetes is a tool to manage containerized workloads and services.

## What is a pod in Kubernetes?

Pod is a unit running a single container inside (but can more if required), being a facade between Kubernetes environment
and the container. That solution decouples the rest of environment from implementation of container (like, we don't depend
on Docker, but can run other implementations of containerization).

## What is a node in Kubernetes?

Node is an environment for pods to run. Node includes three components inside:
- container runtime - to run containers inside a pod;
- kubelet - makes sure that containers are running on a pod;
- kube-proxy - network proxy.

## What is control plane?

Control plane is like a master node, used to manage creation of pods by choosing on which node it should run.
Need to learn more to write more.

## What is a cluster in Kubernetes?

Cluster is a group of nodes and control planes working together. Need to learn more to write more.

## What is Minikube?

Minikube is a tool to create a single node K8s cluster with master and worker processes inside. It has Docker pre-intalled.
Its main use is testing on local machine.

## What is kubectl?

Kubectl is command line tool to interact with K8s cluster, with a cloud cluster but with minikube cluster also.