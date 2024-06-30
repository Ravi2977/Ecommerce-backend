# # Use an official Java runtime as a parent image
# FROM openjdk:17-jdk-alpine

# # Set the working directory in the container
# WORKDIR /app

# # Copy the jar file from the target directory to the container
# COPY target/myapp-0.0.1-SNAPSHOT.jar /app/myapp.jar

# # Make port 8080 available to the world outside this container
# EXPOSE 8080

# # Run the jar fil
# ENTRYPOINT ["java", "-jar", "myapp.jar"]
# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the target directory to the container
COPY target/myapp-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "myapp.jar"]
