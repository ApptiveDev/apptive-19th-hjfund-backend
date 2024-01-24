# 실행
FROM openjdk:11-jre-slim

ARG HJFUND_DEPLOY_TYPE
ENV HJFUND_DEPLOY_TYPE=${HJFUND_DEPLOY_TYPE}

ARG JAR_FILE

WORKDIR /app

COPY ${JAR_FILE} app.jar
RUN chmod +x app.jar

CMD ["java", "-jar", "app.jar"]
