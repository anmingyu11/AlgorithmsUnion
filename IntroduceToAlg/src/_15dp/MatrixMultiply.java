package _15dp;

import base.Base;

public class MatrixMultiply extends Base {

    final int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};////记录6个矩阵的行和列，注意相邻矩阵的行和列是相同的
    final int n = p.length - 1;
    final int[][] m = new int[n][n];//存储第i个矩阵到第j个矩阵的计算代价（以乘法次数来表示）
    final int[][] s = new int[n][n];//存储第i个矩阵到第j个矩阵的最小代价时的分为两部分的位置

    Checker mChecker = new Checker();

    private void matrixChainOrder() {
        for (int i = 0; i < n; ++i) {
            m[i][i] = 0;
        }

        for (int l = 2; l <= n; ++l) {
            //Todo cant get this
            for (int i = 0; i < n - l + 1; ++i) {
                //Todo cant get this
                int j = i + l - 1;
                int min = 0;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    min = m[i][k] + m[k + 1][j] + (p[i] * p[k + 1] * p[j + 1]);
                    if (min < m[i][j]) {
                        m[i][j] = min;
                        s[i][j] = k;
                    }
                }
            }
        }

        mChecker.checkM(m);
        print2DArr(m);
    }

    private static class Checker {
        int[][] realM = new int[][]{
                {0, 15750, 7875, 9375, 11875, 15125},
                {0, 0, 2625, 4375, 7125, 10500},
                {0, 0, 0, 750, 2500, 5375},
                {0, 0, 0, 0, 1000, 3500},
                {0, 0, 0, 0, 0, 5000},
                {0, 0, 0, 0, 0, 0},
        };

        int[][] realS = new int[][]{
                {0, 15750, 7875, 9375, 11875, 15125},
                {0, 0, 2625, 4375, 7125, 10500},
                {0, 0, 0, 750, 2500, 5375},
                {0, 0, 0, 0, 1000, 3500},
                {0, 0, 0, 0, 0, 5000},
                {0, 0, 0, 0, 0, 0},
        };

        private boolean checkM(int[][] m) {
            int n = m.length;
            boolean mReal = true;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    mReal = m[i][j] == realM[i][j];
                    if (!mReal) {
                        println("m is false");
                        return false;
                    }
                }
            }

            return mReal;
        }

        private boolean checkS(int[][] s) {
            int n = s.length;
            boolean sReal = true;

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    sReal = sReal && (s[i][j] == realS[i][j]);
                    if (!sReal) {
                        println("s is false");
                        return false;
                    }
                }
            }

            return sReal;
        }

        private boolean check(int[][] m, int[][] s) {
            return checkM(m) && checkS(s);
        }

    }

    public static void main(String[] args) {
        new MatrixMultiply().matrixChainOrder();
    }
}
