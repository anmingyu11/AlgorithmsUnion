package _java;

import base.Base;

/**
 * A message containing letters from A-Z is being encoded to numbers using
 * the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * <p>
 * Given a non-empty string containing only digits,
 * determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * Example 2:
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class _0091DecodeWays extends Base {

    private abstract static class Solution {
        public abstract int numDecodings(String s);
    }

    /**
     * Runtime: 1 ms, faster than 98.62% of Java online submissions for Decode Ways.
     * Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Decode Ways.
     * 这明显有bug竟然通过了,傻逼问题.
     * 0是一个大问题,题目描述没有给出精确定义,但是testcase里却到处都是0
     */
    private static class Solution1 extends Solution {

        @Override
        public int numDecodings(String s) {
            if (s.isEmpty()) {
                return 0;
            }
            final int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            dp[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;
            for (int i = n - 2; i >= 0; --i) {
                int cur = 0;
                int num1 = s.charAt(i) - '0';
                int num2 = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                if (num1 != 0) {
                    // 1位
                    cur += dp[i + 1];
                }
                if (num2 >= 10 && num2 <= 26) {
                    cur += dp[i + 2];
                }
                dp[i] = cur;
            }
            return dp[0];
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.numDecodings("0"));   // 0
        println(s.numDecodings("00"));  // 0
        println(s.numDecodings("1"));   // 1
        println(s.numDecodings("12"));  // 2
        println(s.numDecodings("226")); // 3
        println(s.numDecodings("110")); // 1
        println(s.numDecodings("11"));  // 2
        println(s.numDecodings("10"));  // 1
        println(s.numDecodings("160")); //
    }
}
