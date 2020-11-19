# FXUI module

This is the user interface (frontend) module for Logger.

## Information

The fxui layer consists of all classes and logic that creates the user interface and handles all user interaction. Our UI allows the user to register a visit, and gives them a list of all registered visits with the option to filter and delete Visits at their own discretion.

The user interface builds using JavaFX and is located in the logger.fxui package. The JavaFX code is in [src/main/java/logger/fxui](src/main/java/logger/fxui) and the FXML file is in [src/main/resources/logger/fxui](src/main/resources/logger/fxui).

## Maven build

In addition to the plugins used [project-wide](../README.md#structure_and_maven_build), we use the following plugins in the FXUI module to run our client application/user interface:

- [JavaFX](https://github.com/openjdk/jfx) to create and show a client user interface
- [TestFX](https://github.com/TestFX/TestFX) to test our JavaFX App
- [WireMock](https://github.com/tomakehurst/wiremock) to test remote storage interactions
