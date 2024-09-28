# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
VOLUME ["/path/to/local/pom.xml:/app/pom.xml"]

# Copy the source code
VOLUME ["/path/to/local/src:/app/src"]

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package -DskipTests

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/relationalmanager-0.0.1-SNAPSHOT.jar"]