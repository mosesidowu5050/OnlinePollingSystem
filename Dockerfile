# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file into the image (replace with your actual JAR name)
COPY target/*.jar app.jar

# Expose the port Spring Boot uses
EXPOSE 8086

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
