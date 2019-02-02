package _java;

import base.Base;

// 模拟,虽然标签是数组,但是我感觉根正常的数组处理没什么关系.
public class _0048RotateImage extends Base {

    private abstract static class Solution {
        public abstract void rotate(int[][] matrix);
    }

    private static class Solution1 extends Solution {

        public void rotate(int[][] matrix) {
            final int n = matrix.length;
            if (n <= 1) {
                return;
            }
            // 数组的clone是浅克隆
            int[][] matrix2 = new int[n][n];

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    matrix2[j][n - 1 - i] = matrix[i][j];
                }
            }

            for (int i = 0; i < n; ++i) {
                matrix[i] = matrix2[i];
            }
        }

    }

    // 纯模拟
    private static class Solution2 extends Solution {

        public void rotate(int[][] matrix) {
            final int n = matrix.length;
            if (n < 2) {
                return;
            }

            for (int i = 0; i < n / 2; ++i) {
                for (int j = i; j < n - 1 - i; ++j) {
                    int aX = i, aY = j;
                    int bX = j, bY = n - 1 - i;
                    int cX = n - 1 - j, cY = i;
                    int dX = n - 1 - i, dY = n - 1 - j;
                    // a c 交换
                    swap(matrix, aX, aY, cX, cY);
                    // b d 交换
                    swap(matrix, bX, bY, dX, dY);
                    // b c 交换
                    swap(matrix, bX, bY, cX, cY);
                }
            }
        }

        private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
            int t = matrix[x1][y1];
            matrix[x1][y1] = matrix[x2][y2];
            matrix[x2][y2] = t;
        }
    }

    // 水平, 主对角线
    private static class Solution3 extends Solution {

        public void rotate(int[][] matrix) {
            final int n = matrix.length;
            if (n < 2) {
                return;
            }

            // 水平
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n / 2; ++j) {
                    swap(matrix, j, i, n - 1 - j, i);
                }
            }

            // 主对角线
            for (int i = 0; i < n - 1; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    swap(matrix, i, j, j, i);
                }
            }

        }

        private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
            int t = matrix[x1][y1];
            matrix[x1][y1] = matrix[x2][y2];
            matrix[x2][y2] = t;
        }

    }

    // 副对角线,水平对折
    private static class Solution4 extends Solution {

        public void rotate(int[][] matrix) {

            final int n = matrix.length;
            if (n < 2) {
                return;
            }

            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - 1 - i; ++j) {
                    swap(matrix, i, j, n - 1 - j, n - 1 - i);
                }
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n / 2; ++j) {
                    swap(matrix, j, i, n - 1 - j, i);
                }
            }
        }

        private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
            int t = matrix[x1][y1];
            matrix[x1][y1] = matrix[x2][y2];
            matrix[x2][y2] = t;
        }

    }

    public static void main(String[] args) {
        int[][] arr1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] arr2 = new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        Solution s = new Solution2();

        s.rotate(arr1);
        print2DArr(arr1);
        //  [7,4,1],
        //  [8,5,2],
        //  [9,6,3]
        s.rotate(arr2);
        print2DArr(arr2);
        // [15, 13,  2,  5],
        // [14,  3,  4,  1],
        // [12,  6,  8,  9],
        // [16,  7, 10, 11]
    }
}
