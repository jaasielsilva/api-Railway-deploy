# Etapa de Build
FROM maven:3.8.6-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de Produção
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]