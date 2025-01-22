
# Scala Blockudoku
![Scala](https://img.shields.io/badge/scala-%23DC322F.svg?style=for-the-badge&logo=scala&logoColor=white)
[![Tests](https://github.com/Freeeezee/scala-blockudoku/actions/workflows/tests.yml/badge.svg)](https://github.com/Freeeezee/scala-blockudoku/actions/workflows/tests.yml)
[![Coverage Status](https://coveralls.io/repos/github/Freeeezee/scala-blockudoku/badge.svg)](https://coveralls.io/github/Freeeezee/scala-blockudoku)

A clone of the mobile game [Blockudoku](https://apps.apple.com/de/app/blockudoku-block-puzzle/id1452227871?l=en-GB) written in Scala by Milena Zepp and Tom Berger.

This project is an academic project at HTWG Konstanz.

## Overview

- [Installation](##Installation)
- [Build](##Build)
- [Screenshots](##Screenshots)
- [Technical Details](##Technical Details)

## Installation

Pull the docker image using

```bash
docker pull freeeezee/blockudoku
```

then run the container with an XServer configuration on your platform.

<details>
  <summary>OS X/Linux</summary>

  Get container ip address:

  ```bash 
  ip=$(ifconfig en0 | grep inet | awk '$1=="inet" {print $2}') 
  xhost + $ip
  ```

  Then run:

  ```bash 
  docker run -e DISPLAY=$ip:0 -v /tmp/.X11-unix:/tmp/.X11-unix -ti freeeezee/blockudoku
  ```

</details>



*Note: Interacting with the TUI does not work as intended inside the docker container. To try it, [build](##Build) the game yourself and run it in a terminal that allows raw input.*

## Build

This project was built using `sbt 1.10.7`, `java corretto 17` and `scala 3.5.0`.

Clone the repo and run:

```bash
git clone https://github.com/Freeeezee/scala-blockudoku.git
cd scala-blockudoku
sbt run
```

## Screenshots

### GUI

<img src=".screenshots/title-screen.png" style="zoom:20%;" />

<img src=".screenshots/ingame.png" style="zoom:20%;" />

<img src=".screenshots/settings.png" style="zoom:20%;" />

### TUI

<img src=".screenshots/tui-ingame.png" style="zoom:30%;" />



## Technical Details

- Clean MVC architecture, with *ModelCollector* traits to read model data and modification through automatically undoable *commands*.
- Domain layer is fully tested in **readable** [tests](https://github.com/Freeeezee/scala-blockudoku/tree/main/src/test/scala/test) that outline component specs (UI Layer makes up around 30% of the codebase at the moment).
- Test coverage on main branch is tracked on [coveralls](https://coveralls.io/github/Freeeezee/scala-blockudoku) and new builds are automatically **containerized** and pushed to [docker hub](https://hub.docker.com/r/freeeezee/blockudoku).
- Output in both a textual UI on console with arrow-key based navigation and *ScalaFX* graphical UI with **synchronized** game state.
- Uses a **custom** dependency injection framework. For more detail see [YADIS](https://gitlab.com/Freeeezee/yadis).
- Game state is serialized as either JSON, XML (through libraries) or [YAML](https://github.com/Freeeezee/scala-blockudoku/tree/yaml/src/main/scala/blockudoku/saving/serializerYAMLImpl) (custom parser and serializer).
