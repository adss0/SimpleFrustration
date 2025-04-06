# Simple Frustration

## 📌 Overview

This project is a simulation of the **Simple Frustration** board game, implemented in Java. The simulation models game mechanics including movement, hitting, winning conditions, and several configurable **variations** and **advanced features** such as different board sizes, undo functionality, and up to four players.

---

## Table of Contents
1. [Variations](#variations)
2. [Key Classes & Responsibilities](#key-classes--responsibilities)
3. [Program Execution Flow](#program-execution-flow)
4. [Advanced Features](#advanced-features)
5. [Design Patterns](#design-patterns)
6. [UML Diagrams](#uml-diagrams)



## 🔧 Variations

| Variation             | Description                                                                                     |
|-----------------------|-------------------------------------------------------------------------------------------------|
| **End Rule**          | Player must land **exactly** on the END position to win. If they overshoot, they "bounce back". |
| **Hit Rule**          | When a player lands on another, the hit player is sent back to their home.                      |
| **Single Die Option** | Can play with either one 6-sided die or two.                                                    |
| **Large Game.Board**       | Supports board Size for 36 player positions with 6 tail positions.                              |

These variations are controlled via the setup parameters in `Game.com.simpleFrustration.Main.java`, allowing dynamic simulation configuration.

---

## 🚀 Advanced Features

| Feature          | Description                                                                                                        |
|------------------|--------------------------------------------------------------------------------------------------------------------|
| **Four Game.Players** | Supports Red, Blue, Green, and Yellow players. Automatically calculates Home and End positions for both board sizes. |
| **Undo Move**    | After each move, player can undo and retry.      |

--- 
## Project Structure
````
src/
└── com/
    └── simpleFrustration/
        ├── board/  # Contains logic related to the game board
        │   ├── Commands.java    # Game commands for the board
        │   ├── GameBoard.java   # Main game board logic
        │   ├── ICommands.java   # Interface for game commands
        │   └── IGameBoard.java  # Interface for game board functionality
        ├── config/  # Game settings configuration
        │   └── GameConfiguration.java   # Setup and configuration for the game
        ├── dice/  # Dice logic and dice selection
        │   ├── DiceShaker.java          # Dice shaking Interface
        │   ├── DiceShakerFactory.java   # Factory to create DiceShaker instances
        │   ├── DiceShakerFactoryManager.java # Choose between different DiceShakers
        │   ├── FixedDiceShaker.java     # Implementation of a fixed dice shaker
        │   ├── FixedDiceShakerFactory.java  # Factory for fixed dice shakers
        │   ├── RandomDoubleDiceShaker.java  # Random double dice shaker
        │   ├── RandomDoubleDiceShakerFactory.java  # Factory for random double dice
        │   └── RandomSingleDiceShaker.java  # Random single dice shakers
        │   └── RandomSingleDiceShakerFactory.java  # Factory for random single dice shakers
        ├── events/  # Event handling during the game
        │   ├── CollisionEvent.java        # Event for collision
        │   ├── EventManager.java          # Manages all events in the game
        │   ├── GameBoardObserver.java    # Interface for observable objects 
        │   ├── HomeEvent.java            # Event related to home position
        │   ├── Observable.java           # Observes game board events
        │   ├── OverflowEvent.java        # Event for overflow situations
        │   ├── OvershootEvent.java       # Event for overshoot situations
        │   ├── PositionChangeEvent.java  # Event for position changes
        │   └── UnderflowEvent.java       # Event for underflow situations
        ├── facade/  # Facade pattern for initializing the game
        │   ├── GameFacade.java   # Game facade to handle game setup and interactions
        │   └── IFacade.java      # Interface for the game facade
        ├── movements/  # Handle different types of movements during the game
        │   ├── HandleCollision.java     # Handles collision scenarios
        │   ├── HandleOverflow.java     # Handles overflow scenarios
        │   ├── HandleOvershoot.java    # Handles overshoot scenarios
        │   ├── HandleUnderflow.java    # Handles underflow scenarios
        │   └── IMovementHandler.java   # Interface for movement handling
        ├── players/  # Player-related configurations and management
        │   ├── Colors.java         # Enum for player colors
        │   ├── IPlayerFactory.java  # Interface for player factory
        │   ├── Player.java         # Player class with attributes and actions
        │   ├── PlayerFactory.java  # Factory for creating players
        │   └── PlayerManager.java  # Manages the players in the game
        └── Main.java   # Entry point to start the game

````

## 🧠 Design Patterns Used

| Pattern              | Usage                                                                           |
|----------------------|---------------------------------------------------------------------------------|
| **Factory Pattern**  | `PlayerFactory` creates different players without exposing instantiation logic. |
| **Strategy Pattern** | `DiceShaker` interface allows use of either single or double dice strategies.   |
| **Observer Pattern** | `Observerable` implements the `GameBoardObserver`                               |
| ** Facade Pattern**  | `com.simpleFrustration.facade.GameFacade` is a stateless Facade which implements a `com.simpleFrustration.facade.IFacade` interface       | 
---

## 📦 Key Classes & Responsibilities

| Class                    | Responsibility                                                                                           |
|-------------------------|----------------------------------------------------------------------------------------------------------|
| `Game.com.simpleFrustration.Main.java`             | Entry point of the program; configure and starts the game.                                               |
| `GameBoard.java`        | Represents the board logic, handles movement, hit detection, win condition, and undo functionality.      |
| `Player.java`           | Represents a player in the game; stores position, color, and home and end locations.                     |
| `PlayerManager.java`    | Manages creation and tracking of players using the `PlayerFactory`. Initializes players based on board size and count. |
| `PlayerFactory.java`    | Implements the **Factory Design Pattern** to create player objects with configured attributes.           |
| `DiceShaker.java`       | Interface for shaking dice. Allows swapping between single and double die via strategy pattern.          |
| `DiceShakerFactoryManager.java` | Returns the correct `DiceShaker` instance depending on game settings.                                    |
| `Commands.java`         | Executes player movement.                                                                                |
| `ICommands.java`        | Command pattern interface for encapsulating game execution.                                         |

---

## 📊 Game Outputs

During gameplay, the following information is printed:
- Current Player
- Game.Dice value
- Start and end position of the move
- Player's running turn count
- Undo prompt and confirmation if selected
- Game winner and total turn count at end

---

## 📌 Home Positions by Game.Board Type

### 🟩 Basic Game.Board (18 Game.com.simpleFrustration.Main Positions, 3 Tail)
- Red: 1
- Blue: 10
- Green: 5
- Yellow: 14

### 🟥 Large Game.Board (36 Game.com.simpleFrustration.Main Positions, 6 Tail)
- Red: 1
- Blue: 19
- Green: 10
- Yellow: 28

These are dynamically assigned based on board size and number of players.

---

## 📈 Sample Output (Console)

```text
Red rolled a 6: Moved from 1 to 7
Red has taken 1 turns.
Do you want to undo your move? (yes/no)
...
Yellow rolled a 2: Moved from 28 to 30
...
🏆 Game Over! Red wins!
Total turns taken by all players: 42
