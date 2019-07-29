FROM @docker.registry.name.prefix@/jdk8
# FROM 192.168.85.9:8010/docker-dev/jdk8
#VOLUME /tmp
ENV SERVICE_NAME message-service
ENV APP_HOME /app/miscroservice
RUN mkdir -p /data/logs
ADD ${SERVICE_NAME}.jar ${APP_HOME}/libs/${SERVICE_NAME}.jar
EXPOSE 16009
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_HOME}/libs/${SERVICE_NAME}.jar