package _java;

import base.Base;

public class _0238ProductOfArrayExceptSelf extends Base {
    private abstract static class Solution {
        public abstract int[] productExceptSelf(int[] nums);
    }

    // 这个属于dp
    // Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
    // Memory Usage: 39.5 MB, less than 99.65% of Java online submissions for Product of Array Except Self.
    private static class Solution1 extends Solution {

        @Override
        public int[] productExceptSelf(int[] nums) {
            final int n = nums.length;
            int[] ans = new int[n];
            int cur = 1;
            ans[0] = 1;
            for (int i = 1; i < n; ++i) {
                cur *= nums[i - 1];
                ans[i] = cur;
            }
            cur = 1;
            for (int i = n - 2; i >= 0; --i) {
                cur *= nums[i + 1];
                ans[i] *= cur;
            }
            return ans;
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public int[] productExceptSelf(int[] nums) {
            final int n = nums.length;
            int cur = 1;
            for (int i = 1; i < n; ++i) {
                nums[i] = cur * nums[i - 1];
            }
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};

        Solution s = new Solution2();

        printArr(s.productExceptSelf(arr));
    }
}
