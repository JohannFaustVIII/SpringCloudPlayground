FROM gradle:jdk17-alpine as builder
WORKDIR /usr/app
COPY build.gradle ./
COPY src src
RUN gradle bootJar

FROM openjdk:17-alpine
WORKDIR /usr/app
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod" , "app.jar"]
COPY --from=builder /usr/app/build/libs/app-*.jar app.jar