# Lab 6 — Event Registration Console Application (Java, OOP)

Console application for managing event registrations in memory (data is lost on exit).

## Requirements
- JDK 17 or later

## How to run
- In an IDE: open the project as a Gradle project and run `registration.Main.main()`.
- From a terminal: `./gradlew run` (Windows: `gradlew.bat run`)
- Without Gradle: `javac -d out src/main/java/registration/*.java && java -cp out registration.Main`

## Classes (package `registration`)
- **Registration** — data class with private fields (id, fullName, email, studentGroup, eventTitle), constructor, getters and `toString()`.
- **RegistrationManager** — owns the `ArrayList<Registration>`; validation, add (auto IDs from 1, never reused), show all, find by ID, delete by ID, filter by event.
- **Main** — console menu (`Scanner`, loop, `switch`); reads input and calls the manager.

## Events
1. Web Design Workshop
2. JavaScript Quiz
3. IT Career Talk
