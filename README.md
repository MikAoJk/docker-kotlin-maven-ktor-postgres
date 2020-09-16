# docker-kotlin-maven-ktor-postgres
This project is for testing development with docker, kotlin, maven, ktor and postgresSql

## Technologies used
* Kotlin
* Maven
* Docker
* Ktor
* PostgresSql

## Getting started
## Running application locally

### Building the application
#### Compile and package application
To build locally and run the integration tests you can simply run `./mvnw install` or on windows 
`mvnw.bat install`

#### Creating a docker image
Creating a docker image should be as simple as `docker build -t docker-kotlin-maven-ktor-postgres .`

#### Running a docker image
`docker run --rm -it -p 8080:8080 docker-kotlin-maven-ktor-postgres`
on linux: http://0.0.0.0:8080
on osx: http://0.0.0.0:8080
on windows : http://127.0.0.1:8080

#### Running the postgresSql from docker compose
docker-compose up -d

#### Testing the endpoint
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


## Contact us
### Code/project related questions can be sent to
* Joakim Taule Kartveit, `joakim.kartveit@gmail.com`
