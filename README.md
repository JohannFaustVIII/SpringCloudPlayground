# SpringCloudPlayground
My playground to learn Spring Cloud

- First service - different ids to see that different instances of the same service were called.
- Second service - reading configuration from configuration server via a bus
- Third service - Spring Boot Actuator usage example
- Fourth service - same as second service, reading configuration from configuration server via a bus, made to notice propagation of config between services via a bus
- Fifth service - "client" in usage of circuit breaker, reads data from sixth service
- Sixth service - "server" in usage of circuit breaker, provides data

[My Notes](NOTES.md)