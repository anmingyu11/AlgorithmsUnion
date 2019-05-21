package _java;

import base.Base;

public class _0303RangeSumQueryImmutable extends Base {

    private abstract static class NumArray {
        public NumArray(int[] nums) {

        }

        public abstract int sumRange(int i, int j);
    }

    /**
     * Runtime: 51 ms, faster than 99.66% of Java online submissions for Range Sum Query - Immutable.
     * Memory Usage: 42.6 MB, less than 46.27% of Java online submissions for Range Sum Query - Immutable.
     */
    private static class Solution1 extends NumArray {
        final int[] dp;

        public Solution1(int[] nums) {
            super(nums);
            final int n = nums.length;
            dp = new int[n + 1];
            int sum = 0;
            for (int i = 0; i < n; ++i) {
                sum += nums[i];
                dp[i + 1] = sum;
            }
            printArr(dp);

        }

        @Override
        public int sumRange(int i, int j) {
            return dp[j + 1] - dp[i];
        }
    }

    public static void main(String[] args) {
        int[] a1 = {-2, 0, 3, -5, 2, -1};
        int[] a2 = {0, 1, 2, 3};

        NumArray s = new Solution1(a1);

        println(s.sumRange(0, 2)); // 1
        println(s.sumRange(2, 5)); // -1
        println(s.sumRange(0, 5)); // 3

    }
}
