FROM @docker.registry.name.prefix@/jdk8
# FROM 192.168.85.9:8010/docker-dev/jdk8
ENV SERVICE_NAME oms-api
ENV APP_HOME /app/miscroservice
RUN mkdir -p /data/logs
ADD ${SERVICE_NAME}.jar ${APP_HOME}/libs/${SERVICE_NAME}.jar
EXPOSE 16103
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_HOME}/libs/${SERVICE_NAME}.jar