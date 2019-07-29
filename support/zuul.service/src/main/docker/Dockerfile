FROM @docker.registry.name.prefix@/jdk8
#VOLUME /tmp
ENV SERVICE_NAME zuul-service
ENV APP_HOME /app/miscroservice
RUN mkdir -p /data/logs
ADD ${SERVICE_NAME}.jar ${APP_HOME}/libs/${SERVICE_NAME}.jar
EXPOSE 17005
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_HOME}/libs/${SERVICE_NAME}.jar