# docker-kotlin-maven-ktor-postgres
This project is for testing development with docker, kotlin, maven, ktor and postgreSQL

## Technologies used
* Kotlin
* Maven
* Docker
* Ktor
* PostgreSQL

## Getting started
## Running application locally

### Building the application
#### Compile and package application
To build locally and run the integration tests you can simply run `./mvnw install` or on windows 
`mvnw.bat install`

#### Creating a docker image
Creating a docker image should be as simple as `docker build -t docker-kotlin-maven-ktor-postgres .`

#### Running a docker image
`docker run -d --rm -it -p 8080:8080 docker-kotlin-maven-ktor-postgres`
on linux: http://0.0.0.0:8080
on osx: http://0.0.0.0:8080
on windows : http://127.0.0.1:8080

#### Local testing
## Running the postgresSql db from docker compose
docker-compose -p docker-kotlin-maven-ktor-postgres-compose up -d

## run the main class in your favoritt IDE(Intellij)
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
docker-compose -p docker-kotlin-maven-ktor-postgres-compose down

## Contact us
### Code/project related questions can be sent to
* Joakim Taule Kartveit, `joakimkartveit@gmail.com`
