FROM adoptopenjdk/openjdk11:jdk-11.0.1.13 AS jdkBuilder

RUN $JAVA_HOME/bin/jlink \
--module-path /opt/jdk/jmods \
--verbose \
--add-modules java.base,java.sql,java.desktop,java.logging,java.xml,jdk.unsupported,java.naming,java.management,java.security.jgss,java.instrument \
--output /opt/jdk-minimal \
--compress 2 \
--no-header-files

FROM debian:9-slim
COPY --from=jdkBuilder /opt/jdk-minimal /opt/jdk-minimal

ENV JAVA_HOME=/opt/jdk-minimal
ENV PATH="/opt/jdk-minimal/bin/:${PATH}"

# Creating A JDK Class-Data Archive
RUN java -Xshare:dump

RUN mkdir /opt/app
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app/app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar
