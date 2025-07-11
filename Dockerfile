# ------------ Stage 1: Build the application ----------------
# Use a Maven image with Temurin JDK 21 for building
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
# Set the working directory for the runtime stage
WORKDIR /app

# Copy the built JAR file from the build stage into the runtime stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8086

ENTRYPOINT ["java", "-jar", "app.jar"]
