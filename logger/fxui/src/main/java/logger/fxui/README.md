# Source code for FXUI

The user interface builds using JavaFX and FXML.

The UI is simple and consists of two tabs. The register-tab is used to create and add a Visit to VisitLog. The Visit Log tab uses a table view to show all Visits in a Visit Log. It also gives the user the possibility to filter on various criteria and to delete a Visit.

## Utils

We have several supporting [utility classes](utils/) for our AppController to handle Visit Log access to both local and remote `.json` files to allow multiple types of persistence.

## Architecture

The architecture is a standard JavaFX application using FXML as  resource. The app initializes using the class **App.java**, while the AppControllers handles user interaction.

We have chosen to split the controllers into two: **RestAppController** for REST interactions and **LocalAppController** for local interactions. Both of these inherit most of their functionality from AbstractAppController.

FXML resources are located in [logger/fxui/src/main/resources/logger/fxui](logger/fxui/src/main/resources/logger/fxui).

## Class diagram

Class diagrams are found in [logger/diagrams](logger/diagrams).

![Fxui class diagram](/logger/diagrams/fxui_class_diagram.png)
