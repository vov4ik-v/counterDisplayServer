#FROM openjdk:17-alpine
#ADD /target/*.jar app.jar
#EXPOSE 8088
#ENTRYPOINT ["java","-jar","app.jar"]

# Use an official Maven image as the base image
FROM maven:3.8.4-openjdk-17 AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn install -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:17-alpine
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/counterDisplayServer-0.0.1-SNAPSHOT.jar .
# Set the command to run the application
CMD ["java", "-jar", "counterDisplayServer-0.0.1-SNAPSHOT.jar"]