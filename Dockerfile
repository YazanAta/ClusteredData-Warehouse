FROM eclipse-temurin:17

WORKDIR /app

ADD target/javatask-0.0.1-SNAPSHOT.jar javatask.jar

ENTRYPOINT ["java", "-jar", "javatask.jar"]
