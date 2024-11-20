# Mastermind: Console-Based Game Implementation

A Java-based console application that implements the classic Mastermind game, featuring both interactive gameplay and automated solving algorithms.

## Features
- Interactive human player mode
- Multiple AI solving algorithms:
    - Random solver
    - MiniMax solver
    - Brute Force solver
- Performance statistics tracking
- Configurable game parameters
- Real-time feedback system

## Tech Stack
- Java - Core programming language
- Gradle - Build automation tool
- JUnit - Testing framework

## Getting Started
Ensure you have Java 17+ installed.

Clone the repository:
```bash
git clone https://github.com/soconnor0919/mastermind.git
```
Build the project:
```bash
gradle build
```
Run the application:
```bash
gradle run
```

## Project Structure
```
src/
├── main/java/org/mastermind/
│ ├── Board.java           # Main game logic
│ ├── CodeMaker.java       # Code generation and validation
│ ├── GameManager.java     # Game flow management
│ ├── HumanPlayer.java     # Interactive player implementation
│ └── solvers/            # AI solving algorithms
└── test/                 # Test files
```

## Game Rules

Generate a 4-digit code using numbers between 1-6
Players have 12 attempts to guess the code
After each guess, feedback is provided:
"" indicates correct number in correct position
"+" indicates correct number in wrong position
"-" indicates incorrect number

## Dependencies

- JUnit Jupiter API: Version 5.8.2 - [Download](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
- JavaFX: Version 17 - [Download](https://gluonhq.com/products/javafx/)


## Team

Lyman Brackett - Sophomore Computer Science Engineering Major
Sean O'Connor - Sophomore Computer Science Engineering Major

## License

This project is part of CSCI 205 - Software Engineering and Design, Fall 2023, under Prof. Brian King at Bucknell University.
