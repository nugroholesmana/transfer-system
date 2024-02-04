FROM amazoncorretto:17
WORKDIR /app
COPY ./target/transfer-system-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]
