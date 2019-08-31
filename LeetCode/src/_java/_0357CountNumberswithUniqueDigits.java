package _java;

import base.Base;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * <p>
 * Example:
 * <p>
 * Input: 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding 11,22,33,44,55,66,77,88,99
 */
public class _0357CountNumberswithUniqueDigits extends Base {

    private abstract static class Solution {
        public abstract int countNumbersWithUniqueDigits(int n);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Numbers with Unique Digits.
     * Memory Usage: 33.1 MB, less than 9.15% of Java online submissions for Count Numbers with Unique Digits.
     */
    private static class Solution1 extends Solution {

        @Override
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return 10;
            }
            int dp = 10, r = 9, base = 9;
            for (int i = 0; i < n - 1; ++i) {
                r *= base - i;
                dp += r;
            }
            return dp;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.countNumbersWithUniqueDigits(2)); // 91
        println(s.countNumbersWithUniqueDigits(3)); // 739
    }

}
