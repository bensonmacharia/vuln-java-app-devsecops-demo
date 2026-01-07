FROM openjdk:8-jdk-slim
WORKDIR /app
COPY target/vuln-0.0.1-SNAPSHOT.jar app.jar
USER 1000:1000
EXPOSE 8002
ENTRYPOINT ["java","-jar","app.jar"]
