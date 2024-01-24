# 실행
FROM openjdk:11-jre-slim

ARG HJFUND_DEPLOY_TYPE
ENV HJFUND_DEPLOY_TYPE=${HJFUND_DEPLOY_TYPE}

COPY ./hjfund-0.0.1-SNAPSHOT.jar /app/app.jar
RUN chmod +x /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
