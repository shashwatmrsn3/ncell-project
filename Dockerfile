# Stage 1: Build the app with Maven and JDK 21
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project and package JAR, skipping tests for faster build
RUN mvn clean package -DskipTests

# Stage 2: Run the app with JDK 21 slim
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
