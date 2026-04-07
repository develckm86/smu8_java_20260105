# Appendix B: Learner Checkpoints and Solution Guide

This appendix is the learner-facing navigation surface for the repository. Use it when the manuscript asks you to open the code package, verify a chapter checkpoint, or locate the canonical file for a lab.

## How To Use This Appendix

1. Compile the repository once from the root:

   ```sh
   rm -rf out
   mkdir -p out
   javac -encoding UTF-8 -d out $(find src -name '*.java')
   ```

2. Use the checkpoint or lab section below to jump to the correct source file.
3. Run the listed canonical class from `./out` after compiling.
4. Use the acceptance criteria to confirm that you are looking at the intended solution path, not a side experiment or older variant.

## Stable Targets

- [Checkpoint 1: Chapters 1-6 foundation](#checkpoint-1-chapters-1-6-foundation)
- [Checkpoint 2: Chapters 7-11 object and method basics](#checkpoint-2-chapters-7-11-object-and-method-basics)
- [Checkpoint 3: Chapters 12-14 inheritance, abstraction, and interfaces](#checkpoint-3-chapters-12-14-inheritance-abstraction-and-interfaces)
- [Checkpoint 4: Chapters 15-20 collections, files, threads, and UI](#checkpoint-4-chapters-15-20-collections-files-threads-and-ui)
- [Lab A2: Rock Paper Scissors](#lab-a2-rock-paper-scissors)
- [Lab B1: Blackjack milestone path](#lab-b1-blackjack-milestone-path)
- [Chapter 16 companion: Path and Files](#chapter-16-companion-path-and-files)

## Checkpoint 1: Chapters 1-6 Foundation

### Canonical solution files

- `src/com/smu8/study/L01HelloJava.java`
- `src/com/smu8/study/L04DataType.java`
- `src/com/smu8/study/L15IfSwitch.java`
- `src/com/smu8/study/L20For.java`

### Run commands

```sh
java -classpath ./out com.smu8.study.L01HelloJava
java -classpath ./out com.smu8.study.L20For
```

### Expected results

- `L01HelloJava` prints a greeting and demonstrates `print` versus `println`.
- `L20For` prints ordered number sequences, skips multiples of six in the 1-20 loop, and stops the cumulative sum once it crosses 20,000.

### Acceptance criteria

- `L01HelloJava` prints the greeting without package or classpath errors.
- `L20For` ends with `200번째에 끝` and `1~10000까지의 누적합 : 20100`.
- You can identify where variables, conditionals, loops, and output statements live before moving on to OOP.

## Checkpoint 2: Chapters 7-11 Object and Method Basics

### Canonical solution files

- `src/com/smu8/oop/L01Object.java`
- `src/com/smu8/oop/L02Method.java`
- `src/com/smu8/oop/L05Field.java`
- `src/com/smu8/oop/L06Constructor.java`
- `src/com/smu8/oop/L07AccessModifier.java`

### Expected results

- These files establish how Java classes hold fields, expose methods, and initialize state through constructors.
- The package moves from standalone syntax practice into object creation and member access.

### Acceptance criteria

- All five files compile in the shared `javac` pass from the repository root.
- You can create an instance, call at least one method, and explain the difference between a field and a local variable.
- You can explain why constructor work happens before the object is used elsewhere in the code.

## Checkpoint 3: Chapters 12-14 Inheritance, Abstraction, and Interfaces

### Canonical solution files

- `src/com/smu8/oop/L11Extends.java`
- `src/com/smu8/oop/L13Override.java`
- `src/com/smu8/oop/L17TypePolymorphism.java`
- `src/com/smu8/oop/L19AbstractClass.java`
- `src/com/smu8/oop/L20Interface.java`

### Run commands

```sh
java -classpath ./out com.smu8.oop.L11Extends
java -classpath ./out com.smu8.oop.L20Interface
```

### Expected results

- `L11Extends` shows inherited field access and superclass method reuse.
- `L20Interface` prints an implementation walking and making a sound, then demonstrates instance versus shared state.

### Acceptance criteria

- `L11Extends` prints `100` and `200` in order.
- `L20Interface` prints:

  ```text
  어슬렁어슬렁
  어흥!
  1
  1
  ```

- You can distinguish `extends`, overriding, abstract types, and interfaces before moving into the advanced utility track.

## Checkpoint 4: Chapters 15-20 Collections, Files, Threads, and UI

### Canonical solution files

- `src/com/smu8/javautil/L06List.java`
- `src/com/smu8/javautil/L07Set.java`
- `src/com/smu8/javautil/L08Map.java`
- `src/com/smu8/javautil/L20StreamAPI.java`
- `src/com/smu8/javautil/L25Thread.java`
- `src/com/smu8/javautil/L43NioFiles.java`
- `src/com/smu8/javautil/L48Swing.java`

### Run commands

```sh
java -classpath ./out com.smu8.javautil.L43NioFiles
```

### Expected results

- The collections files show list, set, and map usage as the data-structure foundation for this section.
- `L20StreamAPI` begins the stream-processing track.
- `L25Thread` opens the concurrency track.
- `L43NioFiles` writes a UTF-8 sample file, reads it back, reports the line count, and deletes the file.
- `L48Swing` opens a small Swing window and introduces the UI track.

### Acceptance criteria

- `L43NioFiles` prints an absolute file path, the text `NIO는 Path와 Files 유틸리티로 파일을 다룹니다.`, and `줄 수 : 1`.
- The temporary `nio-sample.txt` file does not remain in the repository root after the program finishes.
- At least one Swing example from this section launches after the full repo compile succeeds.

## Lab A2: Rock Paper Scissors

### Canonical solution files

- `src/com/smu8/game/L03RockPaperScissors.java`
- `src/com/smu8/game/L03RockScissorsPaper.java` as a legacy compatibility alias for older references

### Run command

```sh
java -classpath ./out com.smu8.game.L03RockPaperScissors
```

### Expected results

- The program explains the controls, accepts `0`, `1`, or `2`, compares the user choice against a random computer choice, and continues until either side reaches three wins.
- The final line declares either `최종 승자 유저!` or `최종 승자 컴퓨터!`.

### Acceptance criteria

- The learner can play repeated rounds without recompiling.
- The canonical reference is `L03RockPaperScissors.java`; use the legacy spelling only when an older note still points there.
- The game loop terminates only when one side reaches three wins or the process is stopped externally.

## Lab B1: Blackjack Milestone Path

### Canonical solution files

- `src/com/smu8/game/L04BlackJack.java` for deck and shuffle setup
- `src/com/smu8/ex/E05BlackJack.java` for score calculation and initial deal verification
- `src/com/smu8/ex/E06BlackJack.java` for the full hit-or-stand game loop

### Run commands

```sh
java -classpath ./out com.smu8.ex.E05BlackJack
java -classpath ./out com.smu8.ex.E06BlackJack
```

### Expected results

- `E05BlackJack` prints the shuffled 52-card deck, the opening user and dealer hands, and both opening scores.
- `E06BlackJack` prints the blackjack rules, deals cards, accepts `Hit=0`, `Stand=1`, and `Exit=2`, and finishes with a win, loss, or draw outcome.

### Acceptance criteria

- The deck representation uses the `"무늬_숫자"` form and covers 52 cards.
- Face cards score as 10, and Ace handling follows the logic implemented in `setScore`.
- `E05BlackJack` and `E06BlackJack` remain the stable learner milestones for Appendix B references.

## Chapter 16 Companion: Path and Files

### Canonical solution file

- `src/com/smu8/javautil/L43NioFiles.java`

### Why this file matters

- This is the committed bridge from classic file IO into `Path` and `Files`.
- Use it when chapter 16 or an appendix note asks for a runnable NIO example instead of prose-only guidance.

### Acceptance criteria

- The sample compiles in the shared repository build.
- It creates, reads, reports, and deletes the UTF-8 sample file in a single run.
- Manuscript or appendix references should point to `L43NioFiles.java`, not to a missing future example.
