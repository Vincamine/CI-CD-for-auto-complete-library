package autowordcomplete;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Main class for the Auto-completion Library.
 */
public class AutoCompleteLibrary {
    private final Trie trie;
    private Set<String> builtInDictionary;
    
    /**
     * Constructs an AutoCompleteLibrary instance and loads words from the specified file.
     *
     * @param wordListFile the file containing words to be loaded
     * @throws IllegalArgumentException if the file is null
     * @throws IOException if an error occurs while reading the file
     */
    private AutoCompleteLibrary(Trie trie, HashSet<String> builtInDictionary) {
        this.trie = trie;
        this.builtInDictionary = builtInDictionary;
    }

    /**
     * Factory method to create an AutoCompleteLibrary instance.
     *
     * @param wordListFile the file containing words to be loaded
     * @return an instance of AutoCompleteLibrary
     * @throws IllegalArgumentException if the file is null
     * @throws IOException if an error occurs while reading the file
     */
    public static AutoCompleteLibrary create(File wordListFile) throws IOException {
        if (wordListFile == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        Trie trie = new Trie();
        HashSet<String> builtInDictionary = new HashSet<>();

        // Load words from the file
        try {
            loadWords(wordListFile, trie, builtInDictionary);
        } catch (IOException e) {
            System.err.println("Error loading words from file: " + e.getMessage());
            throw new IOException("Failed to initialize AutoCompleteLibrary", e);
        }

        return new AutoCompleteLibrary(trie, builtInDictionary);
    }

    /**
     * Loads words from the specified file into the built-in dictionary.
     *
     * @param file the file containing words to be loaded
     * @param trie the Trie to add words to
     * @param builtInDictionary the HashSet to store the words
     * @throws IOException if an error occurs while reading the file
     */
    private static void loadWords(File file, Trie trie, HashSet<String> builtInDictionary) throws IOException {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("[a-zA-Z]+")) {
                    String word = line.trim();
                    if (!word.isEmpty() && word.matches("[a-zA-Z]+")) {
                        builtInDictionary.add(word);
                        trie.addWord(word);
                    }
                }
            }
        }
    }

    /**
     * Validates and trims the word.
     * @param word the word to validate
     * @return the trimmed word if valid
     * @throws IllegalArgumentException if the word is invalid
     */
    private String validateAndTrimWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Invalid word. Word cannot be null.");
        }
        String trimmedWord = word.trim();
        if (trimmedWord.isEmpty() || !trimmedWord.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid word. Only non-empty alphabetic characters are allowed.");
        }
        return trimmedWord;
    }

    /**
     * Adds a new word to the dictionary.
     * @param word the word to add
     */
    public void addWord(String word) {
        String validWord = validateAndTrimWord(word);
        trie.addWord(validWord);
        builtInDictionary.add(validWord);
    }

    /**
     * Adds words from the provided dictionary file to the built-in dictionary.
     * @param wordList the file containing words to add
     */
    public void addToBuiltInDictionary(File wordList) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(wordList), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String validWord = validateAndTrimWord(line);
                builtInDictionary.add(validWord);
                trie.addWord(validWord);
            }
        } catch (IOException e) {
            System.err.println("Error adding words to built-in dictionary: " + e.getMessage());
        }
    }

    /**
     * Removes a word from the dictionary.
     * @param word the word to remove
     * @return true if the word was found and removed
     */
    public boolean removeWord(String word) {
        return trie.removeWord(word);
    }

    /**
     * Gets all possible completions for a given prefix.
     * @param prefix the prefix to complete
     * @return list of possible completions
     */
    public List<String> getCompletions(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return Collections.emptyList();
        }
        return trie.getAutoCompletions(prefix);
    }
}