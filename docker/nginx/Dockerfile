FROM alpine/git as clone

ARG TOKEN_GIT
ARG USERNAME_GIT
ARG TAG_CLIENT
ARG REPOSITORY_CLIENT

WORKDIR /app

RUN git clone https://${TOKEN_GIT}@github.com/${USERNAME_GIT}/${REPOSITORY_CLIENT}.git --branch=${TAG_CLIENT}


# The builder from node image
FROM node:alpine as builder
# build-time variables
# prod|sandbox its value will be come from outside
ARG ENV_CLIENT
ARG REPOSITORY_CLIENT

RUN apk update && apk add --no-cache make git

# Move our files into directory name "app"
WORKDIR /app

COPY --from=clone /app/${REPOSITORY_CLIENT}  /app

RUN npm install @angular/cli -g

RUN cd /app && npm install

# Build with $env variable from outside
RUN cd /app && npm run build:${ENV_CLIENT}


# Build a small nginx image with static website
FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*
COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=builder /app/dist/ui /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]