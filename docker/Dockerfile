
#system environment
ENV REPOSITORY;
ENV USERNAME_GIT;
ENV TAG;
ENV PROFILE;
ENV VERSION;

ENV ARTIFACT ${REPOSITORY}-${VERSION}.jar

#Git
FROM alpine/git as clone

WORKDIR /app

RUN git clone https://5842ab875c2ee6355c2d5c5dd2ee9c9ba7d7b027@github.com/${USERNAME_GIT}/${REPOSITORY}.git --branch=${TAG}

#git clone https://<token>@github.com/username/repo_name.git

#Maven
FROM maven:3.5-jdk-8-alpine as build

WORKDIR /app

COPY --from=clone /app/${REPOSITORY} /app

RUN mvn install -DskipTests -Dspring-boot.run.profiles=${PROFILE}

#Java
FROM openjdk:8-jre-alpine

WORKDIR /app

COPY wait.sh /app/wait.sh
RUN chmod +x /app/wait.sh

COPY --from=build /app/target/${ARTIFACT} /app
EXPOSE 8080

CMD java -jar -Dspring.profiles.active=${PROFILE} ${ARTIFACT}