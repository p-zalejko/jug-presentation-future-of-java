FROM fedora:29 as build-env

RUN dnf install -y unzip glibc-static zlib-static gcc make automake gcc gcc-c++ kernel-devel zlib-devel glibc-nss-devel wget

# notice: I use rc5, not rc-11.
ARG GRAALVM_BUILD=1.0.0-rc5
ARG GRAALVM_VERSION=graalvm-ce-${GRAALVM_BUILD}

RUN mkdir -p /opt/java
RUN cd /opt/java && wget https://github.com/oracle/graal/releases/download/vm-${GRAALVM_BUILD}/${GRAALVM_VERSION}-linux-amd64.tar.gz
RUN cd /opt/java && tar zxf ${GRAALVM_VERSION}-linux-amd64.tar.gz && rm ${GRAALVM_VERSION}-linux-amd64.tar.gz

ENV GRAALVM_HOME=/opt/java/${GRAALVM_VERSION}
ENV JAVA_HOME=${GRAALVM_HOME}
ENV PATH=${PATH}:${JAVA_HOME}/bin

ARG JAR_FILE
ADD ${JAR_FILE} /tmp/app.jar

RUN mkdir -p /tmp/jar-content
RUN unzip /tmp/app.jar -d /tmp/jar-content

ADD graalvm.json /graalvm.json
RUN native-image -H:ReflectionConfigurationFiles=graalvm.json \
                 -H:+ReportUnsupportedElementsAtRuntime \
                 -H:Name=jug-demo \
                 -Dio.netty.noUnsafe=true \
                 -Dfile.encoding=UTF-8 \
                 -Dcom.oracle.graalvm.isaot=true \
                 --static \
                 --verbose \
                 -cp "$(echo /tmp/jar-content/BOOT-INF/lib/*.jar | tr ' ' ':')":/tmp/jar-content/BOOT-INF/classes pawel.zalejko.sample.DemoApplication

RUN cp jug-demo /usr/bin/jug-demo
RUN rm -rf /opt/java ~/.m2 /tmp/app.jar /tmp/jar-content

FROM busybox:1.29.3-glibc
WORKDIR /usr/bin
COPY --from=build-env /usr/bin/jug-demo /usr/bin/jug-demo
RUN addgroup graalvm
RUN adduser graalvmuser -H -D -G graalvm graalvm -s /bin/false
USER graalvmuser
ENTRYPOINT [ "/usr/bin/jug-demo" ]
