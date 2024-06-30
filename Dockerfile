FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

ADD target/myapp.jar myapp.jar
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/myapp.jar"]
