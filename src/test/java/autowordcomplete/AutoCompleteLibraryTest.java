package autowordcomplete;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AutoCompleteLibraryTest {
    private AutoCompleteLibrary library;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        testFile = File.createTempFile("words", ".txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("test");
            writer.println("testing");
            writer.println("tester");
        }
        library = new AutoCompleteLibrary(testFile);
    }

    @AfterEach
    void tearDown() {
        testFile.delete();
    }

    @Test
    void testInitialization() {
        List<String> completions = library.getCompletions("test");
        assertEquals(3, completions.size());
    }

    @Test
    void testAddWord() {
        library.addWord("temporary");
        List<String> completions = library.getCompletions("tem");
        assertEquals(1, completions.size());
        assertEquals("temporary", completions.get(0));
    }
    @Test
    public void testAddInvalidWord() {
        assertThrows(IllegalArgumentException.class, () -> {
            library.addWord("invalid123");
        });
    }

    @Test
    void testRemoveWord() {
        assertTrue(library.removeWord("test"));
        List<String> completions = library.getCompletions("test");
        assertEquals(2, completions.size());
    }

    @Test
    void testGetCompletionsWithEmptyPrefix() {
        List<String> completions = library.getCompletions("");
        assertTrue(completions.isEmpty(), "Expected empty list for empty prefix");
    }

    @Test
    void testGetCompletionsWithNullPrefix() {
        List<String> completions = library.getCompletions(null);
        assertTrue(completions.isEmpty(), "Expected empty list for null prefix");
    }
}
