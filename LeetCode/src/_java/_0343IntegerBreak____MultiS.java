package _java;

import base.Base;

/**
 * Given a positive integer n,
 * break it into the sum of at least two positive integers and maximize the product of those integers.
 * Return the maximum product you can get.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 * <p>
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class _0343IntegerBreak____MultiS extends Base {

    private abstract static class Solution {
        public abstract int integerBreak(int n);
    }

    /**
     * Runtime: 1 ms, faster than 51.64% of Java online submissions for Integer Break.
     * Memory Usage: 33.1 MB, less than 5.13% of Java online submissions for Integer Break.
     */
    private static class Solution1 extends Solution {

        @Override
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[2] = 1;
            for (int i = 3; i <= n; ++i) {
                for (int j = 1; j < i; ++j) {
                    dp[i] = Math.max(dp[i],
                            Math.max(j, dp[j]) // 分解还是不分解
                                    * Math.max(i - j, dp[i - j]));
                }
            }
            return dp[n];
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.integerBreak(2)); // 1
        println(s.integerBreak(10)); // 36
    }
}
