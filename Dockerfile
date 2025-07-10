# ------------ Stage 1: Build the application ----------------
# Use a Maven image with Temurin JDK 21 for building
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the Spring Boot application, skipping tests for faster build
RUN mvn clean package -DskipTests

# ------------ Stage 2: Run the application ------------------
# Use a smaller Temurin JRE 21 image for the runtime environment to reduce image size
FROM eclipse-temurin:21-jre-alpine
# Set the working directory for the runtime stage
WORKDIR /app

# Copy the built JAR file from the build stage into the runtime stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8086

# Define the entrypoint command to run the application
# Pass environment variables as Java system properties (-D)
# This ensures Spring Boot correctly resolves the placeholders from Render's environment variables.
ENTRYPOINT ["java", \
            "-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
            "-Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}", \
            "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", \
            "-Dserver.port=${PORT}", \
            "-Dapp.jwt.secret=${app.jwt.secret}", \
            "-Dapp_password=${app_password}", \
            "-Dapp.jwt.expiration-in-ms=${app.jwt.expiration-in-ms}", \
            "-Dspring.security.oauth2.client.registration.google.client-id=${CLIENT_ID}", \
            "-Dspring.security.oauth2.client.registration.google.client-secret=${CLIENT_SECRET}", \
            "-Dspring.security.oauth2.client.registration.google.scope=openid,profile,email", \
            "-Dspring.security.oauth2.client.registration.google.redirect-uri=${OAUTH_REDIRECT_URI}", \
            "-Dapp.oauth2.redirect-uri=${FRONTEND_REDIRECT_URI}", \
            "-Dspring.mail.username=${MAIL_USERNAME}", \
            "-Dspring.mail.password=${MAIL_PASSWORD}", \
            "-jar", "app.jar"]
