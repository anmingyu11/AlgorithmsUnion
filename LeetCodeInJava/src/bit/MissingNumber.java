package bit;

import base.Base;

public class MissingNumber extends Base {

    abstract static class Solution {
        public abstract int missingNumber(int[] nums);
    }

    /**
     * 高斯公式
     */
    static class Solution1 extends Solution {

        @Override
        public int missingNumber(int[] nums) {

            final int n = nums.length;
            int sum = 0, sum2 = (1 + n) * n / 2;
            for (int i = 0; i < n; ++i) {
                sum += nums[i];
            }
            int res = sum2 - sum;

            return res;
        }

    }

    static class Solution2 extends Solution {

        @Override
        public int missingNumber(int[] nums) {
            int missing = nums.length;

            for (int i = 0; i < nums.length; ++i) {
                missing ^= i ^ nums[i];
            }

            return missing;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 0, 1};
        int[] arr2 = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        println(new Solution2().missingNumber(arr1));
        println(new Solution2().missingNumber(arr2));
    }
}
