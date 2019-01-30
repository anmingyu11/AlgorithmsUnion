package _java;

import java.util.HashSet;
import java.util.Set;

import base.Base;

public class _0036ValidSudoku extends Base {
    private abstract static class Solution {
        abstract boolean isValidSudoku(char[][] board);
    }

    //15ms beats 81%;
    private static class Solution1 extends Solution {

        boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (!isValid(board, i, j)) {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean isValid(char[][] board, int x, int y) {
            if (board[x][y] == '.') {
                return true;
            }
            int start = x / 3 * 3, end = y / 3 * 3;
            for (int i = 0; i < 9; ++i) {
                if (i != y && board[x][i] == board[x][y]) {
                    return false;
                }
                if (i != x && board[i][y] == board[x][y]) {
                    return false;
                }
                final int fX = start + i / 3, fY = end + i % 3;
                if (!(fX == x && fY == y) && board[fX][fY] == board[x][y]) {
                    return false;
                }
            }

            return true;
        }
    }

    // 还可以改,但是不管了,漏行了
    private static class Solution2 extends Solution {

        boolean isValidSudoku(char[][] board) {
            int[][] check = new int[][]{
                    {0, 0},
                    {0, 3},
                    {0, 6},
                    {3, 0},
                    {3, 3},
                    {3, 6},
                    {6, 0},
                    {6, 3},
                    {6, 6}
            };

            for (int i = 0; i < check.length; ++i) {
                if (!isValid(board, check[i][0], check[i][1])) {
                    return false;
                }
            }
            return true;
        }

        boolean isValid(char[][] board, int x, int y) {
            Set<Integer> setRow = new HashSet<>(9);
            Set<Integer> setCol = new HashSet<>(9);
            Set<Integer> setCube = new HashSet<>(9);
            for (int i = 0; i < 9; ++i) {
                if (i != y && board[x][i] != '.' && !setRow.add(board[x][i] - '0')) {
                    return false;
                }
                if (i != x && board[i][y] != '.' && !setCol.add(board[i][y] - '0')) {
                    return false;
                }
                final int fX = x + i / 3, fY = y + i % 3;
                if (!(fX == x && fY == y) && board[fX][fY] != '.' && !setCube.add(board[fX][fY] - '0')) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        char[][] board2 = new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        char[][] board1 = new char[][]{
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
        char[][] board3 = new char[][]{
                {'7', '.', '.', '.', '4', '.', '.', '.', '.'},
                {'.', '.', '.', '8', '6', '5', '.', '.', '.'},
                {'.', '1', '.', '2', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '9', '.', '.', '.'},
                {'.', '.', '.', '.', '5', '.', '5', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };

        Solution s = new Solution2();

        println(s.isValidSudoku(board1)); // true
        println(s.isValidSudoku(board2)); // false
        println(s.isValidSudoku(board3)); // false
    }
}
