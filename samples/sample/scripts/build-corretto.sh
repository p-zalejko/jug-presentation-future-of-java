#!/usr/bin/env bash
# in order to use corretto we need to build it
docker build -t amazon-corretto-8 github.com/corretto/corretto-8-docker

cd ..
mvn clean package docker:build -Pjava8
mvn clean package docker:build -Pjava11