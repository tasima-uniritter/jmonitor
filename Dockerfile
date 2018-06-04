FROM openjdk:8-jdk-alpine

WORKDIR /app

ADD ./build/libs/monitors.jar /app/monitors.jar

CMD java -jar monitors.jar