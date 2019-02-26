package _java;

import base.Base;

public class _0962MaximumWidthRamp__________ extends Base {
    // Given an array A of integers,
    // a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].
    // The width of such a ramp is j - i.
    private abstract static class Solution {
        public abstract int maxWidthRamp(int[] A);
    }

    // beats 35%
    private static class Solution1 extends Solution {

        @Override
        public int maxWidthRamp(int[] A) {
            final int n = A.length;
            int max = 0;
            for (int j = n - 1; j > 0; --j) {
                for (int i = j - 1 - max; i >= 0; --i) {
                    if (A[i] <= A[j]) {
                        max = Math.max(j - i, max);
                    }
                }
            }
            return max;
        }
    }

    private static class Solution2 extends Solution {

        @Override
        public int maxWidthRamp(int[] A) {
            final int n = A.length;
            int max = 0;
            for (int j = n - 1; j > 0; --j) {
                if (max > j) {
                    return max;
                }
                for (int i = j - 1 - max; i >= 0; --i) {
                    if (A[i] <= A[j]) {
                        max = Math.max(j - i, max);
                    }
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] a1 = {6, 0, 8, 2, 1, 5};
        int[] a2 = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};

        Solution s = new Solution2();

        println(s.maxWidthRamp(a1)); // 4
        println(s.maxWidthRamp(a2)); // 7

    }
}
