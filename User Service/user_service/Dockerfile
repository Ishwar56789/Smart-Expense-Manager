FROM eclipse-temurin:17-jre-alpine:latest

ADD target/user-service.jar user-service.jar

ENTRYPOINT [ "java" , "-jar" , "user-service.jar" ]