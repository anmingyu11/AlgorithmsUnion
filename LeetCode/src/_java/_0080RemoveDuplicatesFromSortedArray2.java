package _java;

import base.Base;

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
            final int len = nums.length;
            if (len <= 2) {
                return len;
            }

            int i = 0;
            int j = 1;
            int time = 1;
            for (; j < len; ++j) {
                if (nums[j] == nums[j - 1]) {
                    ++time;
                } else {
                    if (time == 1) {
                        nums[i++] = nums[j - 1];
                    } else {
                        nums[i++] = nums[j - 1];
                        nums[i++] = nums[j - 1];
                    }
                    time = 1;
                }
            }

            if (time == 1) {
                nums[i++] = nums[j - 1];
            } else {
                nums[i++] = nums[j - 1];
                nums[i++] = nums[j - 1];
            }

            return i;
        }

    }

    /**
     * StefanPochman的解法，这个简直天才。  开始怀疑我自己是不是傻逼
     */
    static class Solution2 extends Solution {

        @Override
        int removeDuplicates(int[] nums) {
            int i = 0;

            for (int n : nums) {
                if (i < 2 || n > nums[i - 2]) {
                    nums[i++] = n;
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
