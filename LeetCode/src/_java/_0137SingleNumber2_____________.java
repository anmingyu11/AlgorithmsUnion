package _java;

import base.Base;

public class _0137SingleNumber2_____________ extends Base {

    private abstract static class Solution {
        public abstract int singleNumber(int[] nums);
    }

    private static class Solution1 extends Solution {

        @Override
        public int singleNumber(int[] nums) {
            return 0;
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public int singleNumber(int[] nums) {
            return 0;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = {2, 2, 3, 2};
        int[] arr2 = {0, 1, 0, 1, 0, 1, 99};

        Solution s = new Solution1();
    }
}
