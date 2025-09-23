FROM eclipse-temurin:17-jre-alpine:latest

ADD target/service-registry.jar service-registry.jar

ENTRYPOINT [ "java" , "-jar" , "service-registry.jar" ]