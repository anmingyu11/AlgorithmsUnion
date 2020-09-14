package _java;

import base.Base;

/**
 * Given an array nums, write a function to move all 0's
 * to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class _0283MoveZeroes extends Base {
    private abstract static class Solution {
        abstract void moveZeroes(int[] nums);
    }

    // 1ms beats 100%
    private static class Solution1 extends Solution {

        @Override
        void moveZeroes(int[] nums) {
            int j = 0;
            int n = nums.length;
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != 0) {
                    nums[j++] = nums[i];
                }
            }
            for (; j < nums.length; ++j) {
                nums[j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{0, 1, 0, 3, 12};

        Solution s = new Solution1();
        s.moveZeroes(arr1);
        printArr(arr1);
    }
}
