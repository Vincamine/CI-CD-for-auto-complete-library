# Auto-completion Library

A Java library for providing auto-completion of English words based on a Trie data structure.

## Features
- Fast prefix-based word completion
- Memory-efficient Trie implementation
- Support for word addition and removal
- Input validation for alphabetic-only words

## Usage
To use the Auto-completion Library, you can run the `AutoCompleteMain` class from the command line. Here's how to do it:

1. Ensure you have a text file containing a list of words (one word per line).
2. Compile the project and run the following command:

   ```bash
   java -cp build/libs/HW1-0.1.0.jar autowordcomplete.AutoCompleteMain <path_to_word_list> <prefix>
   ```

   Replace `<path_to_word_list>` with the path to your word list file and `<prefix>` with the prefix you want to complete.

   **Example**:
   ```bash
   java -cp build/libs/HW1-0.1.0.jar autowordcomplete.AutoCompleteMain src/main/resources/words.txt xylo
   ```

   This will output all words that start with the prefix `xylo`.
3. Add word API:
    To add a new word to the auto-completion library, you can use the `addWord` method provided by the `AutoCompleteLibrary` class. Here’s how to do it:
        1. Create an instance of `AutoCompleteLibrary` with the word list file.
        2. Call the `addWord` method with the word you want to add.

4. Remove word API
    To remove a word from the auto-completion library, you can use the `removeWord` method provided by the `AutoCompleteLibrary` class. Here’s how to do it:
        1. Create an instance of `AutoCompleteLibrary` with the word list file.
        2. Call the `removeWord` method with the word you want to remove.

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
