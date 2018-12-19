package dp;

import base.Base;

/**
 */
public class MaximumSubarray extends Base {

    static class Solution1 {

        // 更有点像贪心
        public int maxSubArray(int[] nums) {
            final int n = nums.length;
            int[] dp = new int[n];
            int max = nums[0];
            dp[0] = nums[0];

            for (int i = 1; i < n; ++i) {
                dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
                max = Math.max(max, dp[i]);
            }

            return max;

        }

    }

    static class Solution2 {

        public int maxSubArray(int[] nums) {
            final int n = nums.length;

            int prev = nums[0];
            int max = nums[0];

            for (int i = 1; i < n; ++i) {
                prev = Math.max(nums[i] + prev, nums[i]);
                max = Math.max(max, prev);
            }

            return max;
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = new int[]{1, 2};
        println(new Solution1().maxSubArray(nums));
        println(new Solution1().maxSubArray(nums2));
    }

}
