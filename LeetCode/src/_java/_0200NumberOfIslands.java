package _java;

import base.Base;

public class _0200NumberOfIslands extends Base {

    private abstract static class Solution {
        public abstract int numIslands(char[][] grid);
    }

    // Runtime: 5 ms, faster than 50.38% of Java online submissions for Number of Islands.
    // Memory Usage: 26.1 MB, less than 86.59% of Java online submissions for Number of Islands.
    // UF
    private static class Solution1 extends Solution {

        private class UF {

            final int[][] direct = new int[][]
                    {
                            {0, -1},
                            {0, 1},
                            {1, 0},
                            {-1, 0},
                    };
            final int[][] g;
            int cnt;
            int row, col;

            // 1. 预处理,深度优先搜索
            // 2. 深搜的过程中标记连通分量.
            // 3. 同创建一个矩阵来标记
            UF(char[][] grid, int r, int c) {
                row = r;
                col = c;
                cnt = 1;
                g = new int[row][col];
                for (int i = 0; i < row; ++i) {
                    for (int j = 0; j < col; ++j) {
                        // 还没有被遍历
                        if (g[i][j] == 0) {
                            if (grid[i][j] == '1') {
                                // 深搜
                                dfs(grid, i, j);
                                ++cnt;
                            } else {
                                // 标记为负
                                g[i][j] = -1;
                            }
                        }
                    }
                }
            }

            void dfs(char[][] grid, int i, int j) {
                if (grid[i][j] == '1') {
                    g[i][j] = cnt;
                } else {
                    g[i][j] = -1;
                    return;
                }
                for (int d = 0; d < direct.length; ++d) {
                    int dx = direct[d][0];
                    int dy = direct[d][1];
                    final int x = i + dx;
                    final int y = j + dy;
                    if (x >= 0 && x < row && y >= 0 && y < col) {
                        if (g[x][y] == 0) {
                            dfs(grid, x, y);
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        public int numIslands(char[][] grid) {
            int r = grid.length;
            if (r == 0) {
                return 0;
            }
            int c = grid[0].length;
            if (c == 0) {
                return 0;
            }
            UF uf = new UF(grid, r, c);
            return uf.cnt - 1;
        }

    }

    // UF in-place
    // Runtime: 5 ms, faster than 50.38% of Java online submissions for Number of Islands.
    // Memory Usage: 26.1 MB, less than 86.19% of Java online submissions for Number of Islands.
    // in-place 的 居然一点改进都没有,可能用例太小了,重新创建数组的方法不需要多少内存.
    private static class Solution2 extends Solution {

        private class UF {

            final int[][] direct = new int[][]
                    {
                            {0, -1},
                            {0, 1},
                            {1, 0},
                            {-1, 0},
                    };
            int cnt;
            int row, col;

            // 1. 预处理,深度优先搜索
            // 2. 深搜的过程中标记连通分量.
            // 3. 同创建一个矩阵来标记
            UF(char[][] grid, int r, int c) {
                row = r;
                col = c;
                cnt = 0;
                for (int i = 0; i < row; ++i) {
                    for (int j = 0; j < col; ++j) {
                        if (grid[i][j] != '*') {
                            // 还没有被遍历
                            if (grid[i][j] == '1') {
                                // 深搜
                                dfs(grid, i, j);
                                ++cnt;
                            } else {
                                // 标记为* 代表已遍历过了
                                grid[i][j] = '*';
                            }
                        }
                    }
                }
            }

            void dfs(char[][] grid, int i, int j) {
                // 标记当前
                grid[i][j] = '*';
                for (int d = 0; d < direct.length; ++d) {
                    int dx = direct[d][0];
                    int dy = direct[d][1];
                    final int x = i + dx;
                    final int y = j + dy;
                    if (x >= 0 && x < row && y >= 0 && y < col) {
                        if (grid[x][y] != '*') {
                            if (grid[x][y] == '1') {
                                dfs(grid, x, y);
                            } else {
                                grid[x][y] = '*';
                            }
                        }
                    }
                }
            }
        }

        public int numIslands(char[][] grid) {
            int r = grid.length;
            if (r == 0) {
                return 0;
            }
            int c = grid[0].length;
            if (c == 0) {
                return 0;
            }
            UF uf = new UF(grid, r, c);
            return uf.cnt;
        }

    }

    public static void main(String[] args) {
        char[][] grid1 = new char[][]{
                "11110".toCharArray(),
                "11010".toCharArray(),
                "11000".toCharArray(),
                "00000".toCharArray()
        };
        char[][] grid2 = new char[][]{
                "11000".toCharArray(),
                "11000".toCharArray(),
                "00100".toCharArray(),
                "00011".toCharArray()
        };
        char[][] grid3 = {};
        char[][] grid4 = {{'1'}};
        char[][] grid5 = {{'0'}};

        Solution s = new Solution2();
        println(s.numIslands(grid1)); //1
        println(s.numIslands(grid2)); //3
        println(s.numIslands(grid3)); //0
        println(s.numIslands(grid4)); //1
        println(s.numIslands(grid5)); //0
    }
}
