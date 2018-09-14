package _1array;

import java.util.HashSet;

import _0base.Base;

public class ValidSudoku extends Base {

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            // 1.验证行
            HashSet<Character> row = new HashSet<>();
            // 2. 验证列
            HashSet<Character> column = new HashSet<>();
            // 3. 验证cude
            HashSet<Character> cube = new HashSet<>();
            //下面这段，在下佩服
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.' && !row.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !column.add(board[j][i])) {
                    return false;
                }
                int rowI = 3 * (i / 3);
                int columnI = 3 * (i % 3);
                if (board[rowI + j / 3][columnI + j % 3] != '.'
                        && !cube.add(board[rowI + j / 3][columnI + j % 3])) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        println(" " + 2 % 3);
        println(" " + 0 % 3);
    }
}
