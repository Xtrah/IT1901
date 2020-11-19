# IT1901 gr2030 repository

## About

This repository contains the **Logger** JavaFX app, an app to register visits to rooms, and see an overview of all registered visits. It allows the user to create, browse, filter and delete visits at their own discretion. It reads and stores data to JSON objects, which can be stored either locally or remotely.

The app is located in [logger](logger). The repository also includes Gitpod configuration files and a [.gitignore](.gitignore) file for Java development.

## Running in Gitpod

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030)

Running the app in Gitpod will automatically install modules and run initialization tests. 

When the build is complete, run the app using `mvn -pl fxui javafx:run` and tests/checks using `mvn clean verify`.

## Developer installation

1. `git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030.git`
2. Open folder as Maven project in an IDE  
    **IntelliJ IDEA:** File -> New -> Module from Existing Sources -> `logger` folder -> Maven
3. Install modules by running `mvn clean install`
4. Run server using `mvn -pl rest spring-boot:run` *
5. Run app using `mvn -pl fxui javafx:run`
6. Run code coverage check, spotbugs and tests using `mvn clean verify`

\*Choose remote or local storage by changing controller on line 20 in `logger/fxui/src/main/resources/logger.fxui/App.fxml`.

## Git conventions

During development we will strive to follow [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) for our commit messages.

- [Overview of different commit types](https://github.com/commitizen/conventional-commit-types/blob/v3.0.0/index.json)
- [Rules for commit messages](https://github.com/conventional-changelog/commitlint/tree/master/%40commitlint/config-conventional)
