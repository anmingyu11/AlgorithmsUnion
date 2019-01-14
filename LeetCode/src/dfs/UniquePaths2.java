package dfs;

import base.Base;

public class UniquePaths2 extends Base {

    static class Solution {
        public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid[0][0] == 1) {
                return 0;
            }

            int R = obstacleGrid.length;
            int C = obstacleGrid[0].length;

            obstacleGrid[0][0] = 1;

            for (int i = 1; i < R; ++i) {
                obstacleGrid[i][0] = obstacleGrid[i - 1][0] == 1 && obstacleGrid[i][0] == 0 ? 1 : 0;
            }

            for (int j = 1; j < C; ++j) {
                obstacleGrid[0][j] = obstacleGrid[0][j - 1] == 1 && obstacleGrid[0][j] == 0 ? 1 : 0;
            }

            for (int i = 1; i < R; ++i) {
                for (int j = 1; j < C; ++j) {
                    if (obstacleGrid[i][j] == 1) {
                        obstacleGrid[i][j] = 0;
                    } else {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                    }
                }
            }

            return obstacleGrid[R - 1][C - 1];
        }
    }

    public static void main(String[] args) {
        int[][] obstacles =
                new int[][]{
                        {0, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0}
                };
        println("uniquePath : " + Solution.uniquePathsWithObstacles(obstacles));
    }
}