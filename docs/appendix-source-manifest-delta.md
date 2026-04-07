# Appendix Source Manifest Delta

## Basis

- Canonical executable baseline: commit `2baccef24dbcc2f20093870f622bce237c3bc0d8`
- Reference audit: [CMP-19](/CMP/issues/CMP-19#document-audit)
- Reference production brief: [CMP-20](/CMP/issues/CMP-20#document-production-brief)

## Publication-Critical Normalizations

- Chapter 2 package snippet: normalize manuscript and appendix references to `package com.smu8.study;`
- Lab A2 canonical sample: use `src/com/smu8/game/L03RockPaperScissors.java`
- Legacy compatibility: `src/com/smu8/game/L03RockScissorsPaper.java` now delegates to the canonical class for older references
- Lab B1 blackjack stages:
  - `src/com/smu8/game/L04BlackJack.java`: deck/shuffle seed
  - `src/com/smu8/ex/E05BlackJack.java`: score logic and initial deck setup
  - `src/com/smu8/ex/E06BlackJack.java`: full game loop
- Chapter 14 Optional example: `src/com/smu8/javautil/L17Optional2.java` now uses a parameterized `Optional<Object>`
- Modern date/time examples:
  - `src/com/smu8/study/L23Class.java`
  - `src/com/smu8/oop/L03MethodArgs.java`
  - `src/com/smu8/oop/L10StaticObject.java`
- Chapter 16 NIO companion: add `src/com/smu8/javautil/L43NioFiles.java` as the appendix-ready bridge from classic IO to `Path`/`Files`

## Release Assembly Notes

- Validate the must-keep runnable set from [CMP-19](/CMP/issues/CMP-19#document-audit) by compilation against this normalized baseline
- Point Lab A2 prose and appendix references at `L03RockPaperScissors.java`, not the legacy spelling
- Point Blackjack milestone prose at `E05BlackJack.java` and `E06BlackJack.java`; treat `L04BlackJack.java` as the pre-game deck milestone
- Use `L43NioFiles.java` when the chapter 16 appendix needs a committed NIO sample instead of a prose-only note
