FROM openjdk:18-jdk-alpine

RUN apk --no-cache add curl maven

ENV DB_HOST=${DB_HOST}
ENV DB_NAME=${DB_NAME}
ENV DB_USER=${DB_USER}
ENV DB_PASS=${DB_PASS}

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:resolve

COPY src src

RUN mvn package -DskipTests

ENTRYPOINT ["java", "-jar", "target/application.jar"]