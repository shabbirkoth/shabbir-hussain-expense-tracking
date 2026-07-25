# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project state

This is an early-stage bootstrap of the Expense-Tracking domain (see README.md). The Gradle scaffold is in place but no source files exist yet — `src/main/java/com/uptimecrew/expense_tracking/model` and `.../service` are empty, as is the test tree. Expect to be creating the first classes rather than modifying established ones.

## Build & test commands

- Build: `./gradlew build`
- Run all tests: `./gradlew test`
- Run a single test class: `./gradlew test --tests "com.uptimecrew.expense_tracking.SomeClassTest"`
- Run a single test method: `./gradlew test --tests "com.uptimecrew.expense_tracking.SomeClassTest.someMethod"`

Toolchain is JDK 21 (`build.gradle`), which satisfies the JDK 17+ baseline below. JUnit 5 (`junit-jupiter`, BOM 5.10.2) is wired up via `testImplementation`/`testRuntimeOnly`; no other dependencies are declared yet.

### Non-standard test source layout

Test sources live under `test/main/java/...` at the repo root, **not** the Gradle-default `src/test/java`. `build.gradle` does not currently declare a custom `sourceSets` block, so `./gradlew test` will not discover anything under `test/main/java` as-is. Before relying on `./gradlew test` to pick up new tests, either add a `sourceSets.test.java.srcDirs` entry pointing at `test/main/java`, or confirm with the user which layout is intended — don't silently move files to `src/test/java` without checking.

Also note: the existing test package is `com.uptimecrew.expoense_tracking` (typo — transposed "eo"), while main is `com.uptimecrew.expense_tracking`. Match whichever package a file already lives under; flag the mismatch rather than silently "fixing" it unless asked.

## Code conventions

These are project-mandated and apply to all new Java code:

- **Baseline**: JDK 17+ language/API surface (toolchain currently pins 21).
- **Money**: `BigDecimal`, scale 2, `RoundingMode.HALF_UP`. Never `double`/`float` for monetary values.
- **Identifiers**: `String` (UUID v4, or a prefixed synthetic ID). Never `int`/`long` for IDs.
- **Dates/times**: `LocalDate` for calendar dates, `Instant` for timestamps. No `java.util.Date`, `java.sql.Date`, or `Calendar`.
- **Fields & classes**: default to `private final` fields and `final` classes. No Lombok, no `@Data`.
- **Tests**: JUnit 5 (`@Test`, `assertEquals`, `assertTrue`, `assertThrows`, `@BeforeEach`) — not JUnit 4, not AssertJ/Hamcrest unless added deliberately.
- **Package root**: `com.uptimecrew.expense_tracking` for all main sources.
