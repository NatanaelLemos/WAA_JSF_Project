#!/bin/bash

app="waa-final-derby"

if sudo docker ps | awk -v app="$app" 'NR > 1 && $NF == app{ret=1; exit} END{exit !ret}'; then
  sudo docker rm $app -f
fi

if sudo docker container ls -a | awk -v app="$app" 'NR > 1 && $NF == app{ret=1; exit} END{exit !ret}'; then
  sudo docker container rm $app -f
fi

sudo docker run -dP \
-p 5000:5000 \
-p 1527:1527 \
-v /nodejs/docker-apache-derby-copie/webgui/:/webgui \
-v /dbs:/dbs \
--name $app \
-t adito/apache-derby
