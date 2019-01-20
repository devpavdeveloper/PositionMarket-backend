#Git system environment
FROM alpine/git as clone

ARG repository
ARG username
ARG password
ARG tag

WORKDIR /app

RUN git clone https://${username}:${password}@github.com/${username}/${repository}.git --branch=${tag}

#git clone https://username:password@github.com/username/repo_name.git

#Maven system environment
FROM maven:3.5-jdk-8-alpine as build

ARG project
ARG profile

WORKDIR /app

COPY --from=clone /app/${project} /app

RUN mvn install -DskipTests -Dspring-boot.run.profiles=${profile}


#Java system environment
FROM openjdk:8-jre-alpine

ARG artifactid
ARG version

ENV artifact ${artifactid}-${version}.jar
WORKDIR /app

COPY wait.sh /app/wait.sh
RUN chmod +x /app/wait.sh

COPY --from=build /app/target/${artifact} /app
EXPOSE 8080

CMD java -jar ${artifact}