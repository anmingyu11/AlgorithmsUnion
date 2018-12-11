package dfs;

import base.Base;

public class SudokuSolver extends Base {

    static class Solution1 {

        public void solveSudoku(char[][] board) {
            solve(board);
        }

        private boolean solve(char[][] board) {
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (board[i][j] == '.') {
                        for (char ch = '1'; ch <= '9'; ++ch) {
                            if (isValid(board, i, j, ch)) {
                                board[i][j] = ch;
                                if (solve(board)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isValid(char[][] board, int x, int y, char ch) {
            int startX = 3 * (x / 3), startY = 3 * (y / 3);
            for (int i = 0; i < 9; ++i) {
                if (i != x && board[i][y] == ch) {
                    return false;
                }
                if (i != y && board[x][i] == ch) {
                    return false;
                }
                if (board[startX + i / 3][startY + i % 3] == ch) {
                    return false;
                }
            }

            return true;
        }
    }

    static class Solution2 {

        public void solveSudoku(char[][] board) {
            solve(board, 0, 0);
        }

        private boolean solve(char[][] board, int i, int j) {
            if (i == 9) {
                return true;
            }
            if (j == 9) {
                return solve(board, i + 1, 0);
            }
            if (board[i][j] == '.') {
                for (char ch = '1'; ch <= '9'; ++ch) {
                    if (isValid(board, i, j, ch)) {
                        board[i][j] = ch;
                        if (solve(board, i, j + 1)) {
                            return true;
                        }
                        //else
                        board[i][j] = '.';
                    }
                }
            } else {
                return solve(board, i, j + 1);
            }

            return false;
        }

        private boolean isValid(char[][] board, int x, int y, char ch) {
            int startX = 3 * (x / 3), startY = 3 * (y / 3);
            for (int i = 0; i < 9; ++i) {
                if (i != x && board[i][y] == ch) {
                    return false;
                }
                if (i != y && board[x][i] == ch) {
                    return false;
                }
                if (board[startX + i / 3][startY + i % 3] == ch) {
                    return false;
                }
            }

            return true;
        }

    }

    public static void main(String[] args) {
        char[][] board = new char[][]
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };
        new Solution2().solveSudoku(board);
        print(board);
    }
}
