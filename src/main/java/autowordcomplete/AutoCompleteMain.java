package autowordcomplete;

import java.io.File;
import java.util.ArrayList;
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
            String defaultDictionaryFile = "src/main/resources/words_alpha.txt";

            String prefix = null;
            boolean union = false;
            boolean userDic = false;

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
                        userDic = true;
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
                printHelp();
                return;
            }

            List<String> allWords = new ArrayList<>();

            if(!union && !userDic){
                File defaultWordList = new File(defaultDictionaryFile);
                AutoCompleteLibrary defaultAcl = AutoCompleteLibrary.create(defaultWordList);
                allWords = defaultAcl.getCompletions(prefix);
            } else {
                if(!union){
                    if(dictionaryFile == null){
                        System.out.println("Using -d|-dictionary but no dictionary found");
                        return;
                    }
                    File userWordList = new File(dictionaryFile);
                    AutoCompleteLibrary userAcl = AutoCompleteLibrary.create(userWordList);

                    allWords = userAcl.getCompletions(prefix);
                } else if(union && userDic) {
                    // union branch
                    File defaultWordList = new File(defaultDictionaryFile);
                    AutoCompleteLibrary defaultAcl = AutoCompleteLibrary.create(defaultWordList);
                    List<String> defaultCompletions = defaultAcl.getCompletions(prefix);
                    File userWordList = new File(dictionaryFile);
                    AutoCompleteLibrary userAcl = AutoCompleteLibrary.create(userWordList);
                    List<String> userCompletions = userAcl.getCompletions(prefix);
    
                    allWords = new ArrayList<>(userCompletions);
                    allWords.addAll(defaultCompletions);
                } else {
                    System.out.println("invalid option 'union'\n");
                    printHelp();
                    return;
                }
            }

            System.out.print("OUTPUT ");
            System.out.println(String.join(" ", allWords));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints the usage information for the AutoCompleteMain application.
     * This method displays the command-line options available to the user,
     * including how to use the application and the purpose of each option.
     */
    private static void printHelp() {
        System.out.println("Usage: java AutoCompleteMain <options> <prefix>");
        System.out.println("--help          Print this help message.");
        System.out.println("--version       Print the program version.");
        System.out.println("--dictionary|-d <filename>  Use the specified dictionary file.");
        System.out.println("--union|-u     Combine the specified dictionary with the built-in dictionary. This options is only valid when --dictionary option is also given");
    }
    
    /**
     * Prints the version information for the AutoCompleteLibrary application.
     * This method displays the current version of the application to the user.
     */
    private static void printVersion() {
        System.out.println("AutoCompleteLibrary version 1.0");
    }
}