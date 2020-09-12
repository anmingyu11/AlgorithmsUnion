package _java;

import base.Base;

/**
 * Given a sorted array nums,
 * remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * <p>
 * Do not allocate extra space for another array,
 * you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5,
 * with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Given nums = [0,0,1,1,1,1,2,3,3],
 * <p>
 * Your function should return length = 7,
 * with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 * <p>
 * Confused why the returned value is an integer but your answer is an array?
 * <p>
 * Note that the input array is passed in by reference,
 * which means modification to the input array will be known to the caller as well.
 * <p>
 * Internally you can think of this:
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 */
public class _0080RemoveDuplicatesFromSortedArray2 extends Base {

    private abstract static class Solution {
        abstract int removeDuplicates(int[] nums);
    }

    /**
     * 我的,丑陋 beats 97.47%
     */
    private static class Solution1 extends Solution {

        @Override
        int removeDuplicates(int[] nums) {
            if (nums.length < 3) {
                return nums.length;
            }
            int i = 0, j = 0, last = nums[i] - 1, counter = 0;
            for (; i < nums.length; ++i) {
                if (nums[i] != last) {
                    nums[j++] = nums[i];
                    last = nums[i];
                    counter = 1;
                } else if (nums[i] == last && counter < 2) {
                    nums[j++] = nums[i];
                    ++counter;
                }
            }
            return j;
        }

    }

    /**
     * StefanPochman的解法
     * doubt my iq..
     */
    private static class Solution2 extends Solution {

        @Override
        int removeDuplicates(int[] nums) {
            int i = 0;
            for (int e : nums) {
                if (i < 2 || e > nums[i - 2]) {
                    nums[i++] = e;
                }
            }
            return i;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 1, 1, 2, 2, 3};
        int[] arr2 = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};

        Solution s = new Solution2();

        println("arr1 : " + s.removeDuplicates(arr1));
        printArr(arr1);
        println("arr2 : " + s.removeDuplicates(arr2));
        printArr(arr2);
    }
}
