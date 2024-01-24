FROM openjdk:11-jre-slim

ARG HJFUND_DEPLOY_TYPE
ENV HJFUND_DEPLOY_TYPE=${HJFUND_DEPLOY_TYPE}

ARG JAR_FILE

WORKDIR /app
RUN echo "JAR_FILE is set to ${JAR_FILE}"

COPY ${JAR_FILE} /app/app.jar
RUN chmod +x /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
