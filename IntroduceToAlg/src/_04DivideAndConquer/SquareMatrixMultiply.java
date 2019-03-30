package _04DivideAndConquer;

import java.util.LinkedList;
import java.util.List;

import base.Base;

/**
 * 矩阵乘法,Strassen算法
 * <p>
 * pis : 写到老子怀疑人生
 * <p>
 * 对应4.2
 * <p>
 * 矩阵为n*n的矩阵,方阵.
 * -----
 * 这个算法的复杂性证明要比这个算法本身更有意义(对于普通程序员而言)
 * 此算法的最大意义,除了科学研究以外,就是递归程序的思想很重要,
 * 这个思想一定要清晰明确,每次递归都是解决更小的问题,而且是一个新的问题,前面的父问题是要组合多个子问题,
 * 尤其复杂的递归实现,以及复杂的分治法,对自己的递归方程必须要坚信不疑,如果思想有了偏差,那么后果是严重的.
 * 不然在实现的时候会浪费大量的时间.
 * ------
 * <p>
 */
public class SquareMatrixMultiply extends Base {

    private abstract static class Solution {

        public Solution() {
        }

        public abstract int[][] squareMatrixMultiply(int[][] A, int[][] B);

        void test() {
        }

    }

    // BruteForce 快乐写代码
    private static class Solution1 extends Solution {

        @Override
        public int[][] squareMatrixMultiply(int[][] A, int[][] B) {
            final int n = A.length;
            int[][] mc = new int[n][n];

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

    }

    // 朴素递归1
    // 总时间复杂度 8*T(n/2) +2*Θ(n^2) = Θ(n^3)
    // 部分参考:
    // https://blog.csdn.net/csdn_of_coder/article/details/80058361
    private static class Solution2 extends Solution {

        @Override
        public int[][] squareMatrixMultiply(int[][] A, int[][] B) {
            final int n = A.length;
            int[][] C = new int[n][n];
            if (n == 1) {
                C[0][0] = A[0][0] * B[0][0];
            } else {
                int[][] C11, C12, C21, C22;
                int[][] A11, A12, A21, A22;
                int[][] B11, B12, B21, B22;
                int offset = n / 2;
                // 可以原地计算 n^2
                // Sub A
                A11 = squareMatrixSub(A, 0, 0, offset);
                A12 = squareMatrixSub(A, 0, offset, offset);
                A21 = squareMatrixSub(A, offset, 0, offset);
                A22 = squareMatrixSub(A, offset, offset, offset);
                // Sub B
                B11 = squareMatrixSub(B, 0, 0, offset);
                B12 = squareMatrixSub(B, 0, offset, offset);
                B21 = squareMatrixSub(B, offset, 0, offset);
                B22 = squareMatrixSub(B, offset, offset, offset);

                // 8*T(n/2)
                C11 = squareMatrixAdd(squareMatrixMultiply(A11, B11), squareMatrixMultiply(A12, B21));
                C12 = squareMatrixAdd(squareMatrixMultiply(A11, B12), squareMatrixMultiply(A12, B22));
                C21 = squareMatrixAdd(squareMatrixMultiply(A21, B11), squareMatrixMultiply(A22, B21));
                C22 = squareMatrixAdd(squareMatrixMultiply(A21, B12), squareMatrixMultiply(A22, B22));

                // n^2
                squareMatrixMerge(C11, C, 0, 0, offset);
                squareMatrixMerge(C12, C, 0, offset, offset);
                squareMatrixMerge(C21, C, offset, 0, offset);
                squareMatrixMerge(C22, C, offset, offset, offset);
            }
            return C;
        }

        private int[][] squareMatrixSub(int[][] src, int x, int y, int offset) {
            int[][] dest = new int[offset][offset];
            for (int i = 0; i < offset; ++i) {
                //可以用Arrays.copy
                for (int j = 0; j < offset; ++j) {
                    dest[i][j] = src[x + i][y + j];
                }
            }
            return dest;
        }

        private void squareMatrixMerge(int[][] src, int[][] dest, int x, int y, int offset) {
            for (int i = 0; i < offset; ++i) {
                for (int j = 0; j < offset; ++j) {
                    dest[x + i][y + j] = src[i][j];
                }
            }
        }

        private int[][] squareMatrixAdd(int[][] A, int[][] B) {
            final int n = A.length;
            int[][] C = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            return C;
        }
    }

    /**
     * 朴素递归2
     * 不创建新的A B
     * 不知道能不能不创建新的C,我觉得不能,没有证明,但是每次一想这个就想的晕头转向.
     * 有牛逼的朋友写一下让我膜拜膜拜
     * 8*T(n/2) + Θ(n^2) = Θ(n^3z)
     */
    private static class Solution3 extends Solution {

        @Override
        public int[][] squareMatrixMultiply(int[][] A, int[][] B) {
            final int n = A.length;
            return multiply(A, B, 0, 0, 0, 0, n);
        }

        private int[][] multiply(int[][] A, int[][] B, int ax, int ay, int bx, int by, int n) {
            int[][] C = new int[n][n];
            if (n == 1) {
                C[0][0] = A[ax][ay] * B[bx][by];
            } else {
                int offset = n / 2;

                int[][] C11, C12, C21, C22;
                int ax1 = ax, ax2 = ax + offset, ay1 = ay, ay2 = ay + offset;
                int bx1 = bx, bx2 = bx + offset, by1 = by, by2 = by + offset;
                // A11 * B11 + A12 * B21
                C11 = add(multiply(A, B, ax1, ay1, bx1, by1, offset), multiply(A, B, ax1, ay2, bx2, by1, offset));
                // A11 * B12 + A12 * B22
                C12 = add(multiply(A, B, ax1, ay1, bx1, by2, offset), multiply(A, B, ax1, ay2, bx2, by2, offset));
                // A21 * B11 + A22 * B21
                C21 = add(multiply(A, B, ax2, ay1, bx1, by1, offset), multiply(A, B, ax2, ay2, bx2, by1, offset));
                // A21 * B12 + A22 * B22
                C22 = add(multiply(A, B, ax2, ay1, bx1, by2, offset), multiply(A, B, ax2, ay2, bx2, by2, offset));

                merge(C11, C, 0, 0, offset);
                merge(C12, C, 0, offset, offset);
                merge(C21, C, offset, 0, offset);
                merge(C22, C, offset, offset, offset);
            }
            return C;
        }

        private int[][] add(int[][] C1, int[][] C2) {
            final int n = C1.length;
            int[][] C = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    C[i][j] = C1[i][j] + C2[i][j];
                }
            }
            return C;
        }

        private void merge(int[][] src, int[][] dest, int x, int y, int n) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dest[x + i][y + j] = src[i][j];
                }
            }
        }

    }

    /**
     * Strassen
     * 7T(n/2) + 2Θ(n^2) = Θ(n^2.81)
     */
    private static class Solution4 extends Solution {

        @Override
        public int[][] squareMatrixMultiply(int[][] A, int[][] B) {
            final int n = A.length;
            return multiply(A, B, n);
        }

        private int[][] multiply(int[][] A, int[][] B, int n) {
            int[][] C = new int[n][n];
            if (n == 1) {
                C[0][0] = A[0][0] * B[0][0];
            } else {
                int[][] C11, C12, C21, C22;
                int[][] A11, A12, A21, A22;
                int[][] B11, B12, B21, B22;
                int[][] S1, S2, S3, S4, S5, S6, S7, S8, S9, S10;
                int[][] P1, P2, P3, P4, P5, P6, P7;
                int offset = n / 2;

                A11 = subArr(A, 0, 0, offset);
                A12 = subArr(A, 0, offset, offset);
                A21 = subArr(A, offset, 0, offset);
                A22 = subArr(A, offset, offset, offset);

                B11 = subArr(B, 0, 0, offset);
                B12 = subArr(B, 0, offset, offset);
                B21 = subArr(B, offset, 0, offset);
                B22 = subArr(B, offset, offset, offset);

                // S1  = B12 - B22
                // S2  = A11 + A12
                // S3  = A21 + A22
                // S4  = B21 - B11
                // S5  = A11 + A22
                // S6  = B11 + B22
                // S7  = A12 - A22
                // S8  = B21 + B22
                // S9  = A11 - A21
                // S10 = B11 + B12

                S1 = minus(B12, B22);
                S2 = add(A11, A12);
                S3 = add(A21, A22);
                S4 = minus(B21, B11);
                S5 = add(A11, A22);
                S6 = add(B11, B22);
                S7 = minus(A12, A22);
                S8 = add(B21, B22);
                S9 = minus(A11, A21);
                S10 = add(B11, B12);

                // P1 = A11 * S1
                // P2 = S2  * B22
                // P3 = S3  * B11
                // P4 = A22 * S4
                // P5 = S5  * S6
                // P6 = S7  * S8
                // P7 = S9  * S10

                P1 = multiply(A11, S1, offset);
                P2 = multiply(S2, B22, offset);
                P3 = multiply(S3, B11, offset);
                P4 = multiply(A22, S4, offset);
                P5 = multiply(S5, S6, offset);
                P6 = multiply(S7, S8, offset);
                P7 = multiply(S9, S10, offset);

                // C11 = P5 + P4 - P2 + P6
                // C12 = P1 + P2
                // C21 = P3 + P4
                // C22 = P5 + P1 - P3 - P7
                C11 = add(minus(add(P5, P4), P2), P6);
                C12 = add(P1, P2);
                C21 = add(P3, P4);
                C22 = minus(minus(add(P5, P1), P3), P7);

                merge(C11, C, 0, 0, offset);
                merge(C12, C, 0, offset, offset);
                merge(C21, C, offset, 0, offset);
                merge(C22, C, offset, offset, offset);
            }
            return C;
        }

        private int[][] subArr(int[][] primary, int x, int y, int n) {
            int[][] sub = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    sub[i][j] = primary[x + i][y + j];
                }
            }
            return sub;
        }

        private int[][] minus(int[][] A, int[][] B) {
            final int n = A.length;
            int[][] C = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            return C;
        }

        private int[][] add(int[][] A, int[][] B) {
            final int n = A.length;
            int[][] C = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            return C;
        }

        private void merge(int[][] src, int[][] dest, int x, int y, int n) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dest[x + i][y + j] = src[i][j];
                }
            }
        }

    }

    /**
     * Strassen
     * 不复制数组
     * 实现起来的难度远超想象,以后再说.
     */
    private static class Solution5 extends Solution {

        @Override
        public int[][] squareMatrixMultiply(int[][] A, int[][] B) {
            return null;
        }

        private int[][] multiply(int[][] A, int[][] B, int ax, int ay, int bx, int by, int n) {
            return null;
        }

        private void add(int[][] A, int[][] B, int ax, int ay, int bx, int by, int n) {
        }

        private void minus(int[][] A, int[][] B, int ax, int ay, int bx, int by, int n) {
        }

        private void merge(int[][] src, int[][] dest, int x, int y, int n) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dest[x + i][y + j] = src[i][j];
                }
            }
        }

    }

    public static void main(String[] args) {

        Solution s = new Solution2();

        s.test();
    }

    private static class Case {
        int[][] A, B, C;

        public Case(int[][] a, int[][] b, int[][] c) {
            A = a;
            B = b;
            C = c;
        }
    }

    private static class Testcases {

        static List<Case> getCases() {
            List<Case> cases = new LinkedList<>();
            int num = 3;
            //生成三个矩阵和对应的结果
            for (int i = 1; i <= num; ++i) {
                final int n = 1 << i;
                int[][] a = new int[n][n];
                int[][] b = new int[n][n];
                int[][] c = new int[n][n];
                result(a, b, c, n);
                cases.add(new Case(a, b, c));
            }
            return cases;
        }

        static void result(int[][] A, int[][] B, int[][] C, int n) {
            final int maxNum = n * n + 1;
            //Generate A B
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; ++j) {
                    A[i][j] = n * i + j + 1;
                    B[i][j] = maxNum - A[i][j];
                }
            }
            // Generate C
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    int c = 0;
                    for (int k = 0; k < n; ++k) {
                        c += A[i][k] * B[k][j];
                    }
                    C[i][j] = c;
                }
            }
            //print2DArr(A);
            //print2DArr(B);
            //print2DArr(C);
        }

    }
}
