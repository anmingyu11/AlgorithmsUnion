package _java;

import base.Base;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes
 * the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class _0064MinimumPathSum extends Base {

    private abstract static class Solution {
        public abstract int minPathSum(int[][] grid);
    }

    /**
     * Runtime: 2 ms, faster than 93.77% of Java online submissions for Minimum Path Sum.
     * Memory Usage: 40.9 MB, less than 92.37% of Java online submissions for Minimum Path Sum.
     */
    private static class Solution1 extends Solution {

        @Override
        public int minPathSum(int[][] grid) {
            final int m = grid.length;
            if (m == 0) {
                return 0;
            }
            final int n = grid[0].length;
            if (n == 0) {
                return 0;
            }

            int[][] dp = new int[m + 1][n + 1];

            for (int i = 0; i <= m; ++i) {
                dp[i][0] = Integer.MAX_VALUE;
            }
            for (int j = 0; j <= n; ++j) {
                dp[0][j] = Integer.MAX_VALUE;
            }
            dp[0][1] = 0;
            dp[1][0] = 0;
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
                }
            }

            return dp[m][n];
        }

    }

    /**
     * Runtime: 2 ms, faster than 93.72% of Java online submissions for Minimum Path Sum.
     * Memory Usage: 41.1 MB, less than 91.51% of Java online submissions for Minimum Path Sum.
     */
    private static class Solution2 extends Solution {

        @Override
        public int minPathSum(int[][] grid) {
            final int m = grid.length;
            if (m == 0) {
                return 0;
            }
            final int n = grid[0].length;
            if (n == 0) {
                return 0;
            }
            int[] dp = new int[n];
            for (int i = m - 1; i >= 0; --i) {
                for (int j = n - 1; j >= 0; --j) {
                    if (i == m - 1 && j != n - 1) {
                        dp[j] = grid[i][j] + dp[j + 1];
                    } else if (i != m - 1 && j == n - 1) {
                        dp[j] = grid[i][j] + dp[j];
                    } else if (i != m - 1 && j != n - 1) {
                        dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                    } else {
                        dp[j] = grid[i][j];
                    }
                }
            }
            return dp[0];
        }

    }

    /**
     * Runtime: 2 ms, faster than 93.72% of Java online submissions for Minimum Path Sum.
     * Memory Usage: 41.1 MB, less than 91.69% of Java online submissions for Minimum Path Sum.
     */
    private static class Solution3 extends Solution {

        @Override
        public int minPathSum(int[][] grid) {
            final int m = grid.length;
            if (m == 0) {
                return 0;
            }
            final int n = grid[0].length;
            if (n == 0) {
                return 0;
            }
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (i == m - 1 && j != n - 1)
                        grid[i][j] = grid[i][j] + grid[i][j + 1];
                    else if (j == n - 1 && i != m - 1)
                        grid[i][j] = grid[i][j] + grid[i + 1][j];
                    else if (j != n - 1 && i != m - 1)
                        grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j], grid[i][j + 1]);
                }
            }
            return grid[0][0];
        }

    }

    public static void main(String[] args) {
        int[][] a1 =
                {
                        {1, 3, 1},
                        {1, 5, 1},
                        {4, 2, 1}
                };

        Solution s = new Solution3();

        println(s.minPathSum(a1)); // 7

    }
}
