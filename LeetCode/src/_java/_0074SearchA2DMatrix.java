package _java;

import base.Base;

public class _0074SearchA2DMatrix extends Base {

    private abstract static class Solution {
        public abstract boolean searchMatrix(int[][] matrix, int target);
    }

    // binarySearch
    // Runtime: 4 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
    // Memory Usage: 38.9 MB, less than 42.75% of Java online submissions for Search a 2D Matrix.
    private static class Solution1 extends Solution {

        private int binarySearch(int[] arr, int target) {
            int lo = 0, hi = arr.length - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                int v = arr[mid];
                if (target < v) {
                    hi = mid - 1;
                } else if (target > v) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
            return -lo;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            final int m = matrix.length;
            if (m == 0) {
                return false;
            }
            final int n = matrix[0].length;
            if (n == 0) {
                return false;
            }

            for (int[] arr : matrix) {
                if (arr[0] == target) {
                    return true;
                } else if (arr[0] > target) {
                    return false;
                } else {
                    int index = binarySearch(arr, target);
                    if (index > 0) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    // dfs
    // Runtime: 4 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
    // Memory Usage: 38.9 MB, less than 43.49% of Java online submissions for Search a 2D Matrix.
    private static class Solution2 extends Solution {

        @Override
        public boolean searchMatrix(int[][] matrix, int target) {
            final int m = matrix.length;
            if (m == 0) {
                return false;
            }
            final int n = matrix[0].length;
            if (n == 0) {
                return false;
            }

            int x = m - 1, y = 0;

            while (x >= 0 && y < n) {
                int v = matrix[x][y];
                if (v < target) {
                    ++y;
                } else if (v > target) {
                    --x;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        Solution s = new Solution2();

        println(s.searchMatrix(matrix, 3));
        println(s.searchMatrix(matrix, 13));
    }
}
