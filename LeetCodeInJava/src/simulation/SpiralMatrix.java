package simulation;

import java.util.ArrayList;
import java.util.List;

import base.Base;

public class SpiralMatrix extends Base {

    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }

        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];

        int r = 0, c = 0, direct = 0;
        int[] directR = {0, 1, 0, -1};
        int[] directC = {1, 0, -1, 0};

        for (int i = 0; i < R * C; ++i) {
            result.add(matrix[r][c]);
            seen[r][c] = true;

            int cr = r + directR[direct];
            int cc = c + directC[direct];

            if (cr >= 0 && cr < R &&
                    cc >= 0 && cc < C
                    && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                direct = (direct + 1) % 4;
                r += directR[direct];
                c += directC[direct];
            }
        }

        return result;
    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }

        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;

        while (c1 <= c2 && r1 <= r2) {
            for (int c = c1; c <= c2; ++c) {
                result.add(matrix[r1][c]);
            }
            for (int r = r1 + 1; r <= r2; ++r) {
                result.add(matrix[r][c2]);
            }

            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c >= c1; --c) {
                    result.add(matrix[r2][c]);
                }
                for (int r = r2 - 1; r > r1; --r) {
                    result.add(matrix[r][c1]);
                }
            }

            ++c1;
            --c2;
            ++r1;
            --r2;
        }

        return result;
    }

    public static void main(String[] args) {
        println(spiralOrder2(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));

    }

}
