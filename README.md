# Laboratory Work #0: SOLID Principles

**Course:** Software Design Techniques and Mechanisms  
**Author:** Vremere Adrian  
**Group:** FAF-232  
**Date:** October 17, 2025

---

## Purpose

The purpose of this laboratory work is to demonstrate a practical understanding of the **SOLID principles** in object-oriented programming. This project implements a word grouping application that groups words based on different criteria (anagrams or letter sets) while adhering to SOLID design principles. The implementation showcases how these principles lead to maintainable, extensible, and testable code.

---

## Theory

### What are SOLID Principles?

SOLID is an acronym representing five fundamental principles of object-oriented design and programming:

1. **Single Responsibility Principle (SRP)**
   - A class should have only one reason to change
   - Each class should have a single, well-defined responsibility

2. **Open/Closed Principle (OCP)**
   - Software entities should be open for extension but closed for modification
   - New functionality should be added through extension rather than modification

3. **Liskov Substitution Principle (LSP)**
   - Objects of a superclass should be replaceable with objects of its subclasses without breaking the application
   - Subtypes must be substitutable for their base types

4. **Interface Segregation Principle (ISP)**
   - No client should be forced to depend on methods it does not use
   - Prefer multiple specific interfaces over a single general-purpose interface

5. **Dependency Inversion Principle (DIP)**
   - High-level modules should not depend on low-level modules; both should depend on abstractions
   - Abstractions should not depend on details; details should depend on abstractions

---

## Implementation Overview

The project is a **Word Grouping Application** that reads words from various sources and groups them based on different algorithms. The application demonstrates all five SOLID principles through its architecture.

### Project Structure

```
src/main/java/com/tmps/
├── Main.java                          # Entry point
├── Processor.java                     # Core processing logic
├── algorithm/
│   ├── WordGrouper.java              # Abstract base class for grouping
│   ├── AnagramWordGrouper.java       # Groups anagrams together
│   └── LetterSetWordGrouper.java     # Groups words by letter sets
├── io/
│   ├── Readable.java                 # Interface for reading input
│   ├── Writeable.java                # Interface for writing output
│   ├── FileReader.java               # File-based input
│   └── Console.java                  # Console-based I/O
└── model/
    ├── Histogram.java                # Character frequency representation
    └── LetterSet.java                # Unique character set representation
```

---

## SOLID Principles Application

### 1. Single Responsibility Principle (SRP)

Each class in the project has a single, well-defined responsibility:

- **`Processor`**: Orchestrates the word grouping process
- **`FileReader`**: Handles reading from files
- **`Console`**: Manages console input/output
- **`AnagramWordGrouper`**: Groups words that are anagrams
- **`Histogram`**: Represents character frequency in a word
- **`LetterSet`**: Represents unique characters in a word

**Example:**
```java
public class FileReader implements Readable {
    // Only responsible for reading from files
    @Override
    public List<String> read() {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            return List.of();
        }
    }
}
```

### 2. Open/Closed Principle (OCP)

The application is **open for extension** but **closed for modification**:

- New grouping algorithms can be added by extending `WordGrouper` without modifying existing code
- New I/O mechanisms can be added by implementing `Readable` or `Writeable` interfaces

**Example:**
```java
// Extending without modifying existing code
public abstract class WordGrouper<K> {
    public Collection<List<String>> group(Collection<String> words) {
        // Template method - algorithm defined, key creation delegated
    }
    protected abstract K createKey(String word);
}

// New algorithms through extension
public class AnagramWordGrouper extends WordGrouper<Histogram> {
    @Override
    protected Histogram createKey(String word) {
        return new Histogram(word);
    }
}
```

### 3. Liskov Substitution Principle (LSP)

Subclasses can be substituted for their base classes without breaking functionality:

- Any `WordGrouper` implementation can be used interchangeably in `Processor`
- Both `FileReader` and `Console` can substitute any `Readable` implementation
- The `Console` class implements both `Readable` and `Writeable`, maintaining contracts for both

**Example:**
```java
public class Processor {
    public void execute(Readable readable, Writeable writeable, WordGrouper<?> wordGrouper) {
        // Works with any Readable, Writeable, or WordGrouper implementation
        List<String> words = readable.read();
        Collection<List<String>> groupedWords = wordGrouper.group(words);
        for(List<String> wordGroup: groupedWords){
            writeable.write(wordGroup);
        }
    }
}
```

### 4. Interface Segregation Principle (ISP)

Interfaces are small and focused on specific client needs:

- **`Readable`**: Only defines `read()` method
- **`Writeable`**: Only defines `write()` method
- Clients depend only on the methods they need
- `Console` implements both interfaces independently

**Example:**
```java
public interface Readable {
    List<String> read();
}

public interface Writeable {
    void write(List<String> words);
}

// Console can choose to implement both
public class Console implements Writeable, Readable {
    // Implements both contracts
}
```

### 5. Dependency Inversion Principle (DIP)

High-level modules depend on abstractions, not concrete implementations:

- `Processor` (high-level) depends on `Readable`, `Writeable`, and `WordGrouper` abstractions
- Concrete implementations (`FileReader`, `Console`, `AnagramWordGrouper`) depend on these same abstractions
- Dependencies are injected through method parameters

**Example:**
```java
public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor();
        
        // Dependencies injected as abstractions
        Readable readable = new FileReader("sample.txt");
        Writeable writeable = new Console();
        WordGrouper<?> wordGrouper = new AnagramWordGrouper();
        
        processor.execute(readable, writeable, wordGrouper);
    }
}
```

---

## How It Works

### Algorithm: Word Grouping

The application groups words using a generic template method pattern:

1. **Read** words from an input source (file or console)
2. **Group** words using a specified algorithm:
   - **Anagram Grouping**: Groups words with the same character frequencies (e.g., "listen" and "silent")
   - **Letter Set Grouping**: Groups words with the same unique characters (e.g., "hello" and "hole")
3. **Write** the grouped results to an output destination (console)

### Key Components

#### Grouping Algorithms

- **`Histogram`**: Creates a character frequency map to identify anagrams
  - Example: "listen" → [1×l, 1×i, 1×s, 1×t, 1×e, 1×n]
  
- **`LetterSet`**: Creates a set of unique characters
  - Example: "hello" → {h, e, l, o}

#### Template Method Pattern

The `WordGrouper` abstract class implements the Template Method pattern:
- Defines the algorithm skeleton in `group()`
- Delegates key creation to subclasses via `createKey()`

---

## Running the Application

### Prerequisites
- Java 11 or higher
- Maven

### Build the Project
```bash
mvn clean compile
```

### Run the Application
```bash
mvn exec:java -Dexec.mainClass="com.tmps.Main"
```

### Expected Output
The application reads words from `sample.txt` and groups anagrams together, displaying them on the console.

---

## Benefits of SOLID Design

This implementation demonstrates several benefits of following SOLID principles:

1. **Maintainability**: Each class has a clear purpose, making the code easy to understand and modify
2. **Extensibility**: New grouping algorithms or I/O methods can be added without changing existing code
3. **Testability**: Components can be tested independently due to dependency injection
4. **Flexibility**: Different combinations of readers, writers, and groupers can be configured at runtime
5. **Reusability**: Interfaces and abstract classes can be reused in different contexts

---

## Conclusions

This laboratory work successfully demonstrates the application of all five SOLID principles in a practical Java application:

- **SRP** ensures each class has a single, well-defined responsibility
- **OCP** allows extension through new implementations without modifying existing code
- **LSP** enables substitutability of implementations through proper abstraction
- **ISP** provides focused, client-specific interfaces
- **DIP** decouples high-level logic from low-level implementations through abstractions

The resulting code is clean, maintainable, extensible, and demonstrates professional software engineering practices. These principles form the foundation for building scalable and robust software systems.

---

## References

1. Martin, R. C. (2000). *Design Principles and Design Patterns*
2. Martin, R. C. (2017). *Clean Architecture: A Craftsman's Guide to Software Structure and Design*
3. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*
