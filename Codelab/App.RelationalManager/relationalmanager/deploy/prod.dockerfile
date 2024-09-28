# Builder stage
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory in the builder container
WORKDIR /app

# Copy the Maven project file and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runner stage
FROM openjdk:17-jdk-slim

# Set the working directory in the runner container
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/target/relationalmanager-0.0.1-SNAPSHOT.jar ./relationalmanager.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "relationalmanager.jar"]