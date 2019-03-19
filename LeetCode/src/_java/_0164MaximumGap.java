package _java;

import java.util.Arrays;

import base.Base;

public class _0164MaximumGap extends Base {
    private abstract static class Solution {
        public abstract int maximumGap(int[] nums);
    }

    // Runtime: 3 ms, faster than 99.66% of Java online submissions for Maximum Gap.
    // Memory Usage: 38.7 MB, less than 30.43% of Java online submissions for Maximum Gap.
    private static class Solution1 extends Solution {
        @Override
        public int maximumGap(int[] nums) {
            final int n = nums.length;
            if (n < 2) {
                return 0;
            }

            Arrays.sort(nums);
            int max = 0;
            for (int i = 0; i < n - 1; ++i) {
                int gap = nums[i + 1] - nums[i];
                max = Math.max(gap, max);
            }
            return max;
        }
    }

    // Radix Sort 这个更快一些
    // Runtime: 5 ms, faster than 48.74% of Java online submissions for Maximum Gap.
    // Memory Usage: 37.5 MB, less than 88.04% of Java online submissions for Maximum Gap.
    private static class Solution2 extends Solution {

        // least significant sort
        @Override
        public int maximumGap(int[] nums) {
            final int n = nums.length;
            if (n < 2) {
                return 0;
            }
            lsd(nums);
            int max = 0;
            for (int i = 0; i < n - 1; ++i) {
                int gap = nums[i + 1] - nums[i];
                max = Math.max(gap, max);
            }
            return max;
        }

        private void lsd(int[] a) {
            final int n = a.length;
            final int PER_BITS = 8;
            final int R = 1 << 8;
            final int MASK = R - 1;
            final int w = 4;
            int[] aux = new int[n];
            for (int d = 0; d < w; ++d) {
                final int shift = d * PER_BITS;
                int[] count = new int[R + 1];
                for (int i = 0; i < n; ++i) {
                    ++count[((a[i] >> shift) & MASK) + 1];
                }
                for (int r = 0; r < R; ++r) {
                    count[r + 1] += count[r];
                }
                if (d == w - 1) {
                    int shift1 = count[R] - count[R / 2];
                    int shift2 = count[R / 2];
                    for (int r = 0; r < R / 2; ++r) {
                        count[r] += shift1;
                    }
                    for (int r = R / 2; r < R; ++r) {
                        count[r] -= shift2;
                    }
                }
                for (int i = 0; i < n; ++i) {
                    aux[count[(a[i] >> shift) & MASK]++] = a[i];
                }
                for (int i = 0; i < n; ++i) {
                    a[i] = aux[i];
                }
            }
        }
    }

    // 另一种radix sort
    // Runtime: 6 ms, faster than 32.29% of Java online submissions for Maximum Gap.
    // Memory Usage: 38.7 MB, less than 29.35% of Java online submissions for Maximum Gap.
    private static class Solution3 extends Solution {

        public static int max(int[] A) {
            final int n = A.length;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i <= n / 2; ++i) {
                max = Math.max(max, Math.max(A[i], A[n - 1 - i]));
            }
            return max;
        }

        @Override
        public int maximumGap(int[] nums) {
            final int n = nums.length;
            if (n < 2) {
                return 0;
            }

            lsd(nums);

            int max = 0;
            for (int i = 0; i < n - 1; ++i) {
                int gap = nums[i + 1] - nums[i];
                max = Math.max(gap, max);
            }
            return max;
        }

        private void lsd(int[] arr) {
            final int n = arr.length;
            int exp = 1;
            final int radix = 10;
            final int max = max(arr);
            int[] aux = new int[n];
            while (max / exp > 0) {
                int[] count = new int[10];
                for (int i = 0; i < n; ++i) {
                    ++count[(arr[i] / exp) % radix];
                }
                for (int r = 1; r < radix; ++r) {
                    count[r] += count[r - 1];
                }
                for (int i = n - 1; i >= 0; --i) {
                    aux[--count[arr[i] / exp % radix]] = arr[i];
                }
                for (int i = 0; i < n; ++i) {
                    arr[i] = aux[i];
                }
                exp *= 10;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 6, 9, 1};
        int[] arr2 = {10};
        int[] arr3 = {1, 10000000};
        int[] arr4 = {100, 3, 2, 1};

        Solution s = new Solution3();

        println(s.maximumGap(arr1));
        println(s.maximumGap(arr2));
        println(s.maximumGap(arr3));
        println(s.maximumGap(arr4));

    }
}
