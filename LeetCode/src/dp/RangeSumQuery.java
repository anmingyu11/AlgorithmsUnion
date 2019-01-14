package dp;

import base.Base;

public class RangeSumQuery extends Base {

    static class NumArray {

        final int[] sums;

        public NumArray(int[] nums) {
            final int n = nums.length;
            sums = new int[n + 1];
            for (int i = 0; i < nums.length; ++i) {
                sums[i + 1] = nums[i] + sums[i];
            }
        }

        public int sumRange(int lo, int hi) {
            return sums[hi + 1] - sums[lo];
        }

    }

    public static void main(String[] args) {
        NumArray na = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        println(na.sumRange(0, 2));
        println(na.sumRange(2, 5));
        println(na.sumRange(0, 5));
    }

}
