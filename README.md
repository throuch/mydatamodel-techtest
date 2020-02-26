# techtest

The classic game Rock-Paper-Scissors

#environment variables
ADVERTISED_HOST=localhost
ADVERTISED_PORT=9000

## build
sbt docker:publishLocal

## run
To run the program just enter on the command line:

```docker run --publish 9000:9000 --name RPS_GAME -e ADVERTISED_HOST=localhost -e ADVERTISED_PORT=9000 com.mydatamodels/rockpaperscissors:latest```

Alternatively, if you use a docker host different from the local host, replace the parameter:

```-e ADVERTISED_HOST=`docker-machine ip `docker-machine active`` ```

Open a navigator to access the REST API:

http://localhost:9000/swagger
