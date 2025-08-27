FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/stock-inventory-service-0.0.1-SNAPSHOT.jar /app/stock-inventory-service.jar

EXPOSE 8093

CMD ["java", "-jar", "/app/stock-inventory-service.jar"]