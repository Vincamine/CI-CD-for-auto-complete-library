package autowordcomplete;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @Test
    void testAddAndFindWord() {
        trie.addWord("test");
        List<String> completions = trie.getAutoCompletions("te");
        assertEquals(1, completions.size());
        assertEquals("test", completions.get(0));
    }

    @Test
    void testRemoveWord() {
        trie.addWord("test");
        assertTrue(trie.removeWord("test"));
        assertTrue(trie.getAutoCompletions("test").isEmpty());
    }

    @Test
    void testMultipleWords() {
        String[] words = {"test", "testing", "tester"};
        for (String word : words) {
            trie.addWord(word);
        }
        List<String> completions = trie.getAutoCompletions("test");
        assertEquals(3, completions.size());
        assertTrue(completions.containsAll(Arrays.asList(words)));
    }

    @Test
    void testInvalidWord() {
        assertThrows(IllegalArgumentException.class, () -> trie.addWord("test123"));
    }
}