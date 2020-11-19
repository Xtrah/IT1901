# Source-code for REST

This folder contains the REST-server and REST-API for [Logger](../../README.md).

## REST

- **VisitLogApplication** starts the spring boot server.
- **VisitLogService** layer handles business requirements.
- **VisitLogController** handles the navigation between the different views.

## Class diagram

![Rest class diagram](/logger/diagrams/rest_class_diagram.png)

### Description

The class diagram of the REST-module shows how the internal classes interact.
The VisitLogController is made up of one VisitLogService which is the service which actually contains the logic and does the work.
In the diagram one can also see that the SpringBootApplication is connected to the VisitLogApplication. This is because we use SpringBoot to implement the REST API and server.
