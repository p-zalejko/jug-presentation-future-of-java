#!/usr/bin/env bash

# get an offline version
# extract
# go to the folder with the presentation
# launch
docker run -p 80:80 -v $(pwd):/usr/share/nginx/html nginx
