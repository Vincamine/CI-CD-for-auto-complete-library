package autowordcomplete;


import java.util.*;

/**
 * A Trie data structure implementation for efficient word storage and retrieval.
 */
public class Trie {
    private TrieNode root;

    /**
     * Initializes an empty Trie.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Adds a word to the Trie.
     * @param word the word to add
     * @throws IllegalArgumentException if word contains non-alphabetic characters
     */
    public void addWord(String word) {
        if (!word.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Word must contain only alphabetic characters");
        }

        TrieNode current = root;
        String lowercaseWord = word.toLowerCase();

        for (char c : lowercaseWord.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
        current.word = lowercaseWord;
    }

    /**
     * Removes a word from the Trie.
     * @param word the word to remove
     * @return true if the word was found and removed, false otherwise
     */
    public boolean removeWord(String word) {
        return removeWord(root, word.toLowerCase(), 0);
    }

    private boolean removeWord(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            current.word = null;
            return true;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = removeWord(node, word, index + 1);

        if (shouldDeleteCurrentNode && !node.isEndOfWord && node.children.isEmpty()) {
            current.children.remove(ch);
        }

        return shouldDeleteCurrentNode;
    }

    /**
     * Finds all words that start with the given prefix.
     * @param prefix the prefix to search for
     * @return list of words that start with the prefix
     */
    public List<String> getAutoCompletions(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode current = root;
        String lowercasePrefix = prefix.toLowerCase();

        // Navigate to the last node of the prefix
        for (char c : lowercasePrefix.toCharArray()) {
            TrieNode node = current.children.get(c);
            if (node == null) {
                return results;
            }
            current = node;
        }

        // Collect all words starting from this node
        collectWords(current, results);
        return results;
    }

    private void collectWords(TrieNode node, List<String> results) {
        if (node.isEndOfWord) {
            results.add(node.word);
        }

        for (TrieNode child : node.children.values()) {
            collectWords(child, results);
        }
    }

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        String word;

        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
            word = null;
        }
    }
}