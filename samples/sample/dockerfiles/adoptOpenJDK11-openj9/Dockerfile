FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13

RUN mkdir /opt/app
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app/app.jar

# -XX:+IdleTuningGcOnIdle
# -Xtune:virtualized
# and more ...

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar