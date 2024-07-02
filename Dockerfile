# Use the official OpenJDK image from the Docker Hub as the base image
FROM maven:3.8.5-openjdk-17 AS Build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
EXPOSE 8080
ENTRYPOINT ["java","jar","ecommerce.jar"]