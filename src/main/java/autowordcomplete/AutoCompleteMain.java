package autowordcomplete;

import java.io.File;
import java.util.List;

/**
 * The AutoCompleteMain class provides a command-line interface for the 
 * auto-completion functionality. It reads a list of words from a specified 
//  * file and returns possible completions for a given prefix.
 * 
 * <p>This class uses a default constructor.</p>
 */
public class AutoCompleteMain {
    /**
     * Default constructor for AutoCompleteMain.
     * Initializes the main class for running the application.
     */
    public AutoCompleteMain() {
        // Default constructor
    }

    /**
     * The main method is the entry point of the application.
     * 
     * @param args Command-line arguments. The first argument should be the 
     *             path to the word list file, and the second argument should 
     *             be the prefix for which completions are requested.
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                printHelp();
                return;
            }

            String dictionaryFile = null;
            boolean union = false;
            String prefix = null;

            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "--help":
                        printHelp();
                        return;
                    case "--version":
                        printVersion();
                        return;
                    case "--dictionary":
                    case "-d":
                        if (i + 1 < args.length) {
                            dictionaryFile = args[++i];
                        } else {
                            System.err.println("Error: Missing filename for --dictionary option.");
                            return;
                        }
                        break;
                    case "--union":
                    case "-u":
                        union = true;
                        break;
                    default:
                        if (prefix == null) {
                            prefix = args[i];
                        } else {
                            System.err.println("Error: Too many arguments.");
                            return;
                        }
                        break;
                }
            }

            if (prefix == null) {
                System.out.println("Usage: java AutoCompleteMain <prefix>");
                return;
            }

            if (dictionaryFile != null) {
                File wordList = new File(dictionaryFile);
                AutoCompleteLibrary acl = new AutoCompleteLibrary(wordList);
                if (union) {
                    acl.addToBuiltInDictionary(wordList);
                }
                List<String> completions = acl.getCompletions(prefix);
                System.out.print("OUTPUT ");
                System.out.println(String.join(" ", completions));
            } else {
                System.out.println("Error: Dictionary file is required.");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Usage: java AutoCompleteMain <options> <prefix>");
        System.out.println("--help          Print this help message.");
        System.out.println("--version       Print the program version.");
        System.out.println("--dictionary|-d <filename>  Use the specified dictionary file.");
        System.out.println("--union|-u     Combine the specified dictionary with the built-in dictionary.");
    }

    private static void printVersion() {
        System.out.println("AutoCompleteLibrary version 1.0");
    }
}