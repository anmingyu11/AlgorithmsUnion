package _java;

import java.util.Arrays;

import base.Base;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * <p>
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class _0097InterleavingString extends Base {

    private abstract static class Solution {
        public abstract boolean isInterleave(String s1, String s2, String s3);
    }

    // Brute Force.
    private static class Solution1 extends Solution {

        @Override
        public boolean isInterleave(String s1, String s2, String s3) {
            return isInterleaveAux(s1, s2, s3, 0, 0, "");
        }

        private boolean isInterleaveAux(String s1, String s2, String s3, int i, int j, String res) {
            if (s3.equals(res) && i == s1.length() && j == s2.length()) {
                return true;
            }
            boolean ans = false;
            if (i < s1.length()) {
                ans |= isInterleaveAux(s1, s2, s3, i + 1, j, res + s1.charAt(i));
            }
            if (j < s2.length()) {
                ans |= isInterleaveAux(s1, s2, s3, i, j + 1, res + s2.charAt(j));
            }
            return ans;
        }

    }

    /**
     * Runtime: 1 ms, faster than 93.80% of Java online submissions for Interleaving String.
     * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Interleaving String.
     * dp的设计很重要 , i,j代表的是s1_i,s2_j是否能够构成s3的一部分.
     */
    private static class Solution2 extends Solution {

        private int[][] dp;

        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length(), n = s2.length();
            dp = new int[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dp[i], -1);
            }
            return isInterleaveAux(s1, s2, s3, 0, 0, 0);
        }

        private boolean isInterleaveAux(String s1, String s2, String s3, int i, int j, int k) {
            if (i == s1.length()) {
                return s3.substring(k).equals(s2.substring(j));
            }
            if (j == s2.length()) {
                return s3.substring(k).equals(s1.substring(i));
            }
            if (dp[i][j] >= 0) {
                return dp[i][j] == 1;
            }
            boolean ans = false;
            if ((s1.charAt(i) == s3.charAt(k) && isInterleaveAux(s1, s2, s3, i + 1, j, k + 1))
                    || s2.charAt(j) == s3.charAt(k) && isInterleaveAux(s1, s2, s3, i, j + 1, k + 1)) {
                ans = true;
            }
            dp[i][j] = ans ? 1 : 0;
            return ans;
        }

    }

    /**
     * Runtime: 2 ms, faster than 87.21% of Java online submissions for Interleaving String.
     * Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Interleaving String.
     * dp[i][j] 代表当前i,j能否interleave构成 s3的i+j-1部分.
     */
    private static class Solution3 extends Solution {

        @Override
        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length(), n = s2.length();
            if (m + n != s3.length()) {
                return false;
            }
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            for (int i = 1; i <= m; ++i) {
                dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            }
            for (int j = 1; j <= n; ++j) {
                dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
            }
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    dp[i][j] =
                            dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                                    || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
            return dp[m][n];
        }
    }

    /**
     *
     */
    private static class Solution4 extends Solution {

        @Override
        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length(), n = s2.length();
            if (m + n != s3.length()) {
                return false;
            }
            boolean[] dp = new boolean[n + 1];
            for (int i = 0; i <= m; ++i) {
                for (int j = 0; j <= n; ++j) {
                    if (i == 0 && j == 0) {
                        dp[j] = true;
                    } else if (i == 0) {
                        // j - 1 此为 dp[i , j-1]
                        dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                    } else if (j == 0) {
                        // j 此为 dp[i-1 , j]
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                    } else {
                        dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                                || dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[n];
        }

    }

    private static void test(String s1, String s2, String s3) {
        Solution s = new Solution4();
        println(s.isInterleave(s1, s2, s3));
    }

    public static void main(String[] args) {
        test("aabcc", "dbbca", "aadbbcbcac"); // T
        test("aabcc", "dbbca", "aadbbbaccc"); // F
        test("aabc", "abad", "aabadabc"); // T
        test("", "", "a"); // F
        test("", "b", "b"); // T
        test("a", "", "c"); // F
    }
}