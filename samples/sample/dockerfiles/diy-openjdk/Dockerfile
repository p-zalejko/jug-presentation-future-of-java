FROM fedora:29 AS jdkBuilder

RUN dnf install -y wget

RUN wget -O openJDK.gz https://bitbucket.org/pzalejko/openjdk-builds/raw/66da16eeec57ac1a86acac58934bbd31e3a00aa7/openJDK-11.0.1.gz
RUN mkdir /openJDK
RUN tar -xf openJDK.gz -C /openJDK

# I use fedora 29, not any slim. Due to required libraries in the system...
FROM fedora:29
COPY --from=jdkBuilder /openJDK/jdk /opt/jdk
ENV JAVA_HOME=/opt/jdk
ENV PATH="/opt/jdk/bin/:${PATH}"

RUN mkdir /opt/app
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app/app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar