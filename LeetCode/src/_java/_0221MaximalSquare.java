package _java;

import base.Base;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * <pre>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * </pre>
 * Output: 4
 */
public class _0221MaximalSquare extends Base {

    private abstract static class Solution {
        public abstract int maximalSquare(char[][] matrix);
    }

    private static class Solution1 extends Solution {

        @Override
        public int maximalSquare(char[][] matrix) {
            final int m = matrix.length;
            if (m < 1) {
                return 0;
            }
            final int n = matrix[0].length;
            if (n < 1) {
                return 0;
            }
            int maxsqlen = 0;
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (matrix[i - 1][j - 1] == '1') {
                        // 这里代表除当前顶点(i,j)以外,Min(其他边的最短长度)
                        dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                        maxsqlen = Math.max(dp[i][j], maxsqlen);
                    }
                }
            }
            return maxsqlen * maxsqlen;
        }

    }

    /**
     * prev | dp[j]
     * dp[j-1] | the new dp[j]
     *  very subtle.
     */
    private static class Solution2 extends Solution {

        @Override
        public int maximalSquare(char[][] matrix) {
            final int m = matrix.length;
            if (m < 1) {
                return 0;
            }
            final int n = matrix[0].length;
            if (n < 1) {
                return 0;
            }
            int[] dp = new int[n + 1];
            int maxsqlen = 0, prev = 0;
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    int temp = dp[j];
                    if (matrix[i - 1][j - 1] == '1') {
                        // dp[j-1] 对应(i,j)的(i,j-1)
                        // dp[j]  对应(i,j)的(i-1,j)
                        // prev 对应(i,j)的(i-1,j-1)
                        dp[j] = Math.min(prev, Math.min(dp[j], dp[j - 1]))+1;
                        maxsqlen = Math.max(maxsqlen, dp[j]);
                    } else {
                        dp[j] = 0;
                    }
                    prev = temp;
                }
            }
            return maxsqlen * maxsqlen;
        }
    }

    public static void main(String[] args) {
        char[][] m1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        Solution s = new Solution1();
        println(s.maximalSquare(m1));
    }
}
