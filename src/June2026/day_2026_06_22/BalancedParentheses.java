package june2026.day_2026_06_22;

import java.util.logging.Logger;
import java.util.logging.Level;

/*Given a string of parentheses, find the balanced string that can be produced from it
using the minimum number of insertions and deletions.
If there are multiple solutions, return any of them.
For example, given "(()", you could return "(())". Given "))()(", you could return "()()()()".
*/
public class BalancedParentheses {
    private static final Logger LOGGER = Logger.getLogger(BalancedParentheses.class.getName());

    //Using basic java code
    public static String balance(String s) {
        StringBuilder result = new StringBuilder();
        int openCount = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                result.append(ch);
                openCount++;
            } else { // ch == ')'
                if (openCount > 0) {
                    result.append(ch);
                    openCount--;
                } else {
                    // Insert missing '('
                    result.append('(').append(')');
                }
            }
        }

        // Add missing closing brackets
        while (openCount > 0) {
            result.append(')');
            openCount--;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, () -> balance("(()"));      // (())
        LOGGER.log(Level.INFO, () -> balance("))()("));    // ()()()
        LOGGER.log(Level.INFO, () -> balance("((("));      // ((()))
        LOGGER.log(Level.INFO, () -> balance("()))"));     // ()(())
    }

}

