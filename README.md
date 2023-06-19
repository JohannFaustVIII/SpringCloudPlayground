# SpringCloudPlayground
My playground to learn Spring Cloud

- First service - different ids to see that different instances of the same service were called.
- Second service - reading configuration from configuration server via a bus
- Third service - Spring Boot Actuator usage example
- Fourth service - same as second service, reading configuration from configuration server via a bus, made to notice propagation of config between services via a bus
- Fifth service - "client" in usage of circuit breaker, reads data from sixth service
- Sixth service - "server" in usage of circuit breaker, provides data
- Seventh service - service requiring authorization to get access to resources, authorization redirect and passing the token is provided by the gateway.
- Eighth service - simple service returning a list of integers. Made to work with Ninth service + sending traces to Jaeger via Micrometer.
- Ninth service - service using OpenFeign to communicate with eighth service + has configured circuit breaker if communication is not possible + integration of OpenFeign with Micrometer to send traces to Jaeger.
- Tenth service - service sending traces to Jaeger via Micrometer.
- Eleventh service - service implementing producing, streaming and consuming custom objects via Kafka stream.
- Twelfth service - service using Spring Cache.

[My Notes](NOTES.md)
