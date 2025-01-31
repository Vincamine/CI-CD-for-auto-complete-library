package autowordcomplete;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.charset.StandardCharsets;


class AutoCompleteLibraryTest {
    private AutoCompleteLibrary library;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        testFile = File.createTempFile("words", ".txt");
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(testFile), StandardCharsets.UTF_8))) {
            writer.println("test");
            writer.println("testing");
            writer.println("tester");
        }
        library = AutoCompleteLibrary.create(testFile);
    }

    @AfterEach
    void tearDown() {
        if (!testFile.delete()) {
            System.err.println("Warning: Failed to delete temporary file: " + testFile.getAbsolutePath());
        }
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
