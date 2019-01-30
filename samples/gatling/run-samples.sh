#!/bin/sh

mvn clean package
mvn -f pom.xml -Dgatling.simulationClass=jug.Test gatling:test

