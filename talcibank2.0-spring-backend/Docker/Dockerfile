FROM adoptopenjdk/openjdk11:latest

RUN mkdir -p /software/app

ADD talcibank-spring-rest-0.0.1-SNAPSHOT.jar /software/app/app.jar

CMD java -jar /software/app/app.jar -Dspring.profiles.active=${SPRING_ACTIVE_PROFILE}