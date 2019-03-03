package _java;

import base.Base;

public class _1004MaxConsecutiveOnes3______________ extends Base {
    private abstract static class Solution {
        public abstract int longestOnes(int[] A, int K);
    }

    private static class Solution1 extends Solution {

        @Override
        public int longestOnes(int[] A, int K) {
            final int n = A.length;
            int[] a;
            int cur = 0;
            int max = 0;
            while (cur < n) {
                if (A[cur] != 0) {
                    ++cur;
                    continue;
                }
                a = A.clone();
                // fill 0
                int filled = 0;
                for (int i = cur; i < n; ++i) {
                    if (filled == K) {
                        break;
                    }
                    if (a[i] == 0) {
                        a[i] = 1;
                        ++filled;
                    }
                }
                // calculate max
                int len = 0;
                for (int i = 0; i < n; ++i) {
                    if (a[i] == 1) {
                        ++len;
                        max = Math.max(max, len);
                    } else {
                        len = 0;
                    }
                }
                ++cur;
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] a1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        int[] a2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        int[] a3 = {0, 0, 0, 0};
        int k3 = 0;
        int[] a4 = {1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1};
        int k4 = 8;

        Solution s = new Solution1();
        println(s.longestOnes(a1, k1)); // 6
        println(s.longestOnes(a2, k2)); // 10
        println(s.longestOnes(a3, k3)); // 0
        println(s.longestOnes(a4, k4)); // 25
    }
}
