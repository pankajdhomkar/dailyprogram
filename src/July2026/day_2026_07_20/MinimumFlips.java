package july2026.day_2026_07_20;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
* This problem was asked by LinkedIn.
* You are given a string consisting of the letters x and y, such as xyxxxyxyy.
*  In addition, you have an operation called flip, which changes a single x to y or vice versa.
Determine how many times you would need to apply this operation to ensure that all x's come before all y's.
* In the preceding example, it suffices to flip the second and sixth characters, so you should return 2
* */
public class MinimumFlips {

    private static final Logger LOGGER = Logger.getLogger(MinimumFlips.class.getName());

    public static int minFlips(String input) {

        int flipsToY = 0;
        int flipsToX = 0;

        for (char ch : input.toCharArray()) {

            if (ch == 'x') {
                flipsToY = Math.min(flipsToY + 1, flipsToX);
            } else if (ch == 'y') {
                flipsToY++;
            } else {
                throw new IllegalArgumentException(
                        "Input must contain only x and y"
                );
            }
        }

        return flipsToY;
    }

    public static void main(String[] args) {

        String input = "xyxxxyxyy";

        LOGGER.log(Level.INFO, "Minimum flips: {0}", minFlips(input));
    }
}
