package _java;

import base.Base;

public class _1252CellswithOddValuesInaMatrix extends Base {

    private static abstract class Solution {
        public abstract int oddCells(int n, int m, int[][] indices);
    }

    private static class Solution1 extends Solution {
        @Override
        public int oddCells(int n, int m, int[][] indices) {
            int[] row = new int[n];
            int[] col = new int[m];

            for (int[] inc : indices) {
                ++row[inc[0]];
                ++col[inc[1]];
            }

            int res = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    int v = row[i] + col[j];
                    res = ((v & 1) == 0) ? res : ++res;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();
        println(s.oddCells(2, 3, new int[][]{{0, 1}, {1, 1}}));
        println(s.oddCells(2, 2, new int[][]{{1, 1}, {0, 0}}));
    }
}
