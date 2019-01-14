package dp;

import base.Base;

public class RangeSumQuery2D extends Base {

    static class NumMatrix {
        private int[][] dp;

        public NumMatrix(int[][] matrix) {
            final int row = matrix.length;
            if (row == 0) {
                return;
            }
            final int col = matrix[0].length;
            dp = new int[row + 1][col + 1];

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    dp[i + 1][j + 1] = matrix[i][j] + dp[i + 1][j] + dp[i][j + 1] - dp[i][j];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }

    }

    public static void main(String[] args) {
        int[][] matrix1 = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        int[][] matrix2 = {};
        int[][] matrix3 = {{1}};
        NumMatrix n = new NumMatrix(matrix1);
        //NumMatrix1 n2 = new NumMatrix1(matrix2);
        NumMatrix n3 = new NumMatrix(matrix3);
        println(n.sumRegion(2, 1, 4, 3));
        println(n.sumRegion(1, 1, 2, 2));
        println(n3.sumRegion(0, 0, 0, 0));
    }
}
