# Use a lightweight OpenJDK base image
FROM openjdk:17-jdk-slim
COPY target/app-cli-1.jar /app/app-cli.jar
WORKDIR /app
CMD ["java", "-jar", "app-cli.jar"]