package greedy;

import java.util.Arrays;

import base.Base;

public class JumpGame1 extends Base {

    //back tracking
    static class Solution1 {
        public static boolean canJump(int[] nums) {
            return canJumpFromPosition(0, nums);
        }

        public static boolean canJumpFromPosition(int position, int[] nums) {
            final int len = nums.length - 1;
            //递归终止条件
            if (position == len) {
                return true;
            }
            int furtherPosition = Math.min(position + nums[position], len);
            //for (int i = position + 1; i <= furtherPosition; ++i) {
            for (int i = furtherPosition; i > position; --i) {
                if (canJumpFromPosition(i, nums)) {
                    return true;
                }
            }
            return false;
        }
    }

    //Dp topDown
    static class Solution2 {
        enum Index {
            GOOD, BAD, UNKNOWN
        }

        static Index[] memo;

        public static boolean canJump(int[] nums) {
            final int len = nums.length;
            memo = new Index[len];
            Arrays.fill(memo, Index.UNKNOWN);
            memo[len - 1] = Index.GOOD;
            return canJumpFromPosition(0, nums);
        }

        public static boolean canJumpFromPosition(int position, int[] nums) {
            final int len = nums.length;
            //不处理的点
            if (memo[position] == Index.BAD) {
                return false;
            }
            //胜利条件
            if (position == len - 1) {
                return true;
            }

            int furtherPosition = Math.min(len - 1, position + nums[position]);
            for (int i = furtherPosition; i > position; --i) {
                if (canJumpFromPosition(i, nums)) {
                    return true;
                } else {
                    memo[i] = Index.BAD;
                }
            }

            return false;
        }
    }

    static class Solution3 {
        enum Index {
            GOOD, BAD, UNKNOWN
        }

        public static boolean canJump(int[] nums) {
            final int len = nums.length;
            Index[] memo = new Index[len];
            memo[len - 1] = Index.GOOD;
            //更加先进的初始化流程
            for (int i = 0; i < len - 1; ++i) {
                if (nums[i] == 0) {
                    //不能跳
                    memo[i] = Index.BAD;
                } else {
                    memo[i] = Index.UNKNOWN;
                }
            }


            for (int i = len - 2; i >= 0; --i) {
                if (memo[i] == Index.BAD) {
                    continue;
                }
                final int further = Math.min(nums[i] + i, len - 1);
                for (int j = i + 1; j <= further; ++j) {
                    if (memo[j] == Index.GOOD) {
                        memo[i] = Index.GOOD;
                        break;
                    }
                }
            }

            return memo[0] == Index.GOOD;
        }
    }

    static class Solution4 {
        public static boolean canJump(int[] nums) {
            final int len = nums.length - 1;
            int lastPos = len;
            for (int i = len-1; i >= 0; --i) {
                println("i : " + i + "can Jump To : " +(nums[i] + i));
                if (i + nums[i] >= lastPos) {
                    lastPos = i;
                }
            }
            return lastPos == 0;
        }
    }

    public static void main(String[] args) {
        int[] trueNums = {2, 3, 1, 1, 4};
        int[] falseNums = {3, 2, 1, 0, 4};
        int[] falseNums2 = {2, 5, 0, 0};

        //Solution1 test
        //Solution1.canJump(trueNums);
        //Solution1.canJump(falseNums);

        //Solution2 test
        //println(Solution2.canJump(trueNums));
        //println(Solution2.canJump(falseNums));
        //println(Solution2.canJump(falseNums2));

        //Solution3 test
        //println(Solution3.canJump(trueNums));
        //println(Solution3.canJump(falseNums));
        //println(Solution3.canJump(falseNums2));

        //Solution4 test
        println(Solution4.canJump(trueNums));
        println(Solution4.canJump(falseNums));
        println(Solution4.canJump(falseNums2));
    }
}
