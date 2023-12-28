FROM maven:3-eclipse-temurin-21

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN mvn package -Dmaven.test.skip=true

ENV PORT=8080 
ENV SPRING_REDIS_HOST=viaduct.proxy.rlwy.net
ENV SPRING_REDIS_PORT=16772
ENV SPRING_REDIS_USER=default
ENV SPRING_REDIS_PASSWORD=DK2cMEHBlM25KPhFHcaejK2ngIfJilfD

EXPOSE ${PORT}

ENTRYPOINT java -jar target/surahlisttest-0.0.1-SNAPSHOT.jar
