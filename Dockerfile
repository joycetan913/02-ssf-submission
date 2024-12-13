FROM eclipse-temurin:23-jdk AS builder

LABEL maintainer="joycelin"

WORKDIR /src

COPY ./mvnw .
COPY .mvn .mvn

COPY pom.xml .
COPY src src

RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true 

FROM eclipse-temurin:23-jre-noble

WORKDIR /app

COPY --from=builder src/target/jar file

RUN apt update && apt install -y curl

ENV MY_API_PASSKEY=xyz789
ENV SPRING_DATA_REDIS_HOST=localhost SPRING_DATA_REDIS_PORT=6379
ENV SPRING_DATA_REDIS_USERNAME="" SPRING_DATA_REDIS_PASSWORD=""

EXPOSE ${PORT}

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
    CMD curl http://localhost:${SERVER_PORT}/health || exit 1

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar