#docker/dockerfile:1

FROM maven:3.8.6-openjdk-11

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN mvn package

CMD java -jar target/yafi-1.0-SNAPSHOT.jar
