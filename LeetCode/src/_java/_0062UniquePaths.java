package _java;

import base.Base;

/**
 * A robot is located at the top-left corner of a
 * m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time.
 * <p>
 * The robot is trying to reach the bottom-right corner
 * of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * Example 1:
 * <p>
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * <p>
 * Example 2:
 * <p>
 * Input: m = 7, n = 3
 * Output: 28
 */
public class _0062UniquePaths extends Base {
    private abstract static class Solution {
        public abstract int uniquePaths(int m, int n);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
     * Memory Usage: 33 MB, less than 5.03% of Java online submissions for Unique Paths.
     */
    private static class Solution1 extends Solution {

        @Override
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 0; i <= m; ++i) {
                dp[i][1] = 1;
            }
            for (int i = 0; i <= n; ++i) {
                dp[1][i] = 1;
            }
            for (int i = 2; i <= m; ++i) {
                for (int j = 2; j <= n; ++j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m][n];
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public int uniquePaths(int m, int n) {
            return 0;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.uniquePaths(3, 2)); // 3
        println(s.uniquePaths(7, 3)); // 28
    }
}
