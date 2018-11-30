package dfs;

import java.util.HashSet;
import java.util.Set;

import base.Base;

public class NQueens2 extends Base {

    static class Solution1 {
        private int solution = 0;

        public int totalNQueens(int n) {
            int[][] board = new int[n][n];

            dfs(board, 0);

            return solution;
        }

        private void dfs(int[][] board, int colIndex) {
            if (colIndex == board[0].length) {
                ++solution;
                return;
            }

            for (int i = 0; i < board.length; ++i) {
                if (validate(board, i, colIndex)) {
                    board[i][colIndex] = 3;
                    dfs(board, colIndex + 1);
                    board[i][colIndex] = 0;
                }
            }

        }

        private boolean validate(int[][] board, int x, int y) {
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < y; ++j) {
                    if (board[i][j] == 3
                            && (x == i || x + y == i + j || x + j == y + i)) {
                        return false;
                    }
                }
            }

            return true;
        }

    }

    static class Solution2 {
        private final Set<Integer> cols = new HashSet<>();
        private final Set<Integer> diags = new HashSet<>();
        private final Set<Integer> antiDiags = new HashSet<>();

        public int totalNQueens(int n) {
            return backtrack(0, 0, n);
        }

        private int backtrack(int row, int count, int n) {

            for (int col = 0; col < n; ++col) {
                if (cols.contains(col)) {
                    continue;
                }
                int diag = row - col;
                if (diags.contains(diag)) {
                    continue;
                }
                int antiDiag = col + row;
                if (antiDiags.contains(antiDiag)) {
                    continue;
                }

                if (row == n - 1) {
                    return ++count;
                } else {
                    cols.add(col);
                    diags.add(diag);
                    antiDiags.add(antiDiag);
                    count = backtrack(row + 1, count, n);
                    cols.remove(col);
                    diags.remove(diag);
                    antiDiags.remove(antiDiag);
                }
            }

            return count;
        }

    }

    static class Solution3 {
        int N;
        boolean[] cols;
        boolean[] diags;
        boolean[] antiDiags;

        public int totalNQueens(int n) {
            this.N = n;
            cols = new boolean[N];
            diags = new boolean[2 * N];
            antiDiags = new boolean[2 * N];
            return backtracking(0, 0);
        }

        public int backtracking(int row, int count) {

            for (int col = 0; col < N; ++col) {
                if (cols[col]) {
                    continue;
                }
                int diag = (col - row);
                diag = diag >= 0 ? diag : N - diag;
                if (diags[diag]) {
                    continue;
                }
                int antiDiag = col + row;
                if (antiDiags[antiDiag]) {
                    continue;
                }

                if (row == N - 1) {
                    return ++count;
                } else {
                    cols[col] = true;
                    diags[diag] = true;
                    antiDiags[antiDiag] = true;
                    count = backtracking(row + 1, count);
                    cols[col] = false;
                    diags[diag] = false;
                    antiDiags[antiDiag] = false;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {

        //println(new Solution1().totalNQueens(1));
        //println(new Solution1().totalNQueens(4));

        // println(new Solution2().totalNQueens(1));
        println(new Solution3().totalNQueens(4));
    }
}