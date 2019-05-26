package _15DynamicProgramming;

import base.Base;

public class OptimalBST extends Base {

    private abstract static class Solution {
        public final float[] p = {-1f, 0.15f, 0.1f, 0.05f, 0.1f, 0.2f};
        public final float[] q = {0.05f, 0.1f, 0.05f, 0.05f, 0.05f, 0.1f};
        public final int n = q.length - 1;

        /**
         * @param e    e[i,j]: 包含关键字ki,...,kj的最优二叉树中进行一次搜索的期望.
         * @param w    w[i,j]: 包含关键字ki,...,kj的最优二叉树的概率之和.
         * @param root r[i,j]: 包含关键字ki,...,kj的最优二叉树的根.
         */
        public abstract void optimalBst(float[][] e, float[][] w, int[][] root);
    }

    /**
     * 暴力解法
     */
    private static class Checker extends Solution {

        @Override
        public void optimalBst(float[][] e, float[][] w, int[][] root) {
            for (int i = 1; i <= n + 1; ++i) {
                e[i][i - 1] = q[i - 1];
                w[i][i - 1] = q[i - 1];
            }
            for (int i = 1; i <= n; ++i) {
                for (int j = i; j <= n; ++j) {
                    w[i][j] = w[i][j - 1] + p[j] + q[j];
                }
            }
            // Todo : 怎样把求和写到递归式里,这里是一个突破.
            e[1][n] = optimalBSTAUX(e, w, root, 1, n);
        }

        private float optimalBSTAUX(float[][] e, float[][] w, int[][] root, int i, int j) {
            if (i > j) {
                e[i][j] = q[j];
                w[i][j] = q[j];
                return q[j];
            }
//            else if (i == j) {
//                w[i][j] = p[i] + q[i] + q[i - 1];
//                e[i][j] = 2*q[i-1] + 2*q[i] + p[i];
//                return e[i][j];
//            }
            e[i][j] = Float.MAX_VALUE;
            for (int r = i; r <= j; ++r) {
                e[i][r - 1] = optimalBSTAUX(e, w, root, i, r - 1);
                e[r + 1][j] = optimalBSTAUX(e, w, root, r + 1, j);
                float t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                if (t < e[i][j]) {
                    e[i][j] = t;
                    root[i][j] = r;
                }
            }
            return e[i][j];
        }

    }

    private static class Solution1 extends Solution {

        @Override
        public void optimalBst(float[][] e, float[][] w, int[][] root) {
            for (int i = 1; i <= n + 1; ++i) {
                e[i][i - 1] = q[i - 1];
                w[i][i - 1] = q[i - 1];
            }
            for (int l = 1; l <= n; ++l) {
                for (int i = 1; i <= n - l + 1; ++i) {
                    int j = i + l - 1;
                    e[i][j] = Float.MAX_VALUE;
                    w[i][j] = w[i][j - 1] + p[j] + q[j];
                    for (int r = i; r <= j; ++r) {
                        float t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                        if (t < e[i][j]) {
                            e[i][j] = t;
                            root[i][j] = r;
                        }
                    }
                }
            }
        }

    }

    private static void test() {
    }

    private static void testSolution(Solution s) {
        final int n = s.n;
        float[][] e = new float[n + 2][n + 2];
        float[][] w = new float[n + 2][n + 2];
        int[][] root = new int[n + 2][n + 2];

        s.optimalBst(e, w, root);

        println("--------------- expect ---------------");
        print2DArr(e);
        println("--------------- weight ---------------");
        print2DArr(w);
        println("--------------- root ---------------");
        print2DArr(root);
    }

    public static void main(String[] args) {
        Solution s = new Checker();

        testSolution(s);
    }
}