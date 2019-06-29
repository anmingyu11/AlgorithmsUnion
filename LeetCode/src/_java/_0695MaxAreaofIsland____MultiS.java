package _java;

import base.Base;

/**
 * Given a non-empty 2D array grid of 0's and 1's,
 * an island is a group of 1's (representing land) connected 4-directionally
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Find the maximum area of an island in the given 2D array.
 * (If there is no island, the maximum area is 0.)
 * <p>
 * Example 1:
 * <pre>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * </pre>
 * Given the above grid, return 6. Note the answer is not 11,
 * because the island must be connected 4-directionally.
 * Example 2:
 * <pre>
 * [[0,0,0,0,0,0,0,0]]
 * </pre>
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class _0695MaxAreaofIsland____MultiS extends Base {

    private abstract static class Solution {
        public abstract int maxAreaOfIsland(int[][] grid);
    }

    /**
     * Runtime: 3 ms, faster than 50.07% of Java online submissions for Max Area of Island.
     * Memory Usage: 44.5 MB, less than 48.42% of Java online submissions for Max Area of Island.
     */
    private static class Solution1 extends Solution {

        private int[][] di = {
                {1, 0}
                , {0, 1}
                , {-1, 0}
                , {0, -1}
        };
        private int m;
        private int n;
        private int max;

        public int maxAreaOfIsland(int[][] grid) {
            m = grid.length;
            if (m < 1) {
                return 0;
            }
            n = grid[0].length;
            if (n < 1) {
                return 0;
            }
            max = 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] > 0) {
                        max = Math.max(dfs(grid, i, j), max);
                    }
                }
            }
            return max;
        }

        private int dfs(int[][] grid, int i, int j) {
            if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == -1 || grid[i][j] == 0) {
                return 0;
            }
            int sum = 0;
            if (grid[i][j] > 0) {
                ++sum;
            }
            grid[i][j] = -1;
            for (int[] d : di) {
                // dfs four di
                sum += dfs(grid, i + d[0], j + d[1]);
            }
            //max = Math.max(sum, max);
            return sum;
        }

    }

    /**
     * Todo : UF
     */
    private static class Solution2 extends Solution {

        @Override
        public int maxAreaOfIsland(int[][] grid) {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        int[][] grid2 = {
                {0, 0, 0, 0, 0, 0, 0, 0}};
        int[][] grid3 = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};

        Solution s = new Solution1();
        println(s.maxAreaOfIsland(grid1));// 6
        println(s.maxAreaOfIsland(grid2));// 0
        println(s.maxAreaOfIsland(grid3));// 4
    }

}