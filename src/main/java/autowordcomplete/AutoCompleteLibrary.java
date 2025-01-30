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
     * Initializes the library with words from the provided file.
     * @param wordListFile file containing the word list
     * @throws IOException if there's an error reading the file
     */
    public AutoCompleteLibrary(File wordListFile) throws IOException {
        trie = new Trie();
        builtInDictionary = new HashSet<>();
        loadWords(wordListFile);
    }

    private void loadWords(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("[a-zA-Z]+")) {
                    String word = line.trim();
                    if (!word.isEmpty() && word.matches("[a-zA-Z]+")) {
                        if (builtInDictionary.add(word)) {
                            trie.addWord(word);
                        }
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
        try (BufferedReader br = new BufferedReader(new FileReader(wordList))) {
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