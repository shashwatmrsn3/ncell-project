# Use official OpenJDK image as base
FROM eclipse-temurin:21-jdk-alpine

# Set environment variables
ENV APP_HOME=/usr/app/

# Create app directory
WORKDIR $APP_HOME

# Copy the application jar (adjust name if needed)
COPY target/*.jar app.jar

# Expose port (adjust according to your app's port)
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
