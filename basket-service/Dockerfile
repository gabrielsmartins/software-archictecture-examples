FROM openjdk:11-jdk
VOLUME /app
COPY build/libs/*.jar .
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "-Dspring.active.profile=local", "basket-service.jar"]