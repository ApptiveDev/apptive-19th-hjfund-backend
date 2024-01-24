# 실행
FROM openjdk:11-jre-slim

ARG STOCKTREE_DEPLOY_TYPE
ENV STOCKTREE_DELOPY_TYPE=${STOCKTREE_DEPLOY_TYPE}

COPY --from=build ../hjfund-0.0.1-SNAPSHOT.jar /app/app.jar
RUN chmod +x /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
