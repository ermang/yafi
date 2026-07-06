FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/yafi-1.0-SNAPSHOT.jar yafi-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "yafi-1.0-SNAPSHOT.jar"]
