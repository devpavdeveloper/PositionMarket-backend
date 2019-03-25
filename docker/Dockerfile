#Git system environment
FROM alpine/git as clone

ARG REPOSITORY
ARG USERNAME_GIT
ARG PASSWORD_GIT
ARG TAG

WORKDIR /app

RUN git clone https://${USERNAME_GIT}:${PASSWORD_GIT}@github.com/${USERNAME_GIT}/${REPOSITORY}.git --branch=${TAG}

#git clone https://username:password@github.com/username/repo_name.git

#Maven system environment
FROM maven:3.5-jdk-8-alpine as build

ARG PROJECT
ARG PROFILE

WORKDIR /app

COPY --from=clone /app/${PROJECT} /app

RUN mvn install -DskipTests -Dspring-boot.run.profiles=${PROFILE}


#Java system environment
FROM openjdk:8-jre-alpine

ARG ARTIFACTID
ARG VERSION
ARG PROFILE

ENV ARTIFACT ${ARTIFACTID}-${VERSION}.jar
WORKDIR /app

COPY wait.sh /app/wait.sh
RUN chmod +x /app/wait.sh

COPY --from=build /app/target/${ARTIFACT} /app
EXPOSE 8080

CMD java -jar -Dspring.profiles.active=${PROFILE} ${ARTIFACT}