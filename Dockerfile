FROM openjdk:17
WORKDIR /app
#ADD ./target/ms-scheduling-0.0.1-SNAPSHOT.jar ms-scheduling.jar
COPY ./target/*.jar /app/ms-scheduling.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.config.additional-location=classpath:application-docker.yml", "-jar", "/app/ms-scheduling.jar"]
