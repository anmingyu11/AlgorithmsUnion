package _28simulation;

import java.util.Arrays;

import _00base.Base;

public class SpiralMatrix2 extends Base {

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;

        int begin = 0, end = n - 1;
        while (begin <= end) {
            // Left to Right
            for (int c = begin; c <= end; ++c) {
                matrix[begin][c] = num++;
            }

            // Top to Bottom
            for (int r = begin + 1; r <= end; ++r) {
                matrix[r][end] = num++;
            }

            // Right to Left
            for (int c = end - 1; c >= begin; --c) {
                matrix[end][c] = num++;
            }

            // Bottom to Top
            for (int r = end - 1; r > begin; --r) {
                matrix[r][begin] = num++;
            }

            ++begin;
            --end;
        }

        return matrix;
    }

    public static void main(String[] args) {
        final int n = 5;
        for (int i = 0; i < n; ++i) {
            println(Arrays.toString(generateMatrix(n)[i]));
        }
    }

}
