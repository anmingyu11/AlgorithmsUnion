package _java;

import base.Base;

public class _0189RotateArray extends Base {

    private abstract static class Solution {
        abstract void rotate(int[] nums, int k);
    }

    private static class Solution1 extends Solution {
        public void rotate(int[] nums, int k) {
            k = k % nums.length;
            int count = 0;
            for (int start = 0; count < nums.length; start++) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % nums.length;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                    count++;
                } while (start != current);
            }
        }
    }

    private static class Solution2 extends Solution {

        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k1 = 2;
        int[] arr2 = new int[]{-1, -100, 3, 99};
        int k2 = 2;
        int[] arr3 = new int[]{-1};
        int k3 = 1;
        int[] arr4 = new int[]{};
        int k4 = 1;
        int[] arr5 = new int[]{1, 2};
        int k5 = 1;
        int[] arr6 = new int[]{1, 2, 3, 4, 5, 6};
        int k6 = 1;
        int[] arr7 = new int[]{1, 2, 3, 4, 5, 6};
        int k7 = 5;

        Solution s = new Solution1();

        s.rotate(arr1, k1);
        printArr(arr1); // 5,6,7,1,2,3,4
        s.rotate(arr2, k2);
        printArr(arr2); // 3,99,-1,-100
        s.rotate(arr3, k3);
        printArr(arr3); // -1
        s.rotate(arr4, k4);
        printArr(arr4); //
        s.rotate(arr5, k5);
        printArr(arr5); // 2, 1
        s.rotate(arr6, k6);
        printArr(arr6); // 6,1,2,3,4,5
        s.rotate(arr7, k7);
        printArr(arr7); // 2,3,4,5,6,1
    }
}
