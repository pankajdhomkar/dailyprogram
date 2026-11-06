package march2026.day_2026_03_07;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Given an array of numbers and a number k, determine if there are three entries in the array which add up to the specified number k.
 * For example, given [20, 303, 3, 4, 25] and k = 49, return true as 20 + 4 + 25 = 49
 * Sort the array.
Fix one number.
Search for the remaining two numbers using two pointers.
If the sum is too small, move the left pointer right.
If the sum is too large, move the right pointer left.
If the sum equals k, return true.
If all possibilities are checked, return false.
 *
 */

import java.util.Arrays;

public class ThreeNumberAddition {
    private static final Logger LOGGER = Logger.getLogger(ThreeNumberAddition.class.getName());

    private ThreeNumberAddition() {
    }

    public static boolean hasThreeSum(int[] arr, int k) {
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++){
            int left = i + 1;
            int right = arr.length - 1;

            while(left < right){
                int sum = arr[i] + arr[left] + arr[right];
                if(sum == k){
                    return true;
                }else if(sum > k){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (LOGGER.isLoggable(Level.INFO)) {
            int[] arr = {20, 303, 3, 4, 25};
            int k = 49;

            LOGGER.log(Level.INFO, "{0}", hasThreeSum(arr, k));
        }
    }
}


