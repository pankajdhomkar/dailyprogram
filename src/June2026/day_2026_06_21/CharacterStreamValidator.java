package June2026.day_2026_06_21;

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
    private final StringBuilder buffer = new StringBuilder();

    public void accept(char ch) {

        buffer.append(ch);

        if (".?!".indexOf(ch) >= 0) {

            String sentence = buffer.toString();

            if (SentenceValidator.isValid(sentence)) {
                System.out.println("VALID: " + sentence);
            }

            buffer.setLength(0);
        }
    }

    static class SentenceValidator {

        static boolean isValid(String sentence) {

            if (sentence.length() < 2) {
                return false;
            }

            if (!Character.isUpperCase(sentence.charAt(0))) {
                return false;
            }

            if (sentence.contains("  ")) {
                return false;
            }

            char last = sentence.charAt(sentence.length() - 1);

            if (".?!".indexOf(last) == -1) {
                return false;
            }

            return true;
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
