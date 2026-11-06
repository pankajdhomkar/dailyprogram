package june2026.day_2026_06_21;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Create a basic sentence checker that takes in a stream of characters and determines
whether they form valid sentences. If a sentence is valid, the program should print it out.
We can consider a sentence valid if it conforms to the following rules:

1.The sentence must start with a capital letter, followed by a lowercase letter or a space.
2.All other characters must be lowercase letters, separators (,,;,:) or terminal marks (.,?,!,‽).
3.There must be a single space between each word.
4.The sentence must end with a terminal mark immediately following a word.
*/
public class SentenceChecker {
    private static final Logger LOGGER = Logger.getLogger(SentenceChecker.class.getName());

    private SentenceChecker() {
    }

    public static void main(String[] args) {

        List<String> sentences = List.of(
                "Hello world.",
                "This is valid!",
                "bad sentence.",
                "Hello  world.",
                "Hello world ?"
        );

        sentences.stream()
                .filter(SentenceChecker::isValidSentence)
                .forEach(sentence -> {
                    if (LOGGER.isLoggable(Level.INFO)) {
                        LOGGER.log(Level.INFO, "{0}", sentence);
                    }
                });
    }

    private static boolean isValidSentence(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
           return false;
        }

        int len = sentence.length();
        char first = sentence.charAt(0);
        char last = sentence.charAt(len - 1);

        if (len < 3 || !Character.isUpperCase(first) || ".?!‽".indexOf(last) < 0) {
           return false;
        }

        if (sentence.contains("  ") || sentence.startsWith(" ") || sentence.endsWith(" ")) {
           return false;
        }

        if (!Character.isLowerCase(sentence.charAt(1)) && sentence.charAt(1) != ' ') {
           return false;
        }

        for (int i = 1; i < len - 1; i++) {
           char ch = sentence.charAt(i);
           if (!Character.isLowerCase(ch) && ch != ' ' && ch != ',' && ch != ';' && ch != ':') {
               return false;
           }
        }

        char beforeTerminal = sentence.charAt(len - 2);
        return Character.isLowerCase(beforeTerminal);
    }
}
