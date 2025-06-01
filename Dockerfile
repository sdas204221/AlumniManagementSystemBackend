# -------- Step 1: Build the JAR --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# -------- Step 2: Run the JAR --------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/AlumniManagementSystem-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
