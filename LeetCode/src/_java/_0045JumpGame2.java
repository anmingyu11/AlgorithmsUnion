package _java;

import base.Base;

/**
 * Given an array of non-negative integers,
 * <p>
 * you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class _0045JumpGame2 extends Base {

    private abstract static class Solution {
        public abstract int jump(int[] nums);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game II.
     * Memory Usage: 36.7 MB, less than 99.61% of Java online submissions for Jump Game II.
     */
    private static class Solution1 extends Solution {

        @Override
        public int jump(int[] A) {
            final int n = A.length;
            if (n < 2) {
                return 0;
            }

            int curMax = 0, i = 0, level = 0, nextMax = 0;
            while (curMax - i + 1 > 0) {
                ++level;
                for (; i <= curMax; ++i) {
                    nextMax = Math.max(nextMax, A[i] + i);
                    if (nextMax >= n - 1) {
                        return level;
                    }
                }
                curMax = nextMax;
            }
            return 0;
        }

    }

    /**
     * greedy
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game II.
     * Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Jump Game II.
     */
    private static class Solution2 extends Solution {

        @Override
        public int jump(int[] A) {
            final int n = A.length;
            if (n < 2) {
                return 0;
            }
            int far = 0, end = 0, jump = 0;
            for (int i = 0; i < n - 1; ++i) {
                far = Math.max(far, A[i] + i);
                if (i == end) {
                    ++jump;
                    end = far;
                }
            }
            return jump;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 1, 4};
        int[] arr2 = {2, 1};
        int[] arr3 = {1, 3, 2};
        int[] arr4 = {2, 3, 1};
        Solution s = new Solution2();

        println(s.jump(arr1)); // 2
        println(s.jump(arr2)); // 1
        println(s.jump(arr3)); // 2
        println(s.jump(arr4)); // 1
    }

}