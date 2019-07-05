package _java;

import base.Base;

/**
 * Write a program to check whether a given number is an ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * <p>
 * Example 1:
 * <p>
 * Input: 6
 * Output: true
 * Explanation: 6 = 2 × 3
 * Example 2:
 * <p>
 * Input: 8
 * Output: true
 * Explanation: 8 = 2 × 2 × 2
 * Example 3:
 * <p>
 * Input: 14
 * Output: false
 * Explanation: 14 is not ugly since it includes another prime factor 7.
 */
public class _0263UglyNumber extends Base {

    private abstract static class Solution {
        public abstract boolean isUgly(int num);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Ugly Number.
     * Memory Usage: 33.3 MB, less than 5.24% of Java online submissions for Ugly Number.
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean isUgly(int num) {
            if (num == 1) {
                return true;
            }
            if (num == 0) {
                return false;
            }
            while (num % 2 == 0) {
                num >>= 1;
            }
            while (num % 3 == 0) {
                num /= 3;
            }
            while (num % 5 == 0) {
                num /= 5;
            }
            return num == 1;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.isUgly(6));// t
        println(s.isUgly(8));// t
        println(s.isUgly(14));// f
    }
}