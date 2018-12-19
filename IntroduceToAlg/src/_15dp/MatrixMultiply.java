package _15dp;

import base.Base;

public class MatrixMultiply extends Base {

    static final int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};////记录6个矩阵的行和列，注意相邻矩阵的行和列是相同的

    //Bottom up
    static class Solution1 {

        final int n = p.length - 1;
        //这个 n 真的是缺了大德了 造成了他娘的无穷的麻烦
        final int[][] m = new int[n][n];//存储第i个矩阵到第j个矩阵的计算代价（以乘法次数来表示）
        final int[][] s = new int[n][n];//存储第i个矩阵到第j个矩阵的最小代价时的分为两部分的位置

        //Checker mChecker = new Checker();

        private void matrixChainOrder() {

            //无意义 在java中 数组初始化本身就是0
            for (int i = 0; i < n; ++i) {
                m[i][i] = 0;
            }

            // n = 6
            // 链长度，要计算m[0][5] 2 <= l <= 5;
            for (int l = 1; l < n; ++l) {
                // i从0开始， i + l < n 所以 i < n - l 为了保持代码风格一致，让 i < n - l
                for (int i = 0; i < n - l; ++i) {
                    // j <= i + l
                    int j = i + l;
                    m[i][j] = Integer.MAX_VALUE;
                    // 计算 m[i][j]
                    for (int k = i; k < j; ++k) {
                        int min = m[i][k] + p[i] * p[k + 1] * p[j + 1] + m[k + 1][j];
                        if (min < m[i][j]) {
                            m[i][j] = min;
                            s[i][j] = k + 1;
                        }
                    }
                }

            }

        }
    }

    static class Checker {

        int[][] realM = new int[][]{
                {0, 15750, 7875, 9375, 11875, 15125},
                {0, 0, 2625, 4375, 7125, 10500},
                {0, 0, 0, 750, 2500, 5375},
                {0, 0, 0, 0, 1000, 3500},
                {0, 0, 0, 0, 0, 5000},
                {0, 0, 0, 0, 0, 0},
        };

        int[][] realS = new int[][]{
                {0, 1, 1, 3, 3, 3},
                {0, 0, 2, 3, 3, 3},
                {0, 0, 0, 3, 3, 3},
                {0, 0, 0, 0, 4, 5},
                {0, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 0, 0}
        };

        private boolean checkM(int[][] m) {
            int n = m.length;
            boolean mReal = true;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    mReal = m[i][j] == realM[i][j];
                    if (!mReal) {
                        println("m is " + false);
                        return false;
                    }
                }
            }

            println("m is " + true);
            return true;
        }

        private boolean checkS(int[][] s) {
            int n = s.length;
            boolean sReal = true;

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    sReal = s[i][j] == realS[i][j];
                    if (!sReal) {
                        println("s is " + false);
                        return false;
                    }
                }
            }

            println("s is " + true);
            return true;
        }

        private boolean check(int[][] m, int[][] s) {
            return checkM(m) && checkS(s);
        }

    }

    public static void main(String[] args) {
        new Solution1().matrixChainOrder();
    }
}
