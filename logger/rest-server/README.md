# Module for REST-server and REST-API

This module contains the REST-server and REST-API for [Logger](../README.md).

## REST-server and REST-API

VisitLogApplication starts the spring boot server.
VisitLogService layer handles business requirements.
VisitLogController handles the navigation between the different views.

The server is run on localhost:8080.
The base endpoint is "/logger".

The rest-api offers three methods:
* read Visit Log (GET "/logger")
* add visit to Visit Log (POST "/logger")
* remove visit from Visit Log (DELETE "logger/{id}")

