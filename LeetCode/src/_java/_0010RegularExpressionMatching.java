package _java;

import base.Base;

/**
 * Given an input string (s) and a pattern (p),
 * implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * <p>
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * <p>
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class _0010RegularExpressionMatching extends Base {

    private abstract static class Solution {
        public abstract boolean isMatch(String s, String p);
    }

    /**
     * Runtime: 40 ms, faster than 28.78% of Java online submissions for Regular Expression Matching.
     * Memory Usage: 38.3 MB, less than 31.90% of Java online submissions for Regular Expression Matching
     * This is funny
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean isMatch(String s, String p) {
            return s.matches(p);
        }

    }

    /**
     * Runtime: 59 ms, faster than 12.96% of Java online submissions for Regular Expression Matching.
     * Memory Usage: 36.8 MB, less than 99.95% of Java online submissions for Regular Expression Matching.
     */
    private static class Solution2 extends Solution {

        @Override
        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) {
                return s.isEmpty();
            }
            boolean match = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
            if (p.length() >= 2 && p.charAt(1) == '*') {
                return (match && isMatch(s.substring(1), p)) // 匹配{char}*
                        || isMatch(s, p.substring(2)); // 如果不匹配，向后匹配。
            } else {
                return match && isMatch(s.substring(1), p.substring(1)); // 向后匹配。
            }
        }

    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Regular Expression Matching.
     * Memory Usage: 36 MB, less than 99.94% of Java online submissions for Regular Expression Matching.
     */
    private static class Solution3 extends Solution {

        private Boolean[][] dp;
        private String s;
        private String p;

        public boolean isMatch(String s, String p) {
            this.s = s;
            this.p = p;
            dp = new Boolean[s.length() + 1][p.length() + 1];
            return matchAux(0, 0);
        }

        private boolean matchAux(int i, int j) {
            if (dp[i][j] != null) {
                // s.sub(i) 能否匹配 p.sub[j]
                return dp[i][j];
            }
            if (j == p.length()) {
                // pattern到末尾
                dp[i][j] = i == s.length();
            } else {
                // 匹配s[i],p[j]
                boolean fMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j + 1 < p.length()
                        && p.charAt(j + 1) == '*') {
                    // 匹配 '*'
                    dp[i][j] = matchAux(i, j + 2) || (fMatch && matchAux(i + 1, j));
                } else {
                    dp[i][j] = fMatch && matchAux(i + 1, j + 1);
                }
            }
            return dp[i][j];
        }
    }

    /**
     * Hard to get this bu solution.
     * Runtime: 2 ms, faster than 96.18% of Java online submissions for Regular Expression Matching.
     * Memory Usage: 35.9 MB, less than 99.93% of Java online submissions for Regular Expression Matching.
     */
    private static class Solution4 extends Solution {

        @Override
        public boolean isMatch(String s, String p) {
            final int m = s.length(), n = p.length();
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[m][n] = true; // 终点
            for (int i = m; i >= 0; --i) {
                for (int j = n - 1; j >= 0; --j) {
                    boolean match = i < s.length()
                            && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');// si对应pj是否匹配
                    if (j + 1 < n && p.charAt(j + 1) == '*') {
                        dp[i][j] = match && dp[i + 1][j] || dp[i][j + 2];
                    } else {
                        dp[i][j] = match && dp[i + 1][j + 1];// 匹配下一个
                    }
                }
            }
            return dp[0][0];
        }

    }

    private static void test(String s, String p) {
        Solution so = new Solution4();
        println(so.isMatch(s, p));
    }

    public static void main(String[] args) {
        test("aa", "a"); // F
        test("aaa", "aaaa"); // F
        test("aa", "a*"); // T
        test("ab", ".*"); // T
        test("aab", "c*a*b"); // T
        test("mississippi", "mis*is*p*."); // F
    }

}