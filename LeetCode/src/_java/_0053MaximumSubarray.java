package _java;

import base.Base;

/**
 * Given an integer array nums,
 * find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution,
 * try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class _0053MaximumSubarray extends Base {

    private abstract static class Solution {
        public abstract int maxSubArray(int[] nums);
    }

    /**
     * Runtime: 1 ms, faster than 90.31% of Java online submissions for Maximum Subarray.
     * Memory Usage: 36.5 MB, less than 99.86% of Java online submissions for Maximum Subarray.
     * 瞎猫碰死耗子
     */
    private static class Solution1 extends Solution {

        public int maxSubArray(int[] nums) {
            final int n = nums.length;
            int sum = nums[0];
            int res = nums[0];
            int dp = Math.min(0, nums[0]);
            for (int i = 1; i < n; ++i) {
                sum += nums[i];
                res = Math.max(res, sum - dp);
                dp = Math.min(dp, sum);
            }
            return res;
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public int maxSubArray(int[] nums) {
            final int n = nums.length;
            int maxSum = nums[0];
            for (int i = 1; i < n; ++i) {
                if (nums[i - 1] > 0) {
                    nums[i] += nums[i - 1];
                }
                maxSum = Math.max(maxSum, nums[i]);
            }
            return maxSum;
        }
    }

    /**
     * greedy
     */
    private static class Solution3 extends Solution {

        @Override
        public int maxSubArray(int[] nums) {
            final int n = nums.length;
            int sum = nums[0], max = nums[0];
            for (int i = 1; i < n; ++i) {
                sum = Math.max(nums[i], nums[i] + sum);
                max = Math.max(max, sum);
            }
            return max;
        }
    }

    /**
     * Runtime: 1 ms, faster than 61.46% of Java online submissions for Maximum Subarray.
     * Memory Usage: 37.1 MB, less than 99.53% of Java online submissions for Maximum Subarray.
     */
    private static class Solution4 extends Solution {

        public int maxSubArray(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        private int helper(int[] A, int lo, int hi) {
            if (lo == hi) {
                return A[lo];
            }
            int partition = (lo + hi) / 2;
            int left = helper(A, lo, partition);
            int right = helper(A, partition + 1, hi);
            int cross = cross(A, lo, hi, partition);
            return Math.max(cross, Math.max(left, right));
        }

        private int cross(int[] A, int lo, int hi, int partition) {
            if (lo == hi) {
                return A[lo];
            }
            int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = partition; i >= lo; --i) {
                sum += A[i];
                left = Math.max(sum, left);
            }
            sum = 0;
            for (int i = partition + 1; i <= hi; ++i) {
                sum += A[i];
                right = Math.max(right, sum);
            }
            return left + right;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] a2 = {1, 2};
        int[] a3 = {-2, 1};
        int[] a4 = {-2, -1};
        int[] a5 = {1};

        Solution s = new Solution1();

        println(s.maxSubArray(a1));// 6
        println(s.maxSubArray(a2));// 3
        println(s.maxSubArray(a3));// 1
        println(s.maxSubArray(a4));// -1
        println(s.maxSubArray(a5));// 1
    }
}
