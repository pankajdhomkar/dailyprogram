package june2026.day_2026_06_21;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
Diffrent Approch
Create a basic sentence checker that takes in a stream of characters and determines
whether they form valid sentences. If a sentence is valid, the program should print it out.
We can consider a sentence valid if it conforms to the following rules:

1.The sentence must start with a capital letter, followed by a lowercase letter or a space.
2.All other characters must be lowercase letters, separators (,,;,:) or terminal marks (.,?,!,‽).
3.There must be a single space between each word.
4.The sentence must end with a terminal mark immediately following a word.
*/

/*Short answer in interview
* Since the input arrives as a character stream,
* I maintain a StringBuilder buffer. For every incoming character,
* I append it to the buffer.
* When I encounter a terminal mark such as '.', '?', '!' or '‽',
* I treat the buffer contents as a complete sentence.
* I then validate the sentence against all given rules:
* it must start with a capital letter, contain only allowed characters, have exactly one space between words,
* and end with a terminal mark immediately after a word. If the sentence is valid, I print it.
* Finally, I clear the buffer and continue processing the next sentence.
* This approach processes the stream in a single pass with O(n) time complexity and O(k) auxiliary space,
* where k is the length of the current sentence.
* */

public class CharacterStreamValidator {
    private static final Logger LOGGER = Logger.getLogger(CharacterStreamValidator.class.getName());
    private final StringBuilder buffer = new StringBuilder();

    public void accept(char ch) {

        buffer.append(ch);

        if (".?!".indexOf(ch) >= 0) {

            String sentence = buffer.toString();

            if (SentenceValidator.isValid(sentence)) {
                LOGGER.log(Level.INFO, "VALID: {0}", sentence);
            }

            buffer.setLength(0);
        }
    }

    static class SentenceValidator {

        static boolean isValid(String sentence) {
            if (sentence == null || sentence.isEmpty()) {
               return false;
            }

            int len = sentence.length();
            char first = sentence.charAt(0);
            char last = sentence.charAt(len - 1);

            if (len < 3 || !Character.isUpperCase(first) || ".?!‽".indexOf(last) == -1) {
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

    public static void main(String[] args) {

        CharacterStreamValidator stream =
                new CharacterStreamValidator();

        String input =
                "Hello world.This is valid!bad sentence.";

        input.chars()
                .mapToObj(c -> (char) c)
                .forEach(stream::accept);
    }
}
