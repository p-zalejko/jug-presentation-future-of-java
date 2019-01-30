FROM amazon-corretto-8

# Creating A JDK Class-Data Archive
RUN java -Xshare:dump

RUN mkdir /opt/app
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app/app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar