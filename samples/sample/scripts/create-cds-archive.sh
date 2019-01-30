#!/usr/bin/env bash

# STEP 1: collect classes to cache

#java -XX:DumpLoadedClassList=classes.lst -jar ../target/sample-0.0.1-SNAPSHOT.jar
# stop the app...

# Step 2: create the cache
#java -Xshare:dump -XX:SharedClassListFile=classes.lst -XX:SharedArchiveFile=app-cds.jsa --class-path ../target/sample-0.0.1-SNAPSHOT.jar

# Step 3: use the cache
# use it in that way
#java -XX:+UseAppCDS -Xshare:on -XX:SharedArchiveFile=app-cds.jsa -jar app.jar