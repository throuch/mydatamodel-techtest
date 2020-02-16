#!/bin/bash

docker run --publish 9000:9000 -e ADVERTISED_HOST=localhost -e ADVERTISED_PORT=9000 spawn.thorn.consulting/com.mydatamodels/rockpaperscissors:latest
