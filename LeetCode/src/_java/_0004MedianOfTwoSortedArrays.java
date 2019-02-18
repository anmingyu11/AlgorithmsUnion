package _java;

import base.Base;

public class _0004MedianOfTwoSortedArrays extends Base {

    private abstract static class Solution {
        public abstract double findMedianSortedArrays(int[] nums1, int[] nums2);
    }

    // 第一个想到的是归并排序. Ω(n/2) O(n/2) 比较稳定.
    // Runtime: 29 ms, faster than 63.34% of Java online submissions for Median of Two Sorted Arrays .
    // Memory Usage: 48.8 MB, less than 100.00% of Java online submissions for Median of Two Sorted Arrays.
    private static class Solution1 extends Solution {

        @Override
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            final int n1 = nums1.length;
            final int n2 = nums2.length;
            final int n = n1 + n2;
            int[] aux = new int[n / 2 + 1];
            int i = 0, j = 0;
            for (int k = 0; k <= n / 2; ++k) {
                if (i > n1 - 1) {
                    aux[k] = nums2[j++];
                } else if (j > n2 - 1) {
                    aux[k] = nums1[i++];
                } else if (nums1[i] < nums2[j]) {
                    aux[k] = nums1[i++];
                } else {
                    aux[k] = nums2[j++];
                }
            }
            if ((n & 1) == 1) {
                return aux[n / 2];
            } else {
                return (aux[n / 2] + aux[n / 2 - 1]) / 2.0;
            }

        }

    }

    // 分治法 可能是自己的问题,感觉很难想出来.
    // Runtime: 26 ms, faster than 91.16% of Java online submissions for Median of Two Sorted Arrays.
    // Memory Usage: 49.6 MB, less than 100.00% of Java online submissions for Median of Two Sorted Arrays.
    // 求中值
    // 1. 设A数组是[0..m],B数组是[0...n]
    // 2. 求中值,就是两个数组合并排序后中间那个位置的值 (m + n +1)/2
    // 先将 A 从 i 切分开
    //          left_A             |        right_A
    //    A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
    // 左面A的长度是len(left_A) = i 右面的A的长度是 len(right_A)
    // 再将 B从 j 且分开.
    //       left_B                |        right_B
    //    B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
    // 长度同理
    //          left_part          |        right_part
    //    A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
    //    B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
    // 现在将A[0..i-1] 和B[0..j-1]放到一起,将A[i..m-1]和B[j..n-1] 放到一起
    // 假设:左面部分的长度len(left_part) == len(right_part) 这样能满足中位值的要求
    // 则max(left_part) <= max(right_part)
    // 则中位值是
    // median = (max(leftPart) + min(rightPart))/2
    // 所以必须满足 (i+j) == (m - i + n - j + 1 )
    // 因为i是0到m之间的一点,所以j = (m+n+1)/2 - i
    // n必须大于m 可以将 0 <= j <= n 带入不等式,换算一下,如果m大于n ,j 有可能是负值
    // 所以
    // 当总长度为偶数的时候
    // 我们需要找左部分left_part的最大值,和右部分right_part的最小值
    // 为奇数的时候,只需要取左面部分left_part的最大值
    // 以上为整体思路.
    private static class Solution2 extends Solution {

        @Override
        public double findMedianSortedArrays(int[] A, int[] B) {
            int m = A.length;
            int n = B.length;
            if (m > n) { // to ensure m<=n
                return findMedianSortedArrays(B, A);
            }

            int mid = (m + n + 1) / 2;
            int lo = 0, hi = m;
            while (lo <= hi) {
                int i = (lo + hi) / 2; // i = (0 + m)/2
                int j = mid - i; // j = (m + n + 1)/2 - i
                if (i < hi && A[i] < B[j - 1]) {
                    // need a bigger A[i] i = (lo+hi)/2 lo = mid +1
                    lo = i + 1;
                } else if (i > lo && A[i - 1] > B[j]) {
                    // need a  smaller A[i]  i = (lo+hi)/2 hi = mid-1
                    hi = i - 1;
                } else {
                    int left = 0;
                    if (i == 0) {
                        // i在0的位置,说明A数组太小,中值在B数组里.
                        left = B[j - 1];
                    } else if (j == 0) {
                        // j在0的位置,说明B数组太小,中值在A数组里.
                        left = A[i - 1];
                    } else {
                        // 选左面部分较大的
                        left = Math.max(A[i - 1], B[j - 1]);
                    }

                    if (((m + n) & 1) == 1) {
                        // 如果数组长度是奇数,直接取中点即可.
                        return left;
                    }

                    int right = 0;
                    if (i == m) {
                        // i在0的位置,说明A数组太小,中值在B数组里
                        right = B[j];
                    } else if (j == n) {
                        // j在0的位置,说明B数组太小,中值在A数组里
                        right = A[i];
                    } else {
                        right = Math.min(A[i], B[j]);
                    }
                    return (left + right) / 2.0;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] arr1_1 = {1, 3};
        int[] arr1_2 = {2};
        int[] arr2_1 = {1, 2};
        int[] arr2_2 = {3, 4};
        int[] arr3_1 = {1, 2, 3, 4};
        int[] arr3_2 = {5, 6, 7};

        Solution s = new Solution2();

        println(s.findMedianSortedArrays(arr1_1, arr1_2)); // 2.0
        println(s.findMedianSortedArrays(arr2_1, arr2_2)); // 2.5
        println(s.findMedianSortedArrays(arr3_1, arr3_2)); // 4.0

    }

}
