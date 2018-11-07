package array;

import java.util.Arrays;

import base.Base;

public class GameOfLife extends Base {

    static class Solution1 {

        public static void gameOfLife(int[][] board) {
            if (board == null || board.length == 0) return;
            int m = board.length, n = board[0].length;

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int lives = liveNeighbors(board, m, n, i, j);

                    if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                        board[i][j] = 3;
                    }
                    if (board[i][j] == 0 && lives == 3) {
                        board[i][j] = 2;
                    }
                }
            }

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    board[i][j] >>= 1;
                }
            }
        }

        public static int liveNeighbors(int[][] board, int m, int n, int i, int j) {
            int lives = 0;
            for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
                for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                    lives += board[x][y] & 1;
                }
            }
            lives -= board[i][j] & 1;
            return lives;
        }

        public static void main(String[] args) {
            int[][] board1 = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
            gameOfLife(board1);
            for (int[] arr : board1) {
                println(Arrays.toString(arr));
            }
        }
    }


    static class Solution2 {

        public static void gameOfLife(int[][] board) {
            int m = board.length, n = board[0].length;
            final int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1},{0,-1}};

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {

                    int live = 0;
                    for (int[] d : dir) {
                        int x = i + d[0], y = j + d[1];
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            //边界检查
                            continue;
                        }

                        if (board[x][y] == 1 || board[x][y] == 2) {
                            //1和2对应的活状态。
                            ++live;
                        }

                    }

                    if (board[i][j] == 0 && live == 3) {
                        //死周围有三个活，死复活
                        board[i][j] = 3;
                    }
                    if (board[i][j] == 1 && (live < 2 || live > 3)) {
                        //活周围有2或3个活则存活，否则死
                        board[i][j] = 2;
                    }
                }
            }

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    board[i][j] %= 2;
                }
            }
        }

        public static void main(String[] args) {
            int[][] board1 = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
            gameOfLife(board1);
            for (int[] arr : board1) {
                println(Arrays.toString(arr));
            }
        }
    }

}
