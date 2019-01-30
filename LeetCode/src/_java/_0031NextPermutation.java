package _java;

import base.Base;

public class _0031NextPermutation extends Base {

    private abstract static class Solution {
        public abstract void nextPermutation(int[] nums);
    }

    // 难以想象, 找出这个规律需要对排列有着深刻的理解.
    private static class Solution1 extends Solution {

        public void nextPermutation(int[] nums) {
            final int len = nums.length;
            for (int i = len - 1; i >= 1; --i) {
                if (nums[i - 1] < nums[i]) {
                    int index = findLargerIndex(nums, nums[i - 1], i);
                    swap(nums, i - 1, index);
                    reverse(nums, i, len - 1);
                    return;
                }
            }
            reverse(nums, 0, len - 1);
        }

        private void reverse(int[] nums, int lo, int hi) {
            while (lo <= hi) {
                swap(nums, lo, hi);
                ++lo;
                --hi;
            }
        }

        private int findLargerIndex(int[] nums, int target, int lo) {
            // 确认从lo 到 nums.length -1 是降序
            for (int i = nums.length - 1; i >= lo; --i) {
                if (nums[i] > target) {
                    return i;
                }
            }
            return -1;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }

    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{3, 2, 1};
        int[] arr3 = new int[]{1, 1, 5};
        int[] arr4 = new int[]{1, 5, 8, 4, 7, 6, 5, 3, 1};

        Solution s = new Solution1();

        s.nextPermutation(arr4);// 1,5,8,5,1,3,4,6,7
        printArr(arr4);
        s.nextPermutation(arr1);// 1,3,2
        printArr(arr1);
        s.nextPermutation(arr2);// 1,2,3
        printArr(arr2);
        s.nextPermutation(arr3);// 1,5,1
        printArr(arr3);
    }
}
