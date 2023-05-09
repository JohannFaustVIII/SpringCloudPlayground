# Notes

Here is a document where I explain some things (mainly to myself (probably)).

# Content

 - [Why to use @RefreshScope during update of configuration?](#why-to-use-refreshscope-during-update-of-configuration)

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