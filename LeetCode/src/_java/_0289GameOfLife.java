package _java;

import base.Base;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following
 * four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously.
 */
public class _0289GameOfLife extends Base {

    private abstract static class Solution {
        public abstract void gameOfLife(int[][] board);
    }

    // Simple
    // Runtime: 1 ms, faster than 100.00% of Java online submissions for Game of Life.
    // Memory Usage: 37.2 MB, less than 79.33% of Java online submissions for Game of Life.
    private static class Solution1 extends Solution {

        final int[][] direct = {
                {-1, -1},
                {0, -1},
                {1, -1},
                {-1, 0},
                {1, 0},
                {-1, 1},
                {0, 1},
                {1, 1}
        };

        private int m = 0;
        private int n = 0;

        @Override
        public void gameOfLife(int[][] board) {
            m = board.length;
            if (m == 0) {
                return;
            }
            n = board[0].length;
            int[][] copy = new int[m][n];

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (live(board, i, j)) {
                        copy[i][j] = 1;
                    } else {
                        copy[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < m; ++i) {
                board[i] = copy[i];
            }
        }

        private boolean live(int[][] board, int i, int j) {

            // 原来是多少
            int origin = board[i][j];
            // 活着的邻居
            int liveNeighbor = 0;
            // 计算一圈
            for (int[] d : direct) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 1) {
                    ++liveNeighbor;
                }
            }

            if (origin == 1) {
                if (liveNeighbor < 2) {
                    return false;
                } else if (liveNeighbor <= 3) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (liveNeighbor == 3) {
                    return true;
                } else {
                    return false;
                }
            }

        }

    }

    // Runtime: 1 ms, faster than 100.00% of Java online submissions for Game of Life.
    // Memory Usage: 37.2 MB, less than 65.37% of Java online submissions for Game of Life.
    // 丑陋版原地
    private static class Solution2 extends Solution {

        final int[][] direct = {
                {-1, -1},
                {0, -1},
                {1, -1},
                {-1, 0},
                {1, 0},
                {-1, 1},
                {0, 1},
                {1, 1}
        };

        private int m = 0;
        private int n = 0;

        @Override
        public void gameOfLife(int[][] board) {
            m = board.length;
            if (m == 0) {
                return;
            }
            n = board[0].length;

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    live(board, i, j);
                }
            }
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int val = board[i][j];
                    if (val == 2) {
                        board[i][j] = 0;
                    } else if (val == -1) {
                        board[i][j] = 1;
                    }
                }
            }
        }

        private void live(int[][] board, int i, int j) {
            // 原来是多少
            int origin = board[i][j];
            // 活着的邻居
            int liveNeighbor = 0;
            // 计算一圈
            for (int[] d : direct) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] >= 1) {
                    ++liveNeighbor;
                }
            }

            if (origin >= 1) {
                if (liveNeighbor < 2) {
                    board[i][j] = 2;
                } else if (liveNeighbor > 3) {
                    board[i][j] = 2;
                }
            } else {
                // dead
                if (liveNeighbor == 3) {
                    board[i][j] = -1;
                }
            }

        }
    }

    // 优雅版
    // Runtime: 1 ms, faster than 100.00% of Java online submissions for Game of Life.
    // Memory Usage: 37.2 MB, less than 82.95% of Java online submissions for Game of Life.
    private static class Solution3 extends Solution {

        @Override
        public void gameOfLife(int[][] board) {
            final int m = board.length;
            if (m == 0) {
                return;
            }
            final int n = board[0].length;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int lives = 0;
                    // 技巧1 遍历3*3的格子
                    for (int y = Math.max(0, i - 1); y < Math.min(i + 2, m); ++y) {
                        for (int x = Math.max(0, j - 1); x < Math.min(j + 2, n); ++x) {
                            // 技巧2 0,1两个值进行按位与,这样只需要写一行
                            lives += board[y][x] & 1;
                        }
                    }
                    if (lives == 3 || lives - board[i][j] == 3) {
                        // 技巧3 0,1之前或一个1代表这个结束状态是活
                        board[i][j] |= 0b10;
                    }
                }
            }
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    board[i][j] >>= 1;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] m1 = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        Solution s = new Solution3();

        s.gameOfLife(m1);
        printArr(m1);
    }

}
