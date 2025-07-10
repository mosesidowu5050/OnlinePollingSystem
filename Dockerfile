# Use OpenJDK base image
FROM openjdk:17-jdk-slim
VOLUME /tmp

# Set working directory
WORKDIR /app

# Copy the JAR file into the image (replace with your actual JAR name)
COPY target/OnlinePollingSystem-0.0.1-SNAPSHOT.jar app.jar

# Expose the port Spring Boot uses
EXPOSE 8086

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
