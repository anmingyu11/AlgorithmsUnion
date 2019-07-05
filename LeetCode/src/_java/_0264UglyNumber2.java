package _java;

import java.util.TreeSet;

import base.Base;

/**
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * <p>
 * Example:
 * <p>
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note:
 * <p>
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 */
public class _0264UglyNumber2 extends Base {
    private abstract static class Solution {
        public abstract int nthUglyNumber(int n);
    }


    /**
     * The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
     * because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:
     * <p>
     * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     * We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.
     * <p>
     * Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.
     * <p>
     * Every step we choose the smallest one, and move one step after,including nums with same value.
     * <p>
     * Thanks for this author about this brilliant idea. Here is my java solution
     * <p>
     * Runtime: 2 ms, faster than 99.44% of Java online submissions for Ugly Number II.
     * Memory Usage: 34.2 MB, less than 31.02% of Java online submissions for Ugly Number II.
     * <p>
     * 如何靠上动态规划
     */
    private static class Solution1 extends Solution {

        @Override
        public int nthUglyNumber(int n) {
            int[] ugly = new int[n];
            ugly[0] = 1;
            int f2 = 2, f3 = 3, f5 = 5;
            int i2 = 0, i3 = 0, i5 = 0;
            for (int i = 1; i < n; ++i) {
                int min = Math.min(f2, Math.min(f3, f5));
                ugly[i] = min;
                if (min == f2) {
                    f2 = 2 * ugly[++i2];
                }
                if (min == f3) {
                    f3 = 3 * ugly[++i3];
                }
                if (min == f5) {
                    f5 = 5 * ugly[++i5];
                }
            }
            return ugly[n - 1];
        }

    }

    /**
     * Can use heap but the heap can be duplicate so transfers to RBTree.
     * Runtime: 38 ms, faster than 23.80% of Java online submissions for Ugly Number II.
     * Memory Usage: 40.1 MB, less than 5.14% of Java online submissions for Ugly Number II.
     */
    private static class Solution2 extends Solution {

        @Override
        public int nthUglyNumber(int n) {
            // Why Long
            TreeSet<Long> tree = new TreeSet<>();
            tree.add(1l);
            for (int i = 1; i < n; ++i) {
                // 如果不是long则会溢出
                long first = tree.pollFirst();
                // debug
                if (first * 2 > Integer.MAX_VALUE || first * 3 > Integer.MAX_VALUE || first * 5 > Integer.MAX_VALUE) {
                    println(Integer.valueOf((int) (first * 2)));
                    println(Integer.valueOf((int) (first * 3)));
                    println(Integer.valueOf((int) (first * 5)));
                }
                tree.add(first * 2);
                tree.add(first * 3);
                tree.add(first * 5);
            }
            return tree.first().intValue();
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        //println(s.nthUglyNumber(10));
        println(s.nthUglyNumber(1690));

        //println(Integer.valueOf(2123366400));

    }
}
