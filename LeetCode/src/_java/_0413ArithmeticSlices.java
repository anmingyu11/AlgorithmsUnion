package _java;

import base.Base;


/**
 * A sequence of number is called arithmetic if it consists of at least three elements
 * and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, these are arithmetic sequence:
 * <p>
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 * <p>
 * 1, 1, 2, 5, 7
 * <p>
 * A zero-indexed array A consisting of N numbers is given.
 * A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 * <p>
 * A slice (P, Q) of array A is called arithmetic if the sequence:
 * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 * <p>
 * The function should return the number of arithmetic slices in the array A.
 * <p>
 * <p>
 * Example:
 * <p>
 * A = [1, 2, 3, 4]
 * <p>
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
public class _0413ArithmeticSlices extends Base {

    private abstract static class Solution {
        public abstract int numberOfArithmeticSlices(int[] A);
    }

    /**
     * Runtime: 31 ms, faster than 5.12% of Java online submissions for Arithmetic Slices.
     * Memory Usage: 35.9 MB, less than 99.75% of Java online submissions for Arithmetic Slices.
     */
    private static class Solution1 extends Solution {

        public int numberOfArithmeticSlices(int[] A) {
            final int n = A.length;
            int cnt = 0;
            for (int i = 0; i < n - 2; ++i) {
                int gap = A[i + 1] - A[i];
                for (int len = i + 2; len < n; ++len) {
                    int k = 0;
                    for (k = i + 1; k <= len; ++k) {
                        if (A[k] - A[k - 1] != gap) {
                            break;
                        }
                    }
                    if (k > len) {
                        ++cnt;
                    }
                }
            }
            return cnt;
        }

    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Arithmetic Slices.
     * Memory Usage: 35.4 MB, less than 100.00% of Java online submissions for Arithmetic Slices.
     */
    private static class Solution2 extends Solution {

        @Override
        public int numberOfArithmeticSlices(int[] A) {
            final int n = A.length;
            int cnt = 0;
            for (int i = 0; i < n - 2; ++i) {
                int d = A[i + 1] - A[i];
                for (int len = i + 2; len < n; ++len) {
                    if (A[len] - A[len - 1] == d) {
                        ++cnt;
                    } else {
                        break;
                    }
                }
            }
            return cnt;
        }

    }

    private static class Solution3 extends Solution {

        private int sum = 0;

        public int numberOfArithmeticSlices(int[] A) {
            sum = 0;
            slice(A, A.length - 1);
            return sum;
        }

        private int slice(int[] A, int i) {
            if (i < 2) {
                return 0;
            }
            int as = 0;
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                as = 1 + slice(A, i - 1);
                sum += as;
            } else {
                slice(A, i - 1);
            }
            return as;
        }
    }

    private static class Solution4 extends Solution {

        @Override
        public int numberOfArithmeticSlices(int[] A) {
            final int n = A.length;
            int[] dp = new int[n];
            int sum = 0;
            for (int i = 2; i < n; ++i) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    dp[i] = dp[i - 1] + 1;
                    sum += dp[i];
                }
            }
            return sum;
        }
    }

    private static class Solution5 extends Solution {

        public int numberOfArithmeticSlices(int[] A) {
            final int n = A.length;
            int dp = 0;
            int sum = 0;
            for (int i = 2; i < n; ++i) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    dp = 1 + dp;
                    sum += dp;
                } else {
                    dp = 0;
                }
            }
            return sum;
        }

    }

    public static void main(String[] args) {
        int[] s1 = {1, 2, 3, 4};

        Solution s = new Solution3();

        println(s.numberOfArithmeticSlices(s1));
    }

}