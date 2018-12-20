package _15dp;

import base.Base;

public class MatrixMultiply extends Base {


    static abstract class AbsSolution {

        static final int PINF = Integer.MAX_VALUE;
        final int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};////记录6个矩阵的行和列，注意相邻矩阵的行和列是相同的
        // n = 6 坑爹
        final int n = p.length - 1;
        final int[][] m = new int[n][n];//存储第i个矩阵到第j个矩阵的计算代价（以乘法次数来表示）
        final int[][] s = new int[n][n];//存储第i个矩阵到第j个矩阵的最小代价时的分为两部分的位置
        final Checker mChecker = new Checker();

        abstract void matrixChainOrder();

        void testResult(boolean printM, boolean printS) {
            println(this.getClass().getSimpleName() + " Test Start ---------- ");
            matrixChainOrder();
            if (printM) {
                print2DArr(m);
            }
            if (printS) {
                print2DArr(s);
            }
            mChecker.check(m, s);
        }
    }

    // Naive RECUR
    static class Solution1 extends AbsSolution {

        @Override
        void matrixChainOrder() {
            for (int i = 0; i < n; ++i) {
                m[i][i] = 0;
            }
            m[0][5] = matrixChainOrderAux(0, 5);
        }

        int matrixChainOrderAux(int i, int j) {
            if (i == j) {
                return 0;
            }
            int min = 0;
            m[i][j] = PINF;
            for (int k = i; k < j; ++k) {
                min = matrixChainOrderAux(i, k)
                        + matrixChainOrderAux(k + 1, j)
                        + p[i] * p[k + 1] * p[j + 1];
                if (min < m[i][j]) {
                    m[i][j] = min;
                    s[i][j] = k + 1;
                }
            }

            return m[i][j];
        }

    }

    // MEM UP-BOTTOM
    static class Solution2 extends AbsSolution {

        @Override
        void matrixChainOrder() {

            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    m[i][j] = Integer.MAX_VALUE;
                }
            }

            m[0][5] = matrixChainOrderAux(0, 5);
        }

        int matrixChainOrderAux(int i, int j) {
            if (m[i][j] < PINF) {
                return m[i][j];
            }

            int min = 0;
            for (int k = i; k < j; ++k) {
                min = matrixChainOrderAux(i, k)
                        + matrixChainOrderAux(k + 1, j)
                        + p[i] * p[k + 1] * p[j + 1];
                if (min < m[i][j]) {
                    m[i][j] = min;
                    s[i][j] = k + 1;
                }
            }

            return m[i][j];
        }
    }

    // Bottom up
    static class Solution3 extends AbsSolution {

        @Override
        void matrixChainOrder() {

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
                    m[i][j] = PINF;
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

        boolean checkM(int[][] m) {
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

        boolean checkS(int[][] s) {
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

        boolean check(int[][] m, int[][] s) {
            boolean ans = checkM(m) && checkS(s);
            println("answer is " + ans);
            return ans;
        }

    }

    public static void main(String[] args) {
        //new Solution1().testResult(true, false);
        //new Solution2().testResult(true, false);
        //new Solution3().testResult(false, false);
    }
}
