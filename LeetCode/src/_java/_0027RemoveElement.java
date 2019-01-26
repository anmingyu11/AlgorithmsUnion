package _java;

import base.Base;

public class _0027RemoveElement extends Base {

    private abstract static class Solution {
        abstract int removeElement(int[] nums, int val);
    }

    private static class Solution1 extends Solution {

        @Override
        int removeElement(int[] nums, int val) {
            int j = 0;
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != val) {
                    nums[j++] = nums[i];
                }
            }

            return j;
        }
    }

    // 6ms 这个思想很有意思,怎么想出来的呢.
    private static class Solution2 extends Solution {

        @Override
        int removeElement(int[] nums, int val) {
            int n = nums.length;

            int i = 0;
            while (i < n) {
                if (nums[i] == val) {
                    nums[i] = nums[n-- - 1];
                } else {
                    ++i;
                }
            }

            return n;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 2, 3};
        int v1 = 3;
        int[] arr2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int v2 = 2;

        Solution s = new Solution2();

        int r1 = s.removeElement(arr1, v1);
        int r2 = s.removeElement(arr2, v2);

        printArr(arr1);
        println("len : " + r1);
        printArr(arr2);
        println("len : " + r2);
    }
}
