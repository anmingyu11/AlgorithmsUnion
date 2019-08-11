package _java;

import base.Base;

/**
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x,
 * where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer,
 * the decimal digits are truncated and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * Input: 4
 * Output: 2
 * <p>
 * Example 2:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 */
public class _0067SqrtX extends Base {

    private abstract static class Solution {
        public abstract int mySqrt(int x);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Sqrt(x).
     * Memory Usage: 33.6 MB, less than 5.00% of Java online submissions for Sqrt(x).
     */
    private static class Solution1 extends Solution {

        @Override
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }
            int lo = 1, hi = x, ans = 0, mid = 0;
            while (lo <= hi) {
                mid = (lo + hi) / 2;
                if (mid <= x / mid) {
                    lo = mid + 1;
                    ans = mid;
                } else {
                    hi = mid - 1;
                }
            }
            return ans;
        }

    }

    /**
     * 牛顿法
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Sqrt(x).
     * Memory Usage: 33.5 MB, less than 5.00% of Java online submissions for Sqrt(x).
     */
    private static class Solution2 extends Solution {

        @Override
        public int mySqrt(int x) {
            double ans = x;
            double delta = 1.0E-4;
            while (Math.abs((Math.pow(ans, 2) - x)) > delta) {
                ans = (ans + x / ans) / 2f;
            }
            return (int) ans;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        println(s.mySqrt(4));// 2
        println(s.mySqrt(8));// 2

    }
}