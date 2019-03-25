#Git system environment
FROM alpine/git as clone

WORKDIR /app

RUN git clone https://5842ab875c2ee6355c2d5c5dd2ee9c9ba7d7b027@github.com/devpav/server-attraction.git --branch=2-d52c0c52


FROM maven:3.5-jdk-8-alpine as build

WORKDIR /app

COPY --from=clone /app/server-attraction /app

RUN mvn install -DskipTests -Dspring-boot.run.profiles=prod


#Java system environment
FROM openjdk:8-jre-alpine

ENV ARTIFACT server-attraction-0.0.1.jar
WORKDIR /app

COPY wait.sh /app/wait.sh
RUN chmod +x /app/wait.sh

COPY --from=build /app/target/${ARTIFACT} /app
EXPOSE 8080

CMD java -jar -Dspring.profiles.active=prod ${ARTIFACT}