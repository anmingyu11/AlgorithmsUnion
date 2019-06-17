package _java;

import base.Base;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent
 * houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 * because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 */
public class _0213HouseRobber2 extends Base {

    private abstract static class Solution {
        public abstract int rob(int[] nums);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber II.
     * Memory Usage: 33.1 MB, less than 100.00% of Java online submissions for House Robber II.
     */
    private static class Solution1 extends Solution {

        @Override
        public int rob(int[] nums) {
            final int n = nums.length;
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return nums[0];
            }
            final int[] dp1 = new int[n + 1];
            final int[] dp2 = new int[n + 1];

            dp1[1] = nums[0];
            for (int i = 2; i <= n; ++i) {
                dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i - 1]);
                dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i - 1]);
            }

            return Math.max(dp1[n - 1], dp2[n]);
        }

    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber II.
     * Memory Usage: 33.5 MB, less than 100.00% of Java online submissions for House Robber II.
     * VeryClose
     */
    private static class Solution2 extends Solution {

        @Override
        public int rob(int[] nums) {
            final int n = nums.length;
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return nums[0];
            }
            return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
        }

        private int rob(int[] nums, int lo, int hi) {
            int prev1 = nums[lo], prev2 = 0;
            for (int i = lo + 1; i <= hi; ++i) {
                int cur = Math.max(prev1, prev2 + nums[i]);
                prev2 = prev1;
                prev1 = cur;
            }
            return Math.max(prev1, prev2);
        }
    }

    public static void main(String[] args) {
        int[] h1 = {2, 3, 2};
        int[] h2 = {1, 2, 3, 1};

        Solution s = new Solution2();

        println(s.rob(h1)); // 3
        println(s.rob(h2)); // 4
    }
}
