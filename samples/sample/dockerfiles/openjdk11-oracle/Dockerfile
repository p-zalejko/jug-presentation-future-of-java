FROM openjdk:11.0.1-oracle

# Creating A JDK Class-Data Archive
RUN java -Xshare:dump

RUN mkdir /opt/app
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app/app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar