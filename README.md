# Auto-completion Library

A Java library for providing auto-completion of English words based on a Trie data structure.

## Features
- Fast prefix-based word completion
- Memory-efficient Trie implementation
- Support for word addition and removal
- Input validation for alphabetic-only words

## Compile and Run
Please follow these steps to compile the project and run the program:

1. Compile the project:
   ```bash
   ./gradlew build
   ```

2. Run the following command:
   ```bash
   java -jar build/libs/HW1-0.1.0.jar <option> <prefix>
   ```
   **Example**:
   ```bash
   java -jar build/libs/HW1-0.1.0.jar xylopho
   ```
   This will output all words that start with the prefix `xylopho`.

## Usage
### 1. Display Help Information
To display help information about the available commands and options, use:
```bash
java -jar build/libs/HW1-0.1.0.jar --help
```
### 2. Display Version Information
To check the version of the application, run:
```bash
java -jar build/libs/HW1-0.1.0.jar --version
```

### 3. Autocomplete with a Custom Dictionary
To use a custom dictionary file, run the following command:
```bash
java -jar build/libs/HW1-0.1.0.jar --dictionary path/to/your/custom_dictionary.txt <prefix>
```
Replace `path/to/your/custom_dictionary.txt` with the path to your custom dictionary file and `<prefix>` with the desired prefix.

### 4. Autocomplete with the Built-in Dictionary
If you want to use the built-in dictionary, simply run:
```bash
java -jar build/libs/HW1-0.1.0.jar <prefix>
```
This will use the default built-in dictionary located at `src/main/resources/words_alpha.txt`.

### 5. Combine Custom Dictionary with Built-in Dictionary
To combine a specified custom dictionary with the built-in dictionary, use the `--union` or `-u` option. This allows you to get autocompletion results from both dictionaries.

**Usage:**
```bash
java -jar build/libs/HW1-0.1.0.jar --dictionary path/to/your/custom_dictionary.txt --union <prefix>
```

**Example:**
```bash
java -jar build/libs/HW1-0.1.0.jar --dictionary src/main/resources/custom_words.txt --union xylo
```

In this example, the program will combine the completions from `custom_words.txt` with the built-in dictionary and return results for the prefix `xylo`.


## Adding as a Dependency
To use this library as a dependency in another Java project, add the following to your `build.gradle` file:

```groovy
dependencies {
    implementation 'com.Vincamine'
    autocomplete:'0.1.0'
}
```

## Development
To contribute to the development of this library, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/CS6510-SEA-SP25/hw1-Vincamine.git
   cd autocomplete
   ```

2. **Set up your development environment**:
   - Ensure you have Java JDK and Gradle installed.

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run tests**:
   ```bash
   ./gradlew test
   ```

5. **Generate documentation**:
   ```bash
   ./gradlew javadoc
   ```

6. **Generate test coverage reports**:
   ```bash
   ./gradlew jacocoTestReport
   ```

   Ensure your solution has a coverage of 70% or more for both line and branch coverage metrics.

7. **Generate coding style reports**:
   - If you are using a tool like Checkstyle, you can add the following to your `build.gradle`:
   ```groovy
   checkstyle {
       toolVersion = '8.45'
   }
   ```

   Then run:
   ```bash
   ./gradlew checkstyleMain
   ```

8. **Generate static analysis reports**:
   - You can use tools like PMD or SpotBugs. For example, to run SpotBugs:
   ```bash
   ./gradlew spotbugsMain
   ```

9. **Generate a JAR package**:
   ```bash
   ./gradlew jar
   ```

   The generated JAR file will be located in the `build/libs` directory.

## Building and Testing
   ```gradle
         dependencies {
            implementation 'io.github.Vincamine:autowordcomplete:0.1.0'
         }
   ```

   ```bash
   ./gradlew build        # Build project
   ./gradlew test         # Run tests
   ./gradlew jacocoTestReport  # Generate coverage report
   ./gradlew javadoc     # Generate documentation
   ```

## Published Maven Central package
[https://github.com/CS6510-SEA-SP25/hw1-Vincamine/packages/2381967](https://github.com/CS6510-SEA-SP25/hw1-Vincamine/packages/2381967)

```xml
<dependency>
  <groupId>io.github.Vincamine</groupId>
  <artifactId>autocomplete</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Usage Instructions for Docker Image

This document provides step-by-step instructions for using the Docker image containing the AutoComplete application.

### Prerequisites
- **Docker** must be installed on your machine.
  - [Download Docker](https://www.docker.com/products/docker-desktop/)
---

### Pulling the Docker Image
To get the latest version of the AutoComplete application, pull the Docker image from DockerHub:

```bash
docker pull wylliefang/ac:1.0
```

### Running the Application

```bash
docker run wylliefang/ac:1.0 <option> <prefix>
```

### Docker Image
You can find the published Docker image on DockerHub at the following link:
[DockerHub - Wyllie Fang's General Image](https://hub.docker.com/repository/docker/wylliefang/ac/general)
