package _15DynamicProgramming;

import base.Base;
import base.util.ArraysUtil;

public class MatrixChainOrder extends Base {

    private abstract static class Solution {
        public final int[] p = {30, 35, 15, 5, 10, 20, 25};

        public abstract int matrixChain(int i, int j);
    }

    private static class Checker extends Solution {

        public int matrixChain(int i, int j) {
            if (i == j) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for (int k = i; k < j; ++k) {
                min = Math.min(min, matrixChain(i, k) + p[i - 1] * p[k] * p[j] + matrixChain(k + 1, j));
            }
            return min;
        }

    }

    /**
     * Memorized
     */
    private static class Solution1 extends Solution {

        public int matrixChain(int i, int j) {
            final int n = p.length;
            Integer[][] m = new Integer[n][n];
            return matrixChainAux(m, i, j);
        }

        private int matrixChainAux(Integer[][] m, int i, int j) {
            if (m[i][j] != null) {
                return m[i][j];
            }
            if (i == j) {
                return m[i][j] = 0;
            }
            int min = Integer.MAX_VALUE;
            for (int k = i; k < j; ++k) {
                min = Math.min(min, matrixChainAux(m, i, k) + p[i - 1] * p[k] * p[j] + matrixChainAux(m, k + 1, j));
            }
            return m[i][j] = min;
        }
    }

    /**
     * B-U
     * 这个按照书中的代码来写,不单计算m[i][j]之间的代价
     */
    private static class Solution2 extends Solution {
        @Override
        public int matrixChain(int start, int end) {
            final int n = p.length;
            int[][] m = new int[n][n];
            for (int l = 2; l < n; ++l) {
                for (int i = 1; i < n - l + 1; ++i) {
                    int j = i + l - 1;
                    m[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; ++k) {
                        m[i][j] = Math.min(m[i][j], m[i][k] + p[i - 1] * p[k] * p[j] + m[k + 1][j]);
                    }
                }
            }
            return m[start][end];
        }
    }

    private static void test(Solution s) {
        Checker c = new Checker();
        int[][] m1 = new int[7][7];
        for (int i = 1; i <= 6; ++i) {
            for (int j = i; j <= 6; ++j) {
                m1[i][j] = c.matrixChain(i, j);
            }
        }
        int[][] m2 = new int[7][7];
        for (int i = 1; i <= 6; ++i) {
            for (int j = i; j <= 6; ++j) {
                m2[i][j] = s.matrixChain(i, j);
            }
        }

        println("==== yours ====");
        print2DArr(m2);
        println("==== real ====");
        print2DArr(m1);

        println("pass : " + ArraysUtil.equal(m1, m2));

    }

    public static void main(String[] args) {
        Solution s = new Solution2();
        test(s);
    }
}