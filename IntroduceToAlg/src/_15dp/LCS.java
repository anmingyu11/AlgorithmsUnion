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

        abstract void printLcs();

        void test() {
            lcsLength();
            print2DArr(c);
            print2DArr(b);
        }

    }

    static class Solution1 extends BaseSolution {

        @Override
        void lcsLength() {

            for (int i = 1; i < m; ++i) {
                for (int j = 1; j < n; ++j) {
                    if (X[i - 1] == Y[j - 1]) {
                        c[i][j] = c[i - 1][j - 1] + 1;
                    } else {
                        if (c[i - 1][j] >= c[i][j - 1]) {
                            c[i][j] = c[i - 1][j];
                        } else {
                            c[i][j] = c[i][j - 1];
                        }
                    }
                }
            }

        }

        @Override
        void printLcs() {

        }

    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.test();
    }
}
