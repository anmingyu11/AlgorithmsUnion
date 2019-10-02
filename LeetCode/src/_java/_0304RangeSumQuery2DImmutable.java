package _java;

import base.Base;

public class _0304RangeSumQuery2DImmutable extends Base {
    private static abstract class NumMatrix {

        public NumMatrix(int[][] matrix) {

        }

        public abstract int sumRegion(int row1, int col1, int row2, int col2);
    }

    /**
     * Runtime: 59 ms, faster than 57.50% of Java online submissions for Range Sum Query 2D - Immutable.
     * Memory Usage: 41.8 MB, less than 100.00% of Java online submissions for Range Sum Query 2D - Immutable.:w
     */
    private static class Solution1 extends NumMatrix {

        private int[][] dp = new int[1][1];

        public Solution1(int[][] matrix) {
            super(matrix);
            if (matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int m = matrix.length, n = matrix[0].length;

            dp = new int[m][n + 1];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    dp[i][j + 1] = dp[i][j] + matrix[i][j];
                }
            }
        }

        @Override
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int r = row1; r <= row2; ++r) {
                sum += dp[r][col2 + 1] - dp[r][col1];
            }
            return sum;
        }
    }

    /**
     * Runtime: 55 ms, faster than 99.97% of Java online submissions for Range Sum Query 2D - Immutable.
     * Memory Usage: 41.6 MB, less than 100.00% of Java online submissions for Range Sum Query 2D - Immutable.
     */
    private static class Solution2 extends NumMatrix {

        private int[][] dp = new int[1][1];

        public Solution2(int[][] matrix) {
            super(matrix);
            if (matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int m = matrix.length, n = matrix[0].length;
            dp = new int[m + 1][n + 1];

            for (int i = 1; i <= m; ++i) {
                int sum = 0;
                for (int j = 1; j <= n; ++j) {
                    sum += matrix[i - 1][j - 1];
                    dp[i][j] = sum + dp[i - 1][j];
                }
            }
        }

        @Override
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }
    }

    public static void main(String[] args) {
        int[][] m1 = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix numMatrix = new Solution2(m1);
        println(numMatrix.sumRegion(2, 1, 4, 3)); // 8
        println(numMatrix.sumRegion(1, 1, 2, 2)); // 11
        println(numMatrix.sumRegion(1, 2, 2, 4)); // 12
    }
}
