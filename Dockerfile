#only java to run our jar
FROM openjdk:21

WORKDIR /app
COPY target/authservice-0.0.1.jar /app/

EXPOSE 8080
ENTRYPOINT ["java","-jar","javabot-0.0.1.jar"]
