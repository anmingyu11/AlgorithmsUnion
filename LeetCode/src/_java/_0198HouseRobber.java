package _java;

import base.Base;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them
 * is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class _0198HouseRobber extends Base {

    private abstract static class Solution {
        public abstract int rob(int[] nums);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
     * Memory Usage: 33.4 MB, less than 100.00% of Java online submissions for House Robber.
     */
    private static class Solution1 extends Solution {

        @Override
        public int rob(int[] nums) {
            final int n = nums.length;
            if (n <= 0) {
                return 0;
            }
            int[] dp = new int[n];
            dp[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                int next = nums[i];
                if (i + 2 <= n - 1) {
                    next += dp[i + 2];
                }
                dp[i] = Math.max(dp[i + 1], next);
            }
            return dp[0];
        }

    }

    /**
     * dp optimized
     */
    private static class Solution2 extends Solution {

        @Override
        public int rob(int[] nums) {
            final int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int prev1 = nums[n - 1];
            int prev2 = 0;
            for (int i = n - 2; i >= 0; --i) {
                int cur = Math.max(prev1, prev2 + nums[i]);
                prev2 = prev1;
                prev1 = cur;
            }
            return Math.max(prev1, prev2);
        }

    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 1};
        int[] a2 = {2, 7, 9, 3, 1};
        int[] a3 = {1, 3, 1};
        int[] a4 = {2, 1, 1, 2};
        int[] a5 = {1, 2};
        int[] a6 = {3};
        Solution s = new Solution2();
        println(s.rob(a1)); // 4
        println(s.rob(a2)); // 12
        println(s.rob(a3)); // 3
        println(s.rob(a4)); // 4
        println(s.rob(a5)); // 2
        println(s.rob(a6)); // 3
    }
}
