package _java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import base.Base;

public class _0073SetMatrixZeros extends Base {

    private abstract static class Solution {
        public abstract void setZeroes(int[][] matrix);
    }

    // 状态机 , 1ms beats 100% 但是理论上是不可以的这个
    private static class Solution1 extends Solution {

        public void setZeroes(int[][] matrix) {
            final int m = matrix.length;
            final int n = matrix[0].length;
            // 一个用了　+1 理论上这个数是可能会有的，只是testcase覆盖不到.
            int tag = Integer.MIN_VALUE + 1;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 0) {
                        //fill tag
                        for (int r = 0; r < m; ++r) {
                            if (matrix[r][j] != 0) {
                                matrix[r][j] = tag;
                            }
                        }
                        for (int c = 0; c < n; ++c) {
                            if (matrix[i][c] != 0) {
                                matrix[i][c] = tag;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == tag) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

    }

    // 这个更聪明，有效的利用了规律
    private static class Solution2 extends Solution {

        public void setZeroes(int[][] matrix) {
            final int m = matrix.length, n = matrix[0].length;

            boolean r0 = false, c0 = false;
            for (int i = 0; i < m; ++i) {
                if (matrix[i][0] == 0) {
                    //　第0列是0
                    c0 = true;
                    break;
                }
            }
            for (int j = 0; j < n; ++j) {
                if (matrix[0][j] == 0) {
                    //　第0行是0
                    r0 = true;
                    break;
                }
            }

            for (int i = 1; i < m; ++i) {
                for (int j = 1; j < n; ++j) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            // 将行首为0的行置0
            for (int i = 1; i < m; ++i) {
                if (matrix[i][0] == 0) {
                    Arrays.fill(matrix[i], 0);
                }
            }

            // 将列首为0的列置0
            for (int i = 1; i < n; ++i) {
                if (matrix[0][i] == 0) {
                    for (int j = 0; j < m; ++j) {
                        matrix[j][i] = 0;
                    }
                }
            }
            if (r0) {
                Arrays.fill(matrix[0], 0);
            }
            if (c0) {
                for (int i = 0; i < m; ++i) {
                    matrix[i][0] = 0;
                }
            }
        }
    }

    // 3ms 17.62;
    private static class Solution3 extends Solution {

        public void setZeroes(int[][] matrix) {
            final int r = matrix.length, c = matrix[0].length;
            Set<Integer> rowSet = new HashSet<>(r);
            Set<Integer> colSet = new HashSet<>(c);
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    if (matrix[i][j] == 0) {
                        rowSet.add(i);
                        colSet.add(j);
                    }
                }
            }

            for (int i = 0; i < r; ++i) {
                if (rowSet.contains(i)) {
                    Arrays.fill(matrix[i], 0);
                }
            }

            for (int i = 0; i < c; ++i) {
                if (colSet.contains(i)) {
                    for (int j = 0; j < r; ++j) {
                        matrix[j][i] = 0;
                    }
                }
            }
        }

    }

    // 数组代替set 1ms
    private static class Solution4 extends Solution {

        public void setZeroes(int[][] matrix) {
            final int r = matrix.length, c = matrix[0].length;
            boolean[] rowSet = new boolean[r];
            boolean[] colSet = new boolean[c];
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    if (matrix[i][j] == 0) {
                        rowSet[i] = true;
                        colSet[j] = true;
                    }
                }
            }

            for (int i = 0; i < r; ++i) {
                if (rowSet[i]) {
                    Arrays.fill(matrix[i], 0);
                }
            }

            for (int i = 0; i < c; ++i) {
                if (colSet[i]) {
                    for (int j = 0; j < r; ++j) {
                        matrix[j][i] = 0;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] m1 = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        int[][] m2 = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        Solution s = new Solution4();

        s.setZeroes(m1);
        print2DArr(m1);
        s.setZeroes(m2);
        print2DArr(m2);
    }
}
