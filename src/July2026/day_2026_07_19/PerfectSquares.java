package july2026.day_2026_07_19;

/*
* Good morning! Here's your coding interview problem for today.
This problem was asked by Uber.
Write a program that determines the smallest number of perfect squares that sum up to N.
Here are a few examples:
- Given N = 4, return 1 (4)
- Given N = 17, return 2 (16 + 1)
- Given N = 18, return 2 (9 + 9)
*/

import java.util.logging.Logger;
import java.util.stream.IntStream;

public class PerfectSquares {

    private static final Logger logger = Logger.getLogger(PerfectSquares.class.getName());
    // Approach 1: Traditional Dynamic Programming
    public static int minSquaresDP(int n) {

        if (n < 0) {
            throw new IllegalArgumentException(
                    "N must be non-negative"
            );
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            dp[i] = i;

            for (int j = 1; j * j <= i; j++) {

                int square = j * j;

                dp[i] = Math.min(
                        dp[i],
                        1 + dp[i - square]
                );
            }
        }

        return dp[n];
    }

    // Approach 2: Java Streams + Dynamic Programming
    public static int minSquaresStream(int n) {

        if (n < 0) {
            throw new IllegalArgumentException(
                    "N must be non-negative"
            );
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            int finalI = i;
            dp[i] = IntStream.rangeClosed(
                            1, (int) Math.sqrt(i)
                    )
                    .map(j -> j * j)
                    .map(square -> 1 + dp[finalI - square])
                    .min()
                    .orElse(i);
        }

        return dp[n];
    }

    public static void main(String[] args) {

        int[] testCases = {4, 17, 18, 23, 100};

        for (int n : testCases) {

            logger.info("N = " + n);

            logger.info(
                    "Traditional DP: " + minSquaresDP(n)
            );

            logger.info(
                    "Java Streams:   " + minSquaresStream(n)
            );

            logger.info("--------------------");
        }
    }
}
