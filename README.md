# techtest

The classic game Rock-Paper-Scissors

#environment variables
ADVERTISED_HOSTNAME=localhost
ADVERTISED_PORT=9000

To run the program just enter on the command line:

```docker run --publish 9000:9000 -e ADVERTISED_HOST=localhost -e ADVERTISED_PORT=9000 com.mydatamodels/rockpaperscissors:latest```


Open a navigator to access the REST API:

http://localhost:9000/swagger
