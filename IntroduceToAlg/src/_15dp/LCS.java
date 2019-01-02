package _15dp;

import base.Base;

public class LCS {

    static abstract class BaseSolution extends Base {

        char[] X = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] Y = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};
        // +1 是因为需要一个填充位置 一定要加1 当X为空或者Y为空时无法处理。
        final int m = X.length + 1;
        final int n = Y.length + 1;
        int[][] c = new int[m][n];
        char[][] b = new char[m][n];

        public BaseSolution() {
            println(" m : " + m);
            println(" n : " + n);
        }

        abstract void lcsLength();

        abstract void printLCS(int i, int j);

        void test() {
            lcsLength();

            print2DArr(c);
            print2DArr(b);

            printLCS(m - 1, n - 1);
        }

    }

    static class Solution1 extends BaseSolution {

        @Override
        void lcsLength() {

            for (int i = 1; i < m; ++i) {
                for (int j = 1; j < n; ++j) {
                    if (X[i - 1] == Y[j - 1]) {
                        c[i][j] = c[i - 1][j - 1] + 1;
                        b[i][j] = '↖';
                    } else if (X[i - 1] != Y[j - 1]) {
                        if (c[i - 1][j] >= c[i][j - 1]) {
                            c[i][j] = c[i - 1][j];
                            b[i][j] = '↑';
                        } else {
                            c[i][j] = c[i][j - 1];
                            b[i][j] = '←';
                        }
                    }
                }
            }

        }

        @Override
        void printLCS(int i, int j) {
            if (i == 0 || j == 0) {
                return;
            }

            if (b[i][j] == '↖') {
                printLCS(i - 1, j - 1);
                print(X[i - 1] + " ");
            } else if (b[i][j] == '↑') {
                printLCS(i - 1, j);
            } else {
                printLCS(i, j - 1);
            }

        }

        public static void main(String[] args) {
            Solution1 solution1 = new Solution1();
            solution1.test();
        }
    }
}
