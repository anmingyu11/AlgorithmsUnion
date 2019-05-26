package _15DynamicProgramming;

import base.Base;

public class LCS extends Base {

    private abstract static class Solution {

        /**
         * @param X
         * @param Y
         * @param c c[i,j] 表示X(0..i)和Y(0..j)的LCS长度
         * @param b
         * @return
         */
        public abstract int lcsLength(String X, String Y, int[][] c, char[][] b);
    }

    private static class Checker extends Solution {

        @Override
        public int lcsLength(String X, String Y, int[][] c, char[][] b) {
            final int m = X.length();
            final int n = Y.length();

            return lcsLengthAux(X, Y, c, 0, 0, 0);
        }

        private int lcsLengthAux(String X, String Y, int[][] c, int xI, int yI, int lcsLen) {
            //Todo 换个头脑清醒的日子来做这个事.
//            if (xI >= X.length() || yI >= Y.length()) {
//                return lcsLen;
//            }
//            if (X.charAt(xI) == Y.charAt(yI)) {
//                lcsLengthAux(X, Y, c, ++xI, ++yI, c[xI][yI] = ++lcsLen);
//            } else {
//                int x = lcsLengthAux(X,Y,c,++xI,);
//                int y = lcsLengthAux(X,Y,c,++yI,);
//            }
            return 0;
        }

    }

    private static class Solution1 extends Solution {

        @Override
        public int lcsLength(String X, String Y, int[][] c, char[][] b) {
            final int m = X.length();
            final int n = Y.length();
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                        c[i][j] = c[i - 1][j - 1] + 1;
                        b[i][j] = '↖';
                    } else if (c[i - 1][j] >= c[i][j - 1]) {
                        c[i][j] = c[i - 1][j];
                        b[i][j] = '↑';
                    } else {
                        c[i][j] = c[i][j - 1];
                        b[i][j] = '←';
                    }
                }
            }
            return c[m][n];
        }

    }

    private static void testSolution(Solution s) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        final int m = X.length() + 1;
        final int n = Y.length() + 1;
        int[][] c = new int[m][n];
        char[][] b = new char[m][n];
        int len = s.lcsLength(X, Y, c, b);
        println("len : " + len);
        //printArr(b);
        printArr(c);
    }

    public static void main(String[] args) {

        Solution s = new Solution1();

        testSolution(s);
    }
}
