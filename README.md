# SMU8 Java Study Package

This repository is the learner-facing code package for the Java manuscript. If Appendix A sends you to the repo in chapter 1, start here before browsing individual source files.

## Quick Start

Compile the full sample set from the repository root:

```sh
rm -rf out
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name '*.java')
```

Run the chapter 1 anchor example:

```sh
java -classpath ./out com.smu8.study.L01HelloJava
```

Expected first output:

```text
안녕 자바야2!!!
프린트
프린트
프린트
```

If your terminal does not use UTF-8, Korean strings may render incorrectly even when the code compiles normally.

## Repository Map

- `src/com/smu8/study`: beginner syntax and control-flow examples for the early chapters
- `src/com/smu8/oop`: object-oriented programming, inheritance, abstraction, interfaces, and related patterns
- `src/com/smu8/javautil`: collections, streams, files, threads, sockets, timers, and Swing examples
- `src/com/smu8/game`: compact game-oriented lesson samples
- `src/com/smu8/ex`: larger exercises and milestone builds
- `src/com/smu8/homework`: homework-oriented variants and practice files
- `docs/appendix-b-learner-guide.md`: stable learner checkpoints, expected outcomes, and appendix navigation targets

## Recommended Learner Path

1. Start with `com.smu8.study.L01HelloJava` and move through the `study` package in filename order.
2. Use the Appendix B learner guide when the manuscript asks you to verify a checkpoint or find the canonical solution file for a lab.
3. Move into `com.smu8.oop` after you are comfortable compiling and running the `study` package examples.
4. Treat `com.smu8.javautil` as the advanced track that follows the core syntax and OOP material.
5. Use `com.smu8.game` and `com.smu8.ex` for milestone-style exercises after the chapter concept examples are clear.

## Stable Learner Anchors

- [Appendix B: Learner Checkpoints and Solution Guide](docs/appendix-b-learner-guide.md)
- [Checkpoint 1: Chapters 1-6 foundation](docs/appendix-b-learner-guide.md#checkpoint-1-chapters-1-6-foundation)
- [Checkpoint 2: Chapters 7-11 object and method basics](docs/appendix-b-learner-guide.md#checkpoint-2-chapters-7-11-object-and-method-basics)
- [Checkpoint 3: Chapters 12-14 inheritance-abstraction-and-interfaces](docs/appendix-b-learner-guide.md#checkpoint-3-chapters-12-14-inheritance-abstraction-and-interfaces)
- [Checkpoint 4: Chapters 15-20 collections-files-threads-and-ui](docs/appendix-b-learner-guide.md#checkpoint-4-chapters-15-20-collections-files-threads-and-ui)
- [Lab A2: Rock Paper Scissors](docs/appendix-b-learner-guide.md#lab-a2-rock-paper-scissors)
- [Lab B1: Blackjack milestone path](docs/appendix-b-learner-guide.md#lab-b1-blackjack-milestone-path)
- [Chapter 16 companion: Path and Files](docs/appendix-b-learner-guide.md#chapter-16-companion-path-and-files)
