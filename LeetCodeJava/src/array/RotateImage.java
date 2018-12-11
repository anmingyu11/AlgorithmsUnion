package array;

import base.Base;

public class RotateImage extends Base {

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        // 沿副对角线反转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1 - i; ++j) {
                swap(matrix, i, j, n - 1 - j, n - 1 - i);
            }
        }

        // 水平中线反转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                swap(matrix, i, j, n - 1 - j, i);
            }
        }
    }

    public static void main(String[] args) {

    }
}
