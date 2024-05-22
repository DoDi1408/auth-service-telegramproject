#only java to run our jar
FROM openjdk:21

WORKDIR /app
COPY target/authservice-0.0.1.jar /app/

EXPOSE 8081
ENTRYPOINT ["java","-jar","authservice-0.0.1.jar"]
