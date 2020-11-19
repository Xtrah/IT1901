# Module for REST

This module contains the REST-server and REST-API for [Logger](../README.md).

## Information

* **VisitLogApplication** starts the spring boot server.
* **VisitLogService** layer handles business requirements.
* **VisitLogController** handles the navigation between the different views.

The server is run on localhost:8080 using cmd:
`mvn -pl rest spring-boot:run` *

\*Choose remote or local storage by changing controller on line 20 in `logger/fxui/src/main/resources/logger.fxui/App.fxml`.

The base endpoint for the rest-api is "/logger", offering three methods:

* read Visit Log (GET "/logger")
* add visit to Visit Log (POST "/logger")
* remove visit from Visit Log (DELETE "logger/{id}")

## Maven build

In addition to the plugins used [project-wide](../README.md), we use the following plugins in the REST module to run our server and REST API:

- [SpringBoot](https://spring.io/projects/spring-boot) to set up a server on localhost, set up REST API endpoints and for testing these features.
