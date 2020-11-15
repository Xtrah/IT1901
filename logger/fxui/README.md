# FXUI module

This is the user interface module for Logger.

## Information

This layer consists of all classes and logic that creates the user interface and handles all user interaction. Our UI allows the user to register a visit, and gives them a list of all registered visits with the option to filter at their own discretion.

The user interface is built using JavaFX and is located in the logger.fxui package. The JavaFX code is located in [src/main/java/logger/fxui](src/main/java/logger/fxui) and the FXML file is located in [src/main/resources/logger/fxui](src/main/resources/logger/fxui).

## Maven build

In addition to the plugins used [project-wide](../README.md#Structure_and_Maven_build), we use the following plugins in the FXUI module to run our client application/user interface:

- [JavaFX](https://github.com/openjdk/jfx)
- [TestFX](https://github.com/TestFX/TestFX#testfx) and [Hamcrest](https://github.com/hamcrest/JavaHamcrest#java-hamcrest), to test our JavaFX code
- [Google Guava](https://github.com/google/guava#guava-google-core-libraries-for-java), for additional Java collection types
