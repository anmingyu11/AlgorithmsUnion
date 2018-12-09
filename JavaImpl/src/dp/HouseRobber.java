package dp;

import java.util.Arrays;

import base.Base;

public class HouseRobber extends Base {

    // Mine
    static class Solution {
        public int rob(int[] nums) {
            final int n = nums.length;
            if (n == 0) {
                return 0;
            } else if (n == 1) {
                return nums[0];
            }
            int[] dp = new int[n];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[1], nums[0]);
            for (int i = 2; i < n; ++i) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[n - 1];
        }
    }

    // Recursive top-down
    static class Solution1 {
        public int rob(int[] nums) {
            return rob(nums, nums.length - 1);
        }

        private int rob(int[] nums, int i) {
            if (i < 0) {
                return 0;
            }

            return Math.max(rob(nums, i - 1), rob(nums, i - 2) + nums[i]);
        }
    }

    // Recursive + memo (top-down).
    static class Solution2 {

        private int[] memo;

        public int rob(int[] nums) {
            final int n = nums.length;
            memo = new int[n];
            Arrays.fill(memo, -1);
            return rob(nums, n - 1);
        }

        private int rob(int[] nums, int i) {
            if (i < 0) {
                return 0;
            }
            if (memo[i] >= 0) {
                return memo[i];
            }

            int res = Math.max(rob(nums, i - 1), rob(nums, i - 2) + nums[i]);
            memo[i] = res;
            return memo[i];
        }

    }

    // We can notice that in the previous step we use only memo[i] and memo[i-1], so going just 2 steps back.
    // We can hold them in 2 variables instead.
    // This optimization is met in Fibonacci sequence creation and some other problems [to paste links].
    static class Solution3 {
        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int prev1 = 0;
            int prev2 = 0;
            for (int num : nums) {
                int tmp = prev1;
                prev1 = Math.max(prev2 + num, prev1);
                prev2 = tmp;
            }
            return prev1;
        }

    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 1};
        int[] nums2 = new int[]{2, 7, 9, 3, 1};
        int[] nums3 = new int[]{2, 1, 1, 2};

        println(new Solution2().rob(nums1));
        println(new Solution2().rob(nums2));
        println(new Solution2().rob(nums3));
    }

}
