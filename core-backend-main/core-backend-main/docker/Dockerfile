FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=/api/core-api/build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${ACTIVE_PROFILE}","/app.jar"]
