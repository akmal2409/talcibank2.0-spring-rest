# Build Stage of my Spring Boot application
FROM openjdk:11-jdk-alpine as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .


RUN chmod +x ./mvnw

#download dependencies
RUN ./mvnw dependency:go-offline -B


COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Production stage for spring boo image
FROM openjdk:11-jdk-alpine as production
ARG DEPENDENCY=/app/target/dependency

#Copy dependencies from build artifact

COPY --from=build ${DEPENDECY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app


# Run the spring boot app
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "tech.talci.talcibankspringrest/TalcibankSpringRestApplication"]

