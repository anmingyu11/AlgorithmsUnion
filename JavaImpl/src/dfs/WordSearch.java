package dfs;

import base.Base;

public class WordSearch extends Base {

    // Recursive dfs
    static class Solution1 {

        final int[][] direct = new int[][]{
                {0, -1},//left
                {1, 0}, //down
                {0, 1}, //right
                {-1, 0} //up
        };

        boolean[][] visited;

        public boolean exist(char[][] board, String word) {
            if (board.length * board[0].length < word.length()) {
                return false;
            }
            visited = new boolean[board.length][board[0].length];
            char[] words = word.toCharArray();
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (exist(board, words, 0, i, j)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean exist(char[][] board, char[] words, int pos, int i, int j) {
            //收敛条件
            if (pos == words.length) {
                return true;
            }
            //终止条件
            if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != words[pos] || visited[i][j]) {
                return false;
            }
            visited[i][j] = true;
            for (int k = 0; k < 4; ++k) {
                int[] di = direct[k];
                int newI = di[0] + i;
                int newJ = di[1] + j;
                if (exist(board, words, pos + 1, newI, newJ)) {
                    return true;
                }
            }
            visited[i][j] = false;
            return false;
        }

    }

    public static void main(String[] args) {
        //char[][] board = new char[][]{
        //        {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        //};
        //String word1 = "ABCCED";
        //String word2 = "SEE";
        //String word3 = "ABCB";
        //println(new Solution1().exist(board, word1));
        //println(new Solution1().exist(board, word2));
        //println(new Solution1().exist(board, word3));
        char[][] board1 = new char[][]{{'a'}};
        String word1 = "a";
        char[][] board2 = new char[][]{{'a'}, {'a'}};
        String word2 = "aaa";
        println(new Solution1().exist(board2, word2));
    }

}
