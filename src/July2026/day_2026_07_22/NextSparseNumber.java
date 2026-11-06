package july2026.day_2026_07_22;

import java.util.logging.Level;
import java.util.logging.Logger;
/*
* Good morning! Here's your coding interview problem for today.
This problem was asked by Oracle.
We say a number is sparse if there are no adjacent ones in its binary representation.
For example, 21 (10101) is sparse, but 22 (10110) is not.
For a given input N, find the smallest sparse number greater than or equal to N.
Do this in faster than O(N log N) time.
* */
public class NextSparseNumber {
    private static final Logger LOGGER = Logger.getLogger(NextSparseNumber.class.getName());

    private NextSparseNumber() {
    }

    public static long nextSparse(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("N must be non-negative");
        }

        if (n == 0) {
            return 0;
        }

        long candidate = n;

        while (true) {
            long previous = 0;
            boolean isSparse = true;

            for (int bitIndex = 0; bitIndex < 64; bitIndex++) {
                long bit = (candidate >> bitIndex) & 1L;
                if (bit == 1 && previous == 1) {
                    isSparse = false;
                    break;
                }
                previous = bit;
            }

            if (isSparse) {
                return candidate;
            }

            candidate++;
        }
    }

    public static void main(String[] args) {

        long[] numbers = {21, 22, 23, 24, 26, 38, 42};

        for (long n : numbers) {
            LOGGER.log(Level.INFO, "{0} -> {1}", new Object[]{n, nextSparse(n)});
        }
    }
}