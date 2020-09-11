# IT1901 gr2030 repository

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030)

## About

This repository contains the "Logger" JavaFX app, an app to register a user's visits to rooms and see an overview of all their visits.

The app is located in [logger/src](logger/src). The repository includes Gitpod configuration files and a [.gitignore](.gitignore) file configured for development in IntelliJ IDEA.

## Developer installation

1. `git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2030/gr2030.git`
2. Open folder as IntelliJ project
3. File -> New -> Module from Existing Sources -> `logger` folder -> Maven
4. Run app using `mvn javafx:run`.
5. Run tests using `mvn test`.

### Git conventions

During development of the app we will strive to follow [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) for our commit messages.

- [Overview of different commit types](https://github.com/commitizen/conventional-commit-types/blob/v3.0.0/index.json)
- [Rules for commit messages](https://github.com/conventional-changelog/commitlint/tree/master/%40commitlint/config-conventional)
