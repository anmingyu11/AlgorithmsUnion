package dp;

import base.Base;

public class MinimumPathSum extends Base {

    // Mine
    static class Solution1 {
        public int minPathSum(int[][] grid) {
            final int row = grid.length;
            if (row == 0) {
                return 0;
            }
            final int col = grid[0].length;
            int[][] dp = new int[row][col];

            dp[0][0] = grid[0][0];
            for (int i = 1; i < row; ++i) {
                dp[i][0] = grid[i][0] + dp[i - 1][0];
            }

            for (int j = 1; j < col; ++j) {
                dp[0][j] = grid[0][j] + dp[0][j - 1];
            }

            for (int i = 1; i < row; ++i) {
                for (int j = 1; j < col; ++j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }

            return dp[row - 1][col - 1];
        }
    }

    //No extra space
    static class Solution2 {
        public int minPathSum(int[][] grid) {
            final int row = grid.length;
            if (row == 0) {
                return 0;
            }
            final int col = grid[0].length;

            for (int i = 1; i < row; ++i) {
                grid[i][0] += grid[i - 1][0];
            }

            for (int j = 1; j < col; ++j) {
                grid[0][j] += grid[0][j - 1];
            }

            for (int i = 1; i < row; ++i) {
                for (int j = 1; j < col; ++j) {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }

            return grid[row - 1][col - 1];
        }
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        println(new Solution1().minPathSum(grid1));
    }
}
