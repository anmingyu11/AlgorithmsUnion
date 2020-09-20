package _java;

import base.Base;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Follow up:
 * <p>
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * <p>
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * It's guaranteed that nums[i] fits in a 32 bit-signed integer.
 * k >= 0
 */
public class _0189RotateArray extends Base {

    private static abstract class Solution {
        public abstract void rotate(int[] nums, int k);
    }

    private static class Solution1 extends Solution {
        @Override
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            if (n <= 1) {
                return;
            }
            k %= n;
            reverse(nums, 0, n - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, n - 1);
        }

        private void reverse(int[] A, int lo, int hi) {
            while (lo < hi) {
                int tmp = A[lo];
                A[lo] = A[hi];
                A[hi] = tmp;
                ++lo;
                --hi;
            }
        }
    }

    private static class Solution2 extends Solution {

        @Override
        public void rotate(int[] nums, int k) {
            final int n = nums.length;
            if (n < 2) {
                return;
            }
            k %= n;
            int[] arr = new int[n];
            for (int i = n - 1, j = k - 1; i > n - 1 - k; --i, --j) {
                arr[j] = nums[i];
            }
            for (int i = 0, j = k; i < n - k; ++i, ++j) {
                arr[j] = nums[i];
            }
            System.arraycopy(arr, 0, nums, 0, n);
        }

    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        int[] num2 = new int[]{-1, -100, 3, 99};
        int k2 = 2;
        Solution s = new Solution2();
        s.rotate(num1, k1);
        printArr(num1);//[5,6,7,1,2,3,4]
        s.rotate(num2, k2);
        printArr(num2);//[3,99,-1,-100]
    }

}
