package dfs;

import base.Base;

public class UniquePaths extends Base {
    // DFS
    static class Solution1 {

        public static int uniquePaths(int m, int n) {
            if (m == 1 && n == 1) {// 收敛条件
                return 1;
            }
            if (m < 1 || n < 1) { //深搜的回退条件
                return 0;
            }

            int path1 = uniquePaths(m - 1, n);
            int path2 = uniquePaths(m, n - 1);

            return path1 + path2;
        }

    }

    // Memorize
    static class Solution2 {
        private static int[][] mem;

        public static int uniquePaths(int m, int n) {
            mem = new int[m + 1][n + 1];
            return dfs(m, n);
        }

        private static int dfs(int m, int n) {
            if (m < 1 || n < 1) {
                return 0;
            }
            if (m == 1 && n == 1) {
                return 1;
            }

            if (mem[m][n] != 0) {
                return mem[m][n];
            } else {
                return mem[m][n] = dfs(m - 1, n) + dfs(m, n - 1);
            }
        }
    }

    static class Solution3 {
        public static int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }

            return dp[m - 1][n - 1];
        }
    }

    public static void main(String[] args) {
        int m1 = 3, m2 = 2;
        println("uniquePath : " + Solution3.uniquePaths(m1, m2));
    }
}