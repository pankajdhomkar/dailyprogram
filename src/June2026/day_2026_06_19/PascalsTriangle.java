package June2026.day_2026_06_19;

import java.util.ArrayList;
import java.util.List;

/*
* Pascal's triangle is a triangular array of integers constructed with the following formula:

The first row consists of the number 1.
For each subsequent row, each element is the sum of the numbers directly above it, on either side.
For example, here are the first few rows:

    1
   1 1
  1 2 1
 1 3 3 1
1 4 6 4 1
Given an input k, return the kth row of Pascal's triangle.

Bonus: Can you do this using only O(k) space?
* Using Binomial Coefficient
 */
public class PascalsTriangle {

    public static List<Integer> getKthRow(int k) {

        List<Integer> result = new ArrayList<>();

        long value = 1;

        for (int r = 0; r <= k; r++) {

            result.add((int) value);

            value = value * (k - r) / (r + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getKthRow(5));
    }
}
