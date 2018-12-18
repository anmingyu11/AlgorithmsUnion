package array;

import base.Base;

public class MaximumAverageSubarray extends Base {

    static class Solution1 {

        public static double findMaxAverage(int[] nums, int k) {
            final int len = nums.length;
            int[] sums = new int[len];
            sums[0] = nums[0];
            for (int i = 1; i < len; ++i) {
                sums[i] = sums[i - 1] + nums[i];
            }
            double av = sums[k - 1] * 1.0 / k;
            for (int i = k; i < len; ++i) {
                av = Math.max((sums[i] - sums[i - k]) * 1.0 / k, av);
            }

            return av;
        }

    }

    static class Solution2 {

        public static double findMaxAverage(int[] nums, int k) {
            final int len = nums.length;
            double sum = 0;
            for (int i = 0; i < k; ++i) {
                sum += nums[i];
            }
            double maxSum = sum;
            for (int i = k; i < len; ++i) {
                sum += nums[i] - nums[i - k];
                maxSum = Math.max(maxSum, sum);
            }

            return maxSum / k;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        println("Solution1 : " + Solution1.findMaxAverage(nums1, 4));
        println("Solution2 : " + Solution2.findMaxAverage(nums1, 4));
    }
}
