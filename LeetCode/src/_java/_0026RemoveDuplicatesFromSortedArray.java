package _java;

import base.Base;

public class _0026RemoveDuplicatesFromSortedArray extends Base {

    private abstract static class Solution {
        abstract public int removeDuplicates(int[] nums);
    }

    private static class Solution1 extends Solution {

        @Override
        public int removeDuplicates(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            int j = 1;

            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] != nums[i - 1]) {
                    nums[j++] = nums[i];
                }
            }

            return j;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 1, 2};
        int[] arr2 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        Solution s = new Solution1();
        println(s.removeDuplicates(arr1));
        printArr(arr1);
        println(s.removeDuplicates(arr2));
        printArr(arr2);
    }

}
