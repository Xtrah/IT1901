# Core module

This is the core (backend) module for Logger. It contains the domain logic and persistence layers.

## Information

The core layer consists of all classes and logic that handles the backend information flow and structure for the Logger application. It is completely independent of the user interface, and the type of persistence the user chooses.

Our domain layer handles structuring visits into a visit log. It also takes care of validation for all values entered into a visit, and it gives frontend modules the possibility to implement same validation as used backend. The domain layer is in [logger/core/src/main/java/logger/core](logger/core/src/main/java/logger/core).

The persistence layer handles reading buildings from a source `.json`, and handles reading and writing of both visits, and the visit log into `.json` objects - both locally and remotely. The persistence layer is in [logger/core/src/main/java/logger/json](logger/core/src/main/java/logger/json).

## Maven build

In addition to the plugins used [project-wide](../README.md#structure_and_maven_build), we use the following plugins in the Core module to run our core module:

- [Jackson](https://github.com/FasterXML/jackson) to handle JSON parsing in Java
