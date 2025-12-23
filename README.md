# Reflection-based Dependency Injection

A simple dependency injection implementation in Java using reflection and a `config.properties` file.

## Overview

- Uses the `@AutoInjectable` annotation to mark fields for dependency injection.
- Dependencies are configured in `src/main/resources/config.properties`.
- Injection is performed via reflection (no Spring or other frameworks).

## Project Structure

```
reflection-lab/
├── src/
│ ├── main/
│ │ └── java/ru/vsu/
│ │ ├── Injector.java
│ │ ├── SomeBean.java
│ │ ├── SomeInterface.java
│ │ ├── SomeImpl.java
│ │ ├── SomeOtherInterface.java
│ │ ├── SODoer.java
│ │ ├── OtherImpl.java
│ │ └── AutoInjectable.java
│ │ └── resources/
│ │ └── config.properties
│ └── test/
│ └── java/ru/vsu/
│ └── InjectorTest.java
├── pom.xml
└── README.md
```

## Requirements

- Java 21
- Maven 3.6+

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/fleekwy/reflection-lab.git
cd reflection-lab
```

2. Build the project:

```bash
mvn clean install
```

After successful build, a JAR file will appear in the `target/` folder.

## Run the Example

```bash
mvn exec:java -Dexec.mainClass="ru.vsu.Main"
```

Expected output:

```
A
C
```

## Generate JavaDoc

1. Generate documentation in `target/site/apidocs`:

```bash
mvn javadoc:javadoc
```

2. Open in browser:

```
target/site/apidocs/index.html
```

3. (Optional) Generate JavaDoc and attach it to the JAR:

```bash
mvn clean package
```

A `reflection-lab-1.0-SNAPSHOT-javadoc.jar` will appear in `target/`.

## Run Tests

```bash
mvn test
```

Or with clean build:

```bash
mvn clean test
```

## Adding a New Dependency

1. Create a new interface and its implementation.
2. Add a line to `src/main/resources/config.properties`:

```
ru.vsu.NewInterface=ru.vsu.NewImpl
```

3. In the class where you need injection, add a field:

```java
@AutoInjectable
private NewInterface someField;
```

## Useful Maven Commands

| Command               | Description                               |
| --------------------- | ----------------------------------------- |
| `mvn clean`           | Clean the project                         |
| `mvn compile`         | Compile sources                           |
| `mvn test`            | Run tests                                 |
| `mvn package`         | Build the JAR                             |
| `mvn javadoc:javadoc` | Generate JavaDoc                          |
| `mvn site`            | Generate project site (including JavaDoc) |
