FROM oracle/graalvm-ce:1.0.0-rc11

RUN mkdir /opt/app
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app/app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar