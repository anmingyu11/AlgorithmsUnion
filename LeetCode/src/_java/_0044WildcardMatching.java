package _java;

import base.Base;

/**
 * Given an input string (s) and a pattern (p),
 * implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * <p>
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * <p>
 * Example 2:
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * <p>
 * Example 3:
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * <p>
 * Example 4:
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * <p>
 * Example 5:
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class _0044WildcardMatching extends Base {

    private abstract static class Solution {
        public abstract boolean isMatch(String s, String p);
    }

    // TLE
    private static class Solution1 extends Solution {

        private char[] s;
        private char[] p;

        public boolean isMatch(String s, String p) {
            this.s = s.toCharArray();
            this.p = p.toCharArray();
            return isMatchAux(0, 0);
        }

        private boolean isMatchAux(int i, int j) {
            // matched
            if (j == p.length && i == s.length) {
                return true;
            }
            if (i == s.length) {
                if (p[j] == '*') {
                    return isMatchAux(i, j + 1);
                } else {
                    return false;
                }
            }
            if (j == p.length) {
                return false;
            }
            if (j < p.length && p[j] == '*') {
                return isMatchAux(i, j + 1) || isMatchAux(i + 1, j) || isMatchAux(i + 1, j + 1);
            }
            if ((j < p.length && p[j] == '?') || (i < s.length && j < p.length && s[i] == p[j])) {
                return isMatchAux(i + 1, j + 1);
            }
            return false;
        }

    }

    /**
     * Runtime: 13 ms, faster than 40.14% of Java online submissions for Wildcard Matching.
     * Memory Usage: 40.4 MB, less than 41.86% of Java online submissions for Wildcard Matching.
     * <p>
     * Mine memorized
     */
    private static class Solution2 extends Solution {

        private char[] s;
        private char[] p;
        private Boolean[][] dp;

        public boolean isMatch(String s, String p) {
            if (p.equals(s) || p.equals("*")) {
                return true;
            }
            if (p.isEmpty() || s.isEmpty()) {
                return false;
            }
            this.s = s.toCharArray();
            this.p = removeDuplicate(p);
            dp = new Boolean[s.length() + 1][p.length() + 1];
            dp[s.length()][p.length()] = true;
            return isMatchAux(0, 0);
        }

        private char[] removeDuplicate(String p) {
            StringBuilder sb = new StringBuilder();
            sb.append(p.charAt(0));
            int N = p.length();
            for (int i = 1; i < N; ++i) {
                char ch = p.charAt(i);
                if (!(ch == '*' && p.charAt(i - 1) == '*')) {
                    sb.append(ch);
                }
            }
            return sb.toString().toCharArray();
        }

        private boolean isMatchAux(int i, int j) {
            // matched
            if (j == p.length && i == s.length) {
                return true;
            }
            if (dp[i][j] != null) {
                return dp[i][j];
            } else if (j == p.length - 1 && p[j] == '*') {
                dp[i][j] = true;
            } else if (i == s.length || j == p.length) {
                dp[i][j] = false;
            } else if (p[j] == '*') {
                dp[i][j] = isMatchAux(i, j + 1) || isMatchAux(i + 1, j);// || isMatchAux(i + 1, j + 1);
            } else if (p[j] == '?' || s[i] == p[j]) {
                dp[i][j] = isMatchAux(i + 1, j + 1);
            } else {
                dp[i][j] = false;
            }
            return dp[i][j];
        }

    }

    /**
     * Runtime: 13 ms, faster than 40.18% of Java online submissions for Wildcard Matching.
     * Memory Usage: 38.1 MB, less than 93.02% of Java online submissions for Wildcard Matching.
     * <p>
     * dp
     */
    private static class Solution3 extends Solution {

        @Override
        public boolean isMatch(String s, String p) {
            int m = s.length(), n = p.length();
            if (s.equals(p) || p.equals("*")) {
                return true;
            }
            if (s.isEmpty() || p.isEmpty()) {
                return false;
            }
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            for (int j = 1; j <= n; ++j) {
                int i = 1;
                char ch = p.charAt(j - 1);
                if (ch == '*') {
                    while (!dp[i - 1][j - 1] && i <= m) { // 如果前一步不匹配
                        ++i;
                    }
                    dp[i - 1][j] = dp[i - 1][j - 1];
                    while (i <= m) { // 前一步匹配
                        dp[i++][j] = true;
                    }
                } else if (ch == '?') {
                    for (; i <= m; ++i) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    for (; i <= m; ++i) {
                        dp[i][j] = dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1);
                    }
                }
            }
            return dp[m][n];
        }

    }

    /**
     * Runtime: 2 ms, faster than 100.00% of Java online submissions for Wildcard Matching.
     * Memory Usage: 38.1 MB, less than 93.02% of Java online submissions for Wildcard Matching.
     */
    private static class Solution4 extends Solution {

        public boolean isMatch(String s, String p) {
            if (s.equals(p) || p.equals("*")) {
                return true;
            }
            if (s.isEmpty() || p.isEmpty()) {
                return false;
            }
            int m = s.length(), n = p.length();
            int i = 0, j = 0;
            int starIdx = -1, sTmpIdx = -1;
            while (i < m) {
                if (j < n && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                    // i==j or j==?
                    ++i;
                    ++j;
                } else if (j < n && p.charAt(j) == '*') {
                    // when * matches no characters
                    starIdx = j;
                    sTmpIdx = i;
                    ++j;
                } else if (starIdx == -1) {
                    // if i != j or j >= n or pattern has no *
                    return false;
                } else {
                    // backtrack when * matches one more character
                    j = starIdx + 1;
                    i = sTmpIdx + 1;
                    sTmpIdx = i;
                }
            }
            // The remaining characters in the pattern should all be '*' characters
            for (; j < n; ++j) {
                if (p.charAt(j) != '*') {
                    return false;
                }
            }
            return true;
        }

    }

    private static void test1(String s, String p) {
        Solution so = new Solution4();
        println(so.isMatch(s, p));
    }

    public static void main(String[] args) {
        test1("aa", "a"); // F
        test1("aa", "*"); // T
        test1("cb", "?a"); // F
        test1("adceb", "*a*b"); // T
        test1("acdcb", "a*c?b"); // F
        test1("ho", "ho**"); // T
        test1("", ""); // T
        test1("a", ""); // F
        test1("aa", "a*"); // T
    }

}