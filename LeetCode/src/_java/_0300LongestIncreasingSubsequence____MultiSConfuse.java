package _java;

import java.util.Arrays;

import base.Base;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * Example:
 * <p>
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * <p>
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class _0300LongestIncreasingSubsequence____MultiSConfuse extends Base {

    private abstract static class Solution {
        public abstract int lengthOfLIS(int[] nums);
    }

    private static class Solution1 extends Solution {

        @Override
        public int lengthOfLIS(int[] nums) {
            final int n = nums.length;
            if (n < 1) {
                return 0;
            }
            if (n < 2) {
                return 1;
            }
            int max = 0;
            int[] dp = new int[n];
            dp[n - 1] = 1;
            for (int i = n - 2; i >= 0; --i) {
                for (int j = i + 1; j < n; ++j) {
                    int cur = nums[j] > nums[i] ? 1 + dp[j] : 1;
                    dp[i] = Math.max(dp[i], cur);
                    max = Math.max(dp[i], max);
                }
            }
            return max;
        }
    }

    private static class Solution2 extends Solution {

        @Override
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
        }

    }

    public static void main(String[] args) {
        int[] lis = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] lis2 = {10, 9, 2, 5, 3, 4};

        Solution s = new Solution2();
        //println(s.lengthOfLIS(lis)); // 4
        println(s.lengthOfLIS(lis2)); // 3
    }
}
