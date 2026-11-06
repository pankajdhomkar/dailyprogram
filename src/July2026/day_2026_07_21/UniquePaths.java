package july2026.day_2026_07_21;


import java.util.logging.Level;
import java.util.logging.Logger;

/*
* This problem was asked by Facebook.
* There is an N by M matrix of zeroes. Given N and M, write a function to count the number of ways of starting
*  at the top-left corner and getting to the bottom-right corner.
*  You can only move right or down.
For example, given a 2 by 2 matrix, you should return 2, since there are two ways to get to the bottom-right:
- Right, then down
- Down, then right
Given a 5 by 5 matrix, there are 70 ways to get to the bottom-right.
 */

/*
* Interviewer: How would you solve this problem?

Your answer (2-minute version):

"We are given an N × M matrix, and we need to find the number of unique paths from the top-left corner to the bottom-right corner.

Since we can only move right or down, each cell can be reached from two possible directions:
1. The cell above it.
2. The cell to its left.
So, we can use Dynamic Programming.

The recurrence relation is:
\[
dp[i][j] = dp[i-1][j] + dp[i][j-1]
\]

I initialize the first row and first column with 1 because there is only one way to reach those cells.

To optimize space complexity, instead of using a two-dimensional array, I use a one-dimensional array.

The time complexity is O(N × M), and the space complexity is O(M)."
Round 2: Your turn
Interviewer: Why are you using Dynamic Programming instead of recursion?

Dynamic Programming avoids recalculating overlapping subproblems and improves efficiency.
*
* */






public class UniquePaths {

    private static final Logger LOGGER = Logger.getLogger(UniquePaths.class.getName());

    public static long countPaths(int rows, int cols) {

        if (rows <= 0 || cols <= 0) {
            return 0;
        }

        long[] dp = new long[cols];

        java.util.Arrays.fill(dp, 1);

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }

        return dp[cols - 1];
    }

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "{0}", countPaths(2, 2));
        LOGGER.log(Level.INFO, "{0}", countPaths(5, 5));
        LOGGER.log(Level.INFO, "{0}", countPaths(3, 7));
    }
}
