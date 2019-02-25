package _java;

import java.util.Arrays;

import base.Base;

public class _0164MaximumGap__________ extends Base {
    private abstract static class Solution {
        public abstract int maximumGap(int[] nums);
    }

    // Runtime: 3 ms, faster than 99.66% of Java online submissions for Maximum Gap.
    // Memory Usage: 38.7 MB, less than 30.43% of Java online submissions for Maximum Gap.
    private static class Solution1 extends Solution {
        @Override
        public int maximumGap(int[] nums) {
            final int n = nums.length;
            if (n < 2) {
                return 0;
            }

            Arrays.sort(nums);
            int max = 0;
            for (int i = 0; i < n - 1; ++i) {
                int gap = nums[i + 1] - nums[i];
                max = Math.max(gap, max);
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 6, 9, 1};
        int[] arr2 = {10};

        Solution s = new Solution1();

        println(s.maximumGap(arr1));
        println(s.maximumGap(arr2));

    }
}
