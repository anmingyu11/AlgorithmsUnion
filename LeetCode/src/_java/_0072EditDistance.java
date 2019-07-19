package _java;

import base.Base;

/**
 * Given two words word1 and word2,
 * find the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class _0072EditDistance extends Base {

    private abstract static class Solution {
        public abstract int minDistance(String word1, String word2);
    }

    private static class Solution1 extends Solution {

        @Override
        public int minDistance(String word1, String word2) {
            final int m = word1.length(), n = word2.length();
            if (m * n == 0) {
                return m + n;
            }
            // 动态规划
            // dp[i][j]表示word1[0,i],word2[0,j]的 编辑距离
            // if chari==charj then dp[i][j] = min(dp[i-1][j],dp[i][j-1],dp[i-1]dp[i-1])
            // if chari != charj then 1 + min(dp[i-1][j],dp[i][j-1],dp[i-1]dp[i-1])
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; ++i) {
                dp[i][0] = i;
            }
            for (int j = 1; j <= n; ++j) {
                dp[0][j] = j;
            }
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        // 如果不等,dp[i][j-1]或dp[i-1][j] + 1,
                        // 这两位本身会因为长度的原因会有1不同,当前位置也不同说明此点需要添加1
                        // dp[i-1][j-1]由于长度相同+1
                        dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                    } else {
                        // 前两位同上
                        // 但是dp[i-1][j-1]因为当前位置的字符也相同,所以直接取dp[i-1][j-1],化简后得到下面式子
                        dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1] - 1);
                    }
                }
            }

            return dp[m][n];
        }

    }

    private static void test(String word1, String word2) {
        Solution s = new Solution1();
        println(s.minDistance(word1, word2));
    }

    public static void main(String[] args) {
        test("horse", "ros");// 3
        test("intention", "execution");// 5
        test("zoologicoarchaeologist", "zoogeologist");// 10
    }
}
