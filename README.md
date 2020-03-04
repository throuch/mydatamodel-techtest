# techtest

The classic game Rock-Paper-Scissors

#environment variables
ADVERTISED_HOST=localhost
ADVERTISED_PORT=8080

## build
sbt docker:publishLocal

## run
To run the program just enter on the command line:

```docker run --publish 8080:9000 --name RPS_GAME -e ADVERTISED_HOST=localhost -e ADVERTISED_PORT=8080 com.mydatamodels/rockpaperscissors:latest```

Alternatively, you can choose an other port than 8080. And if you use a docker host different from the local host, replace the parameter:

```-e ADVERTISED_HOST=`docker-machine ip `docker-machine active`` ```

Open a navigator to access the REST API:

http://localhost:8080/swagger

**KNOWN BUG:**
using port 80 as ADVERTISED_PORT won't work because it's omitted by navigators and causes problem with Swagger.
