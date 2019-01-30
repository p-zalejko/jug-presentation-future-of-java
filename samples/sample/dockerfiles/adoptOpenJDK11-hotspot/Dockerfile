FROM adoptopenjdk/openjdk11:jdk-11.0.1.13

# Creating A JDK Class-Data Archive
# On Unix platforms, it is stored in ${JAVA_HOME}/lib/server
#
# to enable CDS: java -Xshare:on -jar <jar-name>.jar
#
# see also: scripts/create-cds-archive.sh
#
RUN java -Xshare:dump

RUN mkdir /opt/app
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app/app.jar
ADD classes.lst /opt/app/classes.lst

# create a schared-class archive
RUN java -Xshare:dump -XX:SharedClassListFile=/opt/app/classes.lst -XX:SharedArchiveFile=/opt/app/app-cds.jsa --class-path /opt/app/app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar
