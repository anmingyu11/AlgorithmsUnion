package _java;

import base.Base;

public class _1254NumberofClosedIslands extends Base {
    private static abstract class Solution {
        public abstract int closedIsland(int[][] grid);
    }

    private static class Solution1 extends Solution {

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

            UF(int[][] grid, int r, int c) {
                row = r;
                col = c;
                cnt = 0;
                for (int i = 0; i < row; ++i) {
                    for (int j = 0; j < col; ++j) {
                        int ele = grid[i][j];
                        if (ele == 0) {
                            // 只要不是水,就进行一次深搜
                            if (dfs(grid, i, j)) {
                                ++cnt;
                            }
                        }
                    }
                }
            }


            boolean dfs(int[][] grid, int i, int j) {
                // 如果不是水 标记成-1已访问
                grid[i][j] = -1;
                boolean res = true;
                for (int d = 0; d < direct.length; ++d) {
                    int dx = direct[d][0];
                    int dy = direct[d][1];
                    final int x = i + dx;
                    final int y = j + dy;
                    if (x >= 0 && x < row && y >= 0 && y < col) {
                        if (grid[x][y] == 0) {
                            res &= dfs(grid, x, y);
                        }
                    } else {
                        // 超越边界的话直接返回false,不是被水围起来的
                        res = false;
                    }
                }
                return res;
            }
        }


        public int closedIsland(int[][] grid) {
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
        int[][] grid1 = {
                {1, 1, 1, 1, 1, 1, 1, 0}
                , {1, 0, 0, 0, 0, 1, 1, 0}
                , {1, 0, 1, 0, 1, 1, 1, 0}
                , {1, 0, 0, 0, 0, 1, 0, 1}
                , {1, 1, 1, 1, 1, 1, 1, 0}
        };
        int[][] grid2 = {
                {0, 0, 1, 0, 0}
                , {0, 1, 0, 1, 0}
                , {0, 1, 1, 1, 0}
        };
        int[][] grid3 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        Solution s = new Solution1();
        println(s.closedIsland(grid1));
        println(s.closedIsland(grid2));
        println(s.closedIsland(grid3));
    }
}
