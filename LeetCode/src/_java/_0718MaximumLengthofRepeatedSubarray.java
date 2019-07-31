package _java;

import base.Base;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 * <p>
 * Note:
 * <p>
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class _0718MaximumLengthofRepeatedSubarray extends Base {

    private abstract static class Solution {
        public abstract int findLength(int[] A, int[] B);
    }

    /**
     * BruteForce
     */
    private static class Solution1 extends Solution {

        @Override
        public int findLength(int[] A, int[] B) {
            final int m = A.length, n = B.length;
            int ans = 0;
            int[][] dp = new int[m][n];

            return ans;
        }

    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};

        Solution s = new Solution1();

        println(s.findLength(A, B));
    }
}
