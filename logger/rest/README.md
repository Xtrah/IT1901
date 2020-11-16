# Module for REST

This module contains the REST-server and REST-API for [Logger](../README.md).

## REST

VisitLogApplication starts the spring boot server.
VisitLogService layer handles business requirements.
VisitLogController handles the navigation between the different views.

The server is run on localhost:8080 using cmd:
`mvn -pl rest spring-boot:run` *

\*Choose remote or local storage by changing truth value in function `isRemoteStorage` in `logger/fxui/src/main/java/logger.fxui/AppController`.

The base endpoint for the rest-api is "/logger", offering three methods:
* read Visit Log (GET "/logger")
* add visit to Visit Log (POST "/logger")
* remove visit from Visit Log (DELETE "logger/{id}")

