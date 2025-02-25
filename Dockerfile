FROM maven:3.8.4-openjdk-21 AS build
WORKDIR /app
COPY src .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/seu-aplicativo.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
