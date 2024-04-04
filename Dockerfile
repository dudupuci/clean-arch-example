FROM openjdk:23-jdk-slim

LABEL maintainer="dudupuci"

ENV DB_NAME="gubee"
ENV DB_HOSTNAME="localhost"
ENV DB_PORT="5432"
ENV DB_USERNAME="postgres"
ENV DB_PWD="orp101099"

EXPOSE 8080

COPY infrastructure/target/infrastructure-0.0.1-SNAPSHOT.jar .

CMD ["java", "-Xms128m", "-Xmx400m", "-jar", "-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector", "infrastructure-0.0.1-SNAPSHOT.jar", "--jdbc.url=jdbc:postgresql://${DB_HOSTNAME}:${DB_PORT}/${DB_NAME}", "--jdbc.password=${DB_PWD}", "--jdbc.username=${DB_USERNAME}"]
