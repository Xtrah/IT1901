# Source code for FXUI

The user interface builds using JavaFX and FXML.

The UI is simple and consists of two tabs. The register-tab is used to create and add a Visit to VisitLog. The Visit Log tab uses a table view to show all Visits in a Visit Log. It also gives the user the possibility to filter on various criteria and to delete a Visit.

## Utils

We have several supporting [utility classes](utils/) for our AppController to handle Visit Log access to both local and remote `.json` files to allow multiple types of persistence.

## Architecture

The architecture is a standard JavaFX application using FXML as  resource. The app initializes using the class **App.java**, while **AppController.java** handles user interaction.

FXML resources are located in [logger/fxui/src/main/resources/logger/fxui](logger/fxui/src/main/resources/logger/fxui).

## Class diagram

Class diagrams are found in [logger/diagrams](logger/diagrams).

![Fxui class diagram](/logger/diagrams/fxui_class_diagram.png)

### Description

The App extends the Application-class and is used to start the program through the start-method. It does this by loading the fxml and starting the AppController.

After the application has been started, the app controller starts initializing the app. The controller further delegates logical operations to the core layer and uses the various data access layers to get data and display them to the user.
