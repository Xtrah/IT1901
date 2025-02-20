# IT1901 gr2030 repository

## About

This repository contains the **Logger** JavaFX app, an app to register visits to rooms, and see an overview of all registered visits. It allows the user to create, browse, filter and delete visits at their own discretion. It reads and stores data to JSON objects, which can be stored either locally or remotely.

The app is located in [logger](logger). The repository also includes Gitpod configuration files and a [.gitignore](.gitignore) file for Java development.

## Running in Gitpod

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030)

1. Click on the gitpod tag in this README.
2. Wait for the machine to start, letting it download the required dependencies and start the server. *
3. When the build is complete, run the app using `mvn -pl fxui javafx:run` on the right side console.
4. Run tests and checks using `mvn verify` in the right side console.

\* If the download fails, follow developer installation from step 3. This is a known GitPod+Maven problem. ([Github issue](https://github.com/Shippable/support/issues/1995))

## Developer installation

1. `git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030.git`
2. Open folder as Maven project in an IDE  
3. Install modules by running `mvn install` (make sure you are in the logger folder, not the gr2030 folder)
4. Run server using `mvn -pl rest spring-boot:run` *
5. Run app using `mvn -pl fxui javafx:run`
6. Run code coverage check, spotbugs and tests using `mvn verify`

\*Choose remote or local storage by changing controller on line 20 in [App.fxml](logger/fxui/src/main/resources/logger.fxui/App.fxml).

## Git conventions

During development, we will strive to follow [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) for our commit messages.

- [Overview of different commit types](https://github.com/commitizen/conventional-commit-types/blob/v3.0.0/index.json)
- [Rules for commit messages](https://github.com/conventional-changelog/commitlint/tree/master/%40commitlint/config-conventional)
