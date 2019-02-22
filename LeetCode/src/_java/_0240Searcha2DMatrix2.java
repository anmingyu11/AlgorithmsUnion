package _java;

import base.Base;

public class _0240Searcha2DMatrix2 extends Base {

    private abstract static class Solution {
        public abstract boolean searchMatrix(int[][] matrix, int target);
    }

    // Runtime: 7 ms, faster than 57.78% of Java online submissions for Search a 2D Matrix II.
    // Memory Usage: 45.4 MB, less than 77.23% of Java online submissions for Search a 2D Matrix II.
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
            return -1;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            for (int[] arr : matrix) {
                int index = binarySearch(arr, target);
                if (index >= 0) {
                    return true;
                }
            }
            return false;
        }
    }

    // 剪枝1 ,随便一写,6666
    // Runtime: 6 ms, faster than 97.21% of Java online submissions for Search a 2D Matrix II.
    // Memory Usage: 46.5 MB, less than 24.89% of Java online submissions for Search a 2D Matrix II.
    private static class Solution2 extends Solution {

        private int binarySearch(int[] a, int target) {
            int lo = 0, hi = a.length - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (target < a[mid]) {
                    hi = mid - 1;
                } else if (target > a[mid]) {
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

            for (int i = 0; i < m; ++i) {
                if (matrix[i][0] < target) {
                    if (binarySearch(matrix[i], target) > 0) {
                        return true;
                    }
                } else if (matrix[i][0] == target) {
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        }

    }

    // 剪枝2
    // Runtime: 6 ms, faster than 97.21% of Java online submissions for Search a 2D Matrix II.
    // Memory Usage: 47 MB, less than 11.27% of Java online submissions for Search a 2D Matrix II.
    private static class Solution3 extends Solution {

        private int binarySearch(int[] arr, int lo, int hi, int target) {
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

            int c = 0;
            for (int i = 0; i < n; ++i) {
                int v = matrix[0][i];
                if (v < target) {
                    c = i;
                } else if (v > target) {
                    break;
                } else {
                    return true;
                }
            }

            for (int i = 0; i < m; ++i) {
                int v = matrix[i][0];
                if (v < target) {
                    if (binarySearch(matrix[i], 0, c, target) > 0) {
                        return true;
                    }
                } else if (v == target) {
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        }

    }

    // DFS
    // 左下角或者右上角,其他的不行,因为修剪行或列可以阻止我们得到正确答案
    // Runtime: 6 ms, faster than 97.21% of Java online submissions for Search a 2D Matrix II.
    // Memory Usage: 46.1 MB, less than 57.19% of Java online submissions for Search a 2D Matrix II.
    private static class Solution4 extends Solution {

        public boolean searchMatrix(int[][] matrix, int target) {
            final int m = matrix.length;
            if (m == 0) {
                return false;
            }
            final int n = matrix[0].length;
            if (n == 0) {
                return false;
            }

            // 左下角
            int i = m - 1;
            int j = 0;

            while (i >= 0 && j <= n - 1) {
                int v = matrix[i][j];
                if (target == v) {
                    return true;
                } else if (target < v) {
                    --i;
                } else if (target > v) {
                    ++j;
                }
            }

            return false;
        }

    }

    public static void main(String[] args) {
        int[][] matrix1 =
                {
                        {1, 4, 7, 11, 15},
                        {2, 5, 8, 12, 19},
                        {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}
                };
        int[][] matrix2 = {{}};
        int[][] matrix3 = {{1, 1}};
        int[][] matrix4 = {{5}, {6}};

        Solution s = new Solution4();

        println(s.searchMatrix(matrix1, 5)); // t
        println(s.searchMatrix(matrix1, 20));// f
        println(s.searchMatrix(matrix2, 1)); // f
        println(s.searchMatrix(matrix3, 2)); // f
        println(s.searchMatrix(matrix4, 6)); // t
        println(s.searchMatrix(matrix4, 7)); // f
    }
}
