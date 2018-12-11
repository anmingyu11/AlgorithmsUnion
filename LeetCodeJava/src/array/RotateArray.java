package array;

import java.util.Arrays;

import base.Base;

public class RotateArray extends Base {

    public static void rotate1(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < k; ++i) {
            int previous = nums[n - 1];
            for (int j = 0; j < n; ++j) {
                int temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    public static void rotate2(int[] nums, int k) {
        final int n = nums.length;
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[(i + k) % n] = nums[i];
        }
        for (int i = 0; i < n; ++i) {
            nums[i] = a[i];
        }
    }

    public static void rotate3(int[] nums, int k) {
        final int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public static void main(String[] args) {
        int[] nums1 = {-1};
        int k1 = 2;
        rotate3(nums1, k1);
        println(Arrays.toString(nums1));
    }
}
