package bfs;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;

import base.Base;

public class SurroundedRegions extends Base {


    static class BaseSolution {
        static char H = 'h';
        static char X = 'X';
        static char O = 'O';
        static int row = 0;
        static int col = 0;

        static final int[][] direct = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
    }

    // state machine and DFS
    static class Solution1 extends BaseSolution {

        public static void solve(char[][] board) {
            row = board.length;
            if (row == 0) {
                return;
            }
            col = board[0].length;

            // check the col0 and col-1
            for (int i = 0; i < row; ++i) {
                check(board, i, 0);
                if (col > 1) {
                    check(board, i, col - 1);
                }
            }

            //check the row0 and row -1
            for (int j = 1; j < col - 1; ++j) {
                check(board, 0, j);
                if (row > 1) {
                    check(board, row - 1, j);
                }
            }

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (board[i][j] == '1') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private static void check(char[][] board, int i, int j) {

            if (board[i][j] == 'O') {
                board[i][j] = '1';
                for (int d = 0; d < direct.length; ++d) {
                    int newI = i + direct[d][0];
                    int newJ = j + direct[d][1];
                    if (newI < row && newI >= 0 && newJ < col && newJ >= 0) {
                        check(board, i + direct[d][0], j + direct[d][1]);
                    }
                }
            }
        }

    }

    // BFS
    static class Solution2 extends BaseSolution {
        static int row = 0;
        static int col = 0;

        static final int[][] direct = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        public static void solve(char[][] board) {
            row = board.length;
            if (row == 0) {
                return;
            }
            col = board[0].length;
            Queue<Point> queue = new ArrayDeque<>();

            //col 0 and col-1 in the queue
            for (int i = 0; i < row; ++i) {
                if (board[i][0] == 'O') {
                    board[i][0] = '1';
                    queue.offer(new Point(i, 0));
                }
                if (col > 1 && board[i][col - 1] == 'O') {
                    board[i][col - 1] = '1';
                    queue.offer(new Point(i, col - 1));
                }
            }
            //row 0 and row-1 in the queue
            for (int j = 1; j < col - 1; ++j) {
                if (board[0][j] == 'O') {
                    board[0][j] = '1';
                    queue.offer(new Point(0, j));
                }
                if (row > 1 && board[row - 1][j] == 'O') {
                    board[row - 1][j] = '1';
                    queue.offer(new Point(row - 1, j));
                }
            }

            while (!queue.isEmpty()) {
                Point p = queue.poll();
                for (int[] di : direct) {
                    int x = p.x + di[0];
                    int y = p.y + di[1];
                    if (x >= 0 && x < row && y >= 0 && y < col && board[x][y] == 'O') {
                        board[x][y] = '1';
                        queue.offer(new Point(x, y));
                    }
                }

            }

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '1') {
                        board[i][j] = 'O';
                    }
                }
            }

        }
    }

    //UF
    static class Solution3 {

        static int row = 0;
        static int col = 0;
        static final int[][] direct = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        static class UF {
            int[] parent;
            byte[] sz;
            int count;

            public UF(int n) {
                count = n;
                parent = new int[n];
                sz = new byte[n];
                for (int i = 0; i < n; ++i) {
                    parent[i] = i;
                    sz[i] = 0;
                }
            }

            public int find(int p) {
                while (parent[p] != p) {
                    parent[p] = parent[parent[p]];
                    p = parent[p];
                }
                return p;
            }

            public boolean connect(int p, int q) {
                return find(p) == find(q);
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);

                if (rootP == rootQ) {
                    return;
                }

                if (sz[rootP] < sz[rootQ]) {
                    parent[rootP] = rootQ;
                } else if (sz[rootP] > sz[rootQ]) {
                    parent[rootQ] = rootP;
                } else {
                    parent[rootQ] = rootP;
                    ++sz[rootP];
                }
                --count;
            }

        }

        public static void solve(char[][] board) {
            row = board.length;
            if (row == 0) {
                return;
            }
            col = board[0].length;
            int edge = col * row;
            UF uf = new UF(edge + 1);

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    boolean onEdge = (i == 0 || i == row - 1 || j == 0 || j == col - 1);
                    //将所有边界结点 union到edge结点
                    if (onEdge && board[i][j] == 'O') {
                        uf.union(convertUFIndex(i, j), edge);
                    } else if (board[i][j] == 'O') {
                        // 将所有非边界结点遍历
                        for (int[] d : direct) {
                            int x = i + d[0];
                            int y = j + d[1];
                            if (board[x][y] == 'O') {
                                uf.union(convertUFIndex(i, j), convertUFIndex(x, y));
                            }
                        }
                    }
                }

            }

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (!uf.connect(convertUFIndex(i, j), edge)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }


        private static int convertUFIndex(int i, int j) {
            return i * col + j;
        }

    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        char[][] board2 = new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        };
        char[][] board3 = new char[][]{
                {'X', 'O', 'X', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'}};
        test(board3);
    }

    public static void test(char[][] board) {
        println("=== before ===");
        print(board);
        println("=== after ===");
        Solution3.solve(board);
        print(board);
    }

}
