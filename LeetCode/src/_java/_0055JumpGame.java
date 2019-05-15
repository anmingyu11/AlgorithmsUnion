package _java;

import base.Base;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 */
public class _0055JumpGame extends Base {

    private abstract static class Solution {
        public abstract boolean canJump(int[] nums);
    }

    /**
     * TLE
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean canJump(int[] nums) {
            return canJumpFromPosition(0, nums);
        }

        private boolean canJumpFromPosition(int position, int[] nums) {
            final int n = nums.length;
            if (position == n - 1) {
                return true;
            }
            int futherJump = Math.min(position + nums[position], n - 1);
            for (int i = position + 1; i <= futherJump; ++i) {
                if (canJumpFromPosition(i, nums)) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * Memorized
     * Runtime: 1262 ms, faster than 6.04% of Java online submissions for Jump Game.
     * Memory Usage: 42.5 MB, less than 14.08% of Java online submissions for Jump Game.
     */
    private static class Solution2 extends Solution {

        int[] memo;

        @Override
        public boolean canJump(int[] nums) {
            final int n = nums.length;
            memo = new int[n];
            memo[n - 1] = 1;
            return canJumpAux(0, nums);
        }

        private boolean canJumpAux(int pos, int[] nums) {
            if (memo[pos] != 0) {
                return memo[pos] > 0;
            }
            final int n = nums.length;
            int further = Math.min(pos + nums[pos], n - 1);
            for (int i = pos + 1; i <= further; ++i) {
                if (canJumpAux(i, nums)) {
                    memo[pos] = 1;
                    return true;
                }
            }
            memo[pos] = -1;
            return false;
        }

    }

    /**
     * Runtime: 169 ms, faster than 27.48% of Java online submissions for Jump Game.
     * Memory Usage: 38.4 MB, less than 93.90% of Java online submissions for Jump Game.
     */
    private static class Solution3 extends Solution {

        @Override
        public boolean canJump(int[] nums) {
            final int n = nums.length;
            int[] memo = new int[n];
            memo[n - 1] = 1;

            for (int i = n - 2; i >= 0; --i) {
                int further = Math.min(i + nums[i], n - 1);
                for (int j = i + 1; j <= further; ++j) {
                    if (memo[j] > 0) {
                        memo[i] = 1;
                        break;
                    }
                }
            }
            return memo[0] > 0;
        }

    }

    /**
     * Runtime: 1 ms, faster than 99.93% of Java online submissions for Jump Game.
     * Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Jump Game.
     */
    private static class Solution4 extends Solution {

        @Override
        public boolean canJump(int[] nums) {
            final int n = nums.length;
            int last = n - 1;
            for (int i = last - 1; i >= 0; --i) {
                if (i + nums[i] >= last) {
                    last = i;
                }
            }
            return last == 0;
        }
    }

    public static void main(String[] args) {
        int[] a1 = {2, 3, 1, 1, 4};
        int[] a2 = {3, 2, 1, 0, 4};
        int[] a3 = {0};
        int[] a4 = {2, 0, 0};
        int[] a5 = {2, 5, 0, 0};

        Solution s = new Solution4();

        println(s.canJump(a1));// true
        println(s.canJump(a2));// false
        println(s.canJump(a3));// true
        println(s.canJump(a4));// true
        println(s.canJump(a5));// true
    }

}
