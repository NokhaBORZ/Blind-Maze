<div align="center">

# 🌀 Blind Maze

[![Scala](https://img.shields.io/badge/Scala-3.5.1-DC322F?style=for-the-badge&logo=scala&logoColor=white)](https://www.scala-lang.org/)
[![ScalaFX](https://img.shields.io/badge/ScalaFX-21.0.0-blue?style=for-the-badge)](https://www.scalafx.org/)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)
[![Coveralls](https://img.shields.io/coverallsCoverage/github/NokhaBORZ/Blind-Maze?style=for-the-badge)](https://coveralls.io/github/NokhaBORZ/Blind-Maze)
[![Codacy Badge](https://img.shields.io/codacy/grade/ffaf2f936cf746dfb411efe75c7a1195?style=for-the-badge&logo=codacy)](https://app.codacy.com/gh/NokhaBORZ/Blind-Maze/dashboard)

**A two-player maze navigation game built with Scala 3**

*Software Engineering Course Project — HTWG Konstanz*

</div>

---

## 📖 About

**Blind Maze** is a strategic game where two players compete to navigate through an invisible maze and reach the center first. Players cannot see the paths and must rely on intuition and memory to find their way. Along the journey, they may encounter items that provide advantages or obstacles. The first player to successfully reach the victory tile in the center wins!

### ✨ Features

- 🎮 **Two-Player Gameplay** — Compete locally against a friend
- 👁️ **Hidden Maze** — Paths and walls are invisible until discovered
- 🎯 **Victory Tile** — Race to reach the center of the maze first
- 💎 **Item System** — Discover items with various rarities (Common, Rare, Epic, Legendary)
- 🖥️ **Dual Interface** — Play via GUI (ScalaFX) or TUI (Terminal)
- 💾 **Save/Load System** — Persist your game in JSON or XML format
- ↩️ **Undo Functionality** — Take back your moves with the command history
- 🐳 **Docker Support** — Run the game in a containerized environment

---

## 🏗️ Architecture

This project follows clean software engineering principles and demonstrates several design patterns:

| Pattern | Implementation |
|---------|----------------|
| **MVC** | Model-View-Controller architecture separating game logic, UI, and control flow |
| **Command Pattern** | All player actions (move, start, quit, save, load, undo) are encapsulated as commands |
| **Observer Pattern** | Views subscribe to controller updates for reactive UI |
| **State Pattern** | Game states (NotStarted, Running, Finished) manage game flow |
| **Factory Pattern** | Tile creation through `TileFactory` |
| **Flyweight Pattern** | `TileCache` for efficient tile object reuse |
| **Dependency Injection** | Google Guice for IoC and loose coupling |

### 📁 Project Structure

```
src/main/scala/de/htwg/se/blindmaze/
├── blindmaze.scala          # Application entry point
├── controller/              # Game controller with command execution
├── model/
│   ├── commands/            # Command pattern implementations
│   ├── fileIO/              # Save/Load functionality (JSON & XML)
│   ├── grid/                # Maze grid logic
│   ├── item/                # Item system with rarities
│   ├── managers/            # Game state management
│   ├── player/              # Player model
│   └── tiles/               # Tile types and caching
├── modules/                 # Guice dependency injection module
├── utils/                   # Utilities (Direction, Position, Generator, etc.)
└── view/
    ├── TUI.scala            # Terminal User Interface
    └── gui/                 # ScalaFX Graphical User Interface
```

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK 17+**
- **Scala 3.5.1**
- **sbt 1.10+**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/NokhaBORZ/Blind-Maze.git
   cd Blind-Maze
   ```

2. **Run the game**
   ```bash
   sbt run
   ```

3. **Run tests**
   ```bash
   sbt test
   ```

4. **Build executable JAR**
   ```bash
   sbt assembly
   java -jar target/scala-3.5.1/BlindMaze.jar
   ```

### 🐳 Docker

Run the game in a Docker container with GUI support:

```bash
# Build the image
docker build -t blindmaze .

# Run with X11 forwarding (Linux/macOS)
docker run -it -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix blindmaze

# Or use docker-compose
docker-compose up --build
```

---

## 🎮 How to Play

### Controls

| Action | Player 1 | Player 2 |
|--------|----------|----------|
| Move Up | `W` | `I` |
| Move Down | `S` | `K` |
| Move Left | `A` | `J` |
| Move Right | `D` | `L` |

### Game Commands

| Command | Description |
|---------|-------------|
| `n` | Start a new game |
| `u` | Undo last move |
| `save` | Save current game state |
| `load` | Load saved game |
| `q` | Quit the game |

### Objective

1. Players start at opposite corners of the maze
2. Navigate through the invisible maze by trial and error
3. Find and collect items for advantages
4. First player to reach the **victory tile** in the center wins!

---

## 🛠️ Tech Stack

- **Language:** Scala 3.5.1
- **GUI Framework:** ScalaFX 21.0.0
- **Dependency Injection:** Google Guice 5.1.0
- **JSON Processing:** Play JSON 3.0.4
- **XML Processing:** Scala XML 2.3.0
- **Testing:** ScalaTest 3.2.10, Mockito
- **Build Tool:** sbt with Assembly plugin
- **Containerization:** Docker

---

## 👥 Authors

| Name | GitHub |
|------|--------|
| **Nokha ** | [@NokhaBORZ](https://github.com/NokhaBORZ) |
| **Olti ** | [@oltir06](https://github.com/oltir06) |

---

## 📄 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

<div align="center">

**Made with ❤️ at HTWG Konstanz**

*Software Engineering Course 2024*

</div>
