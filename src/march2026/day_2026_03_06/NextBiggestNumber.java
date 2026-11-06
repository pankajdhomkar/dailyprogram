package march2026.day_2026_03_06;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Given an integer n, find the next biggest integer with the same number of 1-bits on.
 * For example, given the number 6 (0110 in binary), return 9 (1001).
 */
public class NextBiggestNumber {
    private static final Logger LOGGER = Logger.getLogger(NextBiggestNumber.class.getName());

    private NextBiggestNumber() {
    }

    public static int nextNumber(int n) {
        int c = n;
        int c0 = 0;
        int c1 = 0;

        // count trailing zeros
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // count ones after trailing zeros
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // no bigger number possible
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }

        int p = c0 + c1;

        // Step 1: flip rightmost non-trailing zero
        n |= (1 << p);

        // Step 2: clear bits to the right
        n &= ~((1 << p) - 1);

        // Step 3: add (c1 - 1) ones
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }

    public static void main(String[] args) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, "{0}", nextNumber(6)); // 9
            LOGGER.log(Level.INFO, "{0}", nextNumber(9)); // 10
            LOGGER.log(Level.INFO, "{0}", nextNumber(12)); // 17
        }
    }
}
