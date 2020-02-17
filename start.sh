#!/bin/bash

docker stop RPS_GAME
docker run --publish 9000:9000 --name RPS_GAME -e ADVERTISED_HOST=`docker-machine ip \`docker-machine active\`` -e ADVERTISED_PORT=9000 spawn.thorn.consulting/com.mydatamodels/rockpaperscissors:latest
