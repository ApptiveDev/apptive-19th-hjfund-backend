# 실행
FROM openjdk:11-jre-slim
COPY --from=build ../hjfund-0.0.1-SNAPSHOT.jar /app/app.jar
RUN chmod +x /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
