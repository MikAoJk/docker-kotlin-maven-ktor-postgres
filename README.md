# docker-kotlin-maven-ktor-postgres
This project is for testing development with docker, kotlin, maven, ktor and postgreSQL

[![build master branch](https://github.com/MikAoJk/docker-kotlin-maven-ktor-postgres/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/MikAoJk/docker-kotlin-maven-ktor-postgres/actions/workflows/build.yml)

## Technologies used
* JDK 21
* Kotlin
* Maven
* Docker
* Ktor
* PostgreSQL

## Getting started
## Running application locally

### Building the application
#### Compile and package application
To build locally and run the integration tests you can simply run
``` bash
./mvnw install
``` 
or on windows 
`mvnw.bat install`

#### Creating a docker image
Creating a docker image should be as simple as 
``` bash
docker build -t docker-kotlin-maven-ktor-postgres .
```

#### Running a docker image
``` bash
docker run -d --rm -it -p 8080:8080 docker-kotlin-maven-ktor-postgres
```
on linux: http://0.0.0.0:8080
on osx: http://0.0.0.0:8080
on windows : http://127.0.0.1:8080

#### Local testing
## Running the postgresSql db from docker compose
``` bash
docker-compose -p docker-kotlin-maven-ktor-postgres-compose up -d
```

## Run the main class in your favoritt IDE(Intellij)
Go to src/main/kotlin/Bootstrap.kt and run it

## Testing the endpoint
For testing the endpoint ValidateDataApi
You need a tool to send a request and to inspect the repsonse
A tool you can use is Postman: https://www.postman.com/downloads/
The endpoint is currently available at http://$yourlocalhost/v1/validate when running the application locally 
Set the body in the request, example: `{"data":"DATA"}` and profit

Example of a request:
`curl --location --request POST 'http://127.0.0.1:8080/v1/validate' \
--header 'Content-Type: application/json' \
--data-raw '{"data":"DATA"}'`

Example of a response:
`{"result":"OK"}`

### Tear down the postgresSql db from docker compose
``` bash
docker-compose -p docker-kotlin-maven-ktor-postgres-compose down
```

### Contact

This project is maintained by [CODEOWNERS](CODEOWNERS)

Questions please create an
[issue](https://github.com/MikAoJk/docker-kotlin-maven-ktor-postgres/issues)


