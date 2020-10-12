# ![Logo](https://i.imgur.com/mCYWZDc.png) Logger, the app

The purpose of the app is to register a user's visits to rooms and see an overview of all their visits.

## User stories

- As a user I want to register where I have been using my name and phone number, when I visited and how long
- As a user I want to to pick a room from a menu
- As a user I want to see former visits

## Illustration

![UI register view](https://i.imgur.com/gxikIqa.png)
![UI visit log view](https://i.imgur.com/UYIaMtT.png)

## Class Diagram

![PlantUML class diagram](../PlantUML_diagram.png)

The PlantUML diagram illustrates the relationship between the classes. We have chosen a hierarchic layout.  

App.java lies besides the structure because it has no form of dependency or relationship with the others. This is because it simply instructs the program to start.

Visit.java, which sits at the top, is the class which formats and creates the visits. Therefore, all other classes depend on this class to create Visit objects.

VisitLog.java keeps track of all the visits in one log. It has the functionality of adding visits and reading/writing to file.

AppController.java plays the role of combining all the functionality and connecting it to the FXML. 
Furthermore, the controller has the full set of input fields declared as FXML variables. Therefore, to access the fields, you have to use the controller.
Other features of the controller are validation and checks for the input fields. This ensures that no matter the user input, the program has a way of recognizing its validity and dealing with potential errors.
Lastly, it is the controller that creates both the instance of Visit and VisitLog.
AppController.java is solely dependent on VisitLog to exist and work for the test to run. This is also true for the VisitLogTest class. VisitLog is further dependent on the Visit class.

On a conceptual level you could say that you need the concept of visits to comprehend the idea og a visit log. The controller and the test class depends on this also as they directly work using the Visit log, which uses Visit.
