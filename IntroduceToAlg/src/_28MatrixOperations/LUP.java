package _28MatrixOperations;

import base.Base;

public class LUP extends Base {

    /**
     * 方阵乘法
     *
     * @param A
     * @param B
     * @return
     */
    public static float[][] multiply(float[][] A, float[][] B) {
        final int n = A.length;
        float[][] mc = new float[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int c = 0;
                for (int k = 0; k < n; ++k) {
                    c += A[i][k] * B[k][j];
                }
                mc[i][j] = c;
            }
        }
        return mc;
    }

    /**
     * 求解线性方程组 Ax = b
     * PA = LU
     * <p>
     * 1. PAx = Pb
     * 2. LUx = Pb
     * 3. 定义y = Ux
     * 4. Ly = Pb 下三角系统
     * 5. 已知L,P,b, 求解y
     * 6. 第一步 利用正向替换 y_i = b_πi - Σ(j=1,i-1)l_ij * y_1
     * 7. 第二步 利用反向替换 x_i = (y_i - Σ(j=i+1,n))u_ij
     *
     * @param L
     * @param U
     * @param pi P是置换P pi[i] = j 代表 置换矩阵P的(i,j)是1
     * @return LUx = Pb 求解x
     */
    public static float[] LUPSolve(float[][] L, float[][] U, int[] pi, float[] b) {
        final int n = L.length;
        float[] x = new float[n];
        float[] y = new float[n];
        // 正向替换 Ly = Pb 求解y, L是单位下三角矩阵
        for (int i = 0; i < n; ++i) {
            float sum = 0;
            for (int j = 0; j < i; ++j) {
                sum += L[i][j] * y[j];
            }
            y[i] = b[pi[i]] - sum;
        }
        // 反向替换 Ux = y 求解x U是上三角矩阵.
        for (int i = n - 1; i >= 0; --i) {
            float sum = 0;
            for (int j = n - 1; j > i; --j) {
                sum += U[i][j] * x[j];
            }
            x[i] = (y[i] - sum) / U[i][i];
        }
        return x;
    }

    /**
     * A的LU分解.
     * A 是对称正定矩阵(避免主元为0)
     * A
     * L =
     * [1     , 0]
     * [v/a_11,L']
     * <p>
     * U =
     * [a11  ,w^T]
     * [0    ,U' ]
     */
    public static void LUDecomposition(float[][] A, float[][] L, float[][] U) {
        final int n = A.length;
        for (int i = 0; i < n; ++i) {
            L[i][i] = 1;
        }
        for (int k = 0; k < n; ++k) {
            U[k][k] = A[k][k]; // 选主元
            for (int i = k + 1; i < n; ++i) { // 用v和w^t对L和U进行更新.
                L[i][k] = A[i][k] / U[k][k]; // 确定v,放入L中------求 v/a_11
                U[k][i] = A[k][i];           // 确定w^T,放入U中------求 w^T
            }
            for (int i = k + 1; i < n; ++i) { // 舒尔补 A' - v*w^t/a_11 即利用上面求出来的部分
                for (int j = k + 1; j < n; ++j) {
                    A[i][j] -= L[i][k] * U[k][j];
                }
            }
        }
    }

    /**
     * 添加置换矩阵P来避免主元为0
     * PA = LU
     * L:
     * [1        , 0]
     * [P'*v/a_k1,L']
     * <p>
     * U:
     * [a_k1,w^t]
     * [0   ,U' ]
     *
     * @param A
     * @param L
     * @param U
     * @return P 置换矩阵P pi[i] = j 表示 P[i][j] = 1
     */
    public static int[] LUPDecomposition(float[][] A, float[][] L, float[][] U) {
        final int n = A.length;
        float[][] ANew = new float[n][n];
        // 深克隆一个A
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                ANew[i][j] = A[i][j];
            }
        }
        // 初始化置换矩阵,顺便初始化单位下三角矩阵L
        int[] pi = new int[n]; // 置换矩阵P pi[i] = j 表示 P[i][j] = 1
        for (int i = 0; i < n; ++i) {
            L[i][i] = 1;
            pi[i] = i;
        }
        for (int k = 0; k < n; ++k) {
            // 第一步选主元
            float p = 0;
            int k_ex = k;
            for (int i = k; i < n; ++i) {
                float a_ik = Math.abs(ANew[i][k]);
                if (a_ik > p) {
                    p = a_ik;
                    k_ex = i;
                }
            }
            // 如果列中元素都是0,则不能进行LUP分解,矩阵A是奇异矩阵.
            if (p == 0) {
                throw new IllegalArgumentException("singular matrix");
            }
            // 相当于置换矩阵的行变换
            swap(pi, k, k_ex);
            // 矩阵A的行变换,相当于左乘上置换矩阵P
            swap(ANew, k, k_ex);
            // 计算舒尔补
            for (int i = k + 1; i < n; ++i) {
                ANew[i][k] /= ANew[k][k];
                for (int j = k + 1; j < n; ++j) {
                    ANew[i][j] -= ANew[i][k] * ANew[k][j];
                }
            }
        }
        // 给L,U赋值
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > j) {
                    // 下三角矩阵L
                    L[i][j] = ANew[i][j];
                } else {
                    // 上三角矩阵U
                    U[i][j] = ANew[i][j];
                }
            }
        }
        return pi;
    }

    /**
     * 矩阵求逆
     * A是非奇异矩阵
     * PA = LU
     * 设 Ax = b
     * LUP分解取决于A而不是b
     * 所以我们考虑  AX = I_n
     * I是n阶单位矩阵.
     * 那么X就是A的逆矩阵
     * 所以可以这样来做
     * 利用A的LUP分解来求解X_i,X_i就是逆矩阵的第i列
     * AX_i = ei
     *
     * @param A 非奇异矩阵A
     * @return
     */
    public static float[][] inverse(float[][] A) {
        // Todo inverse.
        final int n = A.length;
        float[][] AInverse = new float[n][n];
        float[][] I_n = new float[n][n]; // n阶单位矩阵I
        for (int i = 0; i < n; ++i) {
            I_n[i][i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            float[][] L = new float[n][n];
            float[][] U = new float[n][n];
            int[] pi = LUPDecomposition(A, L, U);
            // LUPSolve求得的是列向量,这里赋值给行向量
            AInverse[i] = LUPSolve(L, U, pi, I_n[i]);
        }
        // AInverse^T 列向量换成行向量
        transposition_in(AInverse);
        throw new RuntimeException("这个错了,找时间重写一下.");
        //return AInverse;
    }

    public static float[][] transposition(float[][] A) {
        final int n = A.length;
        float[][] B = new float[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

    public static void transposition_in(float[][] A) {
        final int n = A.length;
        float tmp = 0f;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                tmp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = tmp;
            }
        }
    }

    //---------------------------- Utilities -----------------------------------
    public static void swap(float[][] A, int i, int j) {
        float[] tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    private static void testLUPSolve() {
        float[][] A = {
                {1, 2, 0},
                {3, 4, 4},
                {5, 6, 3}
        };
        float[][] L = {
                {1, 0, 0},
                {0.2f, 1, 0},
                {0.6f, 0.5f, 1},
        };
        float[][] U = {
                {5, 6, 3},
                {0, 0.8f, -0.6f},
                {0, 0, 2.5f}
        };
        float[][] P = {
                {0, 0, 1},
                {1, 0, 0},
                {0, 1, 0}
        };
        float[] b = {3, 7, 8};
        int[] pi = {2, 0, 1};
        float[] x = LUPSolve(L, U, pi, b);
        // 这个有数值不稳定的问题.
        printArr(x);
    }

    private static void testLUDecomposition() {
        float[][] A = {{2, 3, 1, 5}, {6, 13, 5, 19}, {2, 19, 10, 23}, {4, 10, 11, 31}};
        final int n = A.length;
        float[][] L = new float[n][n];
        float[][] U = new float[n][n];
        LUDecomposition(A, L, U);

        print2DArr(L);
        print2DArr(U);
    }

    private static void testLUPDecomposition() {
        float[][] A = {{2, 0, 2, 0.6f}, {3, 3, 4, -2}, {5, 5, 4, 2}, {-1, -2, 3.4f, -1}};
        final int n = A.length;
        float[][] L = new float[n][n];
        float[][] U = new float[n][n];

        int[] pi = LUPDecomposition(A, L, U);
        int[][] P = new int[n][n];
        for (int i = 0; i < n; ++i) {
            P[i][pi[i]] = 1;
        }

        println("P :--------------- ");
        print2DArr(P);
        println("L :--------------- ");
        print2DArr(L);
        println("U :--------------- ");
        print2DArr(U);
    }

    private static void testInverse() {
        float[][] A = {{2, 0, 2, 0.6f}, {3, 3, 4, -2}, {5, 5, 4, 2}, {-1, -2, 3.4f, -1}};
        float[][] A_inv = inverse(A);
        float[][] ans = multiply(A, A_inv);

        throw new RuntimeException("这个错了,找时间重写一下.");
    }

    public static void main(String[] args) {
        // testLUPSolve();
        // testLUDecomposition();
        // testLUPDecomposition();
        testInverse();
    }

}