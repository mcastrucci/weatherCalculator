@Author Mcastrucci

# weatherCalculator
mercadolibre test

Spring boot app.

Clone the repository and then do a mvn clean install.
execute WeatherCalculatorApplicationTests.java as test to have the big output with all the details or 
run WeatherCalculatorApplication to create a server at localhost:8080

there is a little GUI located in the root location of the server (localhost:8080/) that will use the API /clima{day} 
to show the location graphicaly.

Api details:

/10Años  = just result of how many different periods there were in that time
/clima{day}  = (example: http://localhost:8080/clima?dia=10) you will get a json with the locations of the planets on that time:

Example: 
{"currentDay":10,
  "ferengiLocation":{"x":492.0,"y":-87.0},
  "betasoideLocation":{"x":1732.0,"y":-1000.0},
  "vulcanoLocation":{"x":643.0,"y":766.0},
  "weatherText":"normal"
}
