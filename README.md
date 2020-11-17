# IT1901 gr2030 repository

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030)

## About

This repository contains the **Logger** JavaFX app, an app to register visits to rooms, and see an overview of all registered visits. It allows the user to create, browse, filter and delete visits at their own discretion. It reads and stores data to JSON objects, which can be stored either locally or remotely.

The app is located in [logger](logger). The repository also includes Gitpod configuration files and a [.gitignore](.gitignore) file for Java development.

## Developer installation

1. `git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030.git`
2. Open folder as Maven project in an IDE or [Gitpod](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030)
3. Install core module to run server by running `mvn install -f core/pom.xml`
4. Run server using `mvn -pl rest spring-boot:run`
5. Install modules and run initialization tests by running `mvn clean install`
6. Run app using `mvn -pl fxui javafx:run`
7. Run code coverage check, spotbugs and tests using `mvn clean verify`

> To choose remote or local storage, change the truth value in the method `isRemoteStorage` in `logger/fxui/src/main/java/logger.fxui/AppController`.

## Git conventions

During development we will strive to follow [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) for our commit messages.

- [Overview of different commit types](https://github.com/commitizen/conventional-commit-types/blob/v3.0.0/index.json)
- [Rules for commit messages](https://github.com/conventional-changelog/commitlint/tree/master/%40commitlint/config-conventional)
