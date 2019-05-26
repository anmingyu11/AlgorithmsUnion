package _15DynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;

import base.Base;

/**
 * 原书中默认给的最长长度就是10,
 * 做了扩展,不知道对不对.
 */
public class CutTheRod extends Base {

    private abstract static class Solution {
        final int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        public abstract int cutRod(int rotLen);
    }

    /**
     * 朴素递归
     */
    private static class Solution1 extends Solution {

        @Override
        public int cutRod(int n) {
            if (n == 0) {
                return 0;
            }
            int max = Integer.MIN_VALUE;
            // Todo 这里应该有些争议
            for (int i = 1; i <= 10; ++i) {
                if (i > n) {
                    break;
                }
                max = Math.max(max, price[i - 1] + cutRod(n - i));
            }
            return max;
        }
    }

    /**
     * 自顶向下
     */
    private static class Solution2 extends Solution {

        @Override
        public int cutRod(int rotLen) {
            int[] dp = new int[rotLen + 1];
            Arrays.fill(dp, Integer.MIN_VALUE);
            return cutRodAux(rotLen, dp);
        }

        private int cutRodAux(int rotLen, int[] dp) {
            if (dp[rotLen] >= 0) {
                return dp[rotLen];
            }
            int max = Integer.MIN_VALUE;
            if (rotLen == 0) {
                max = 0;
            } else {
                for (int i = 1; i <= 10; ++i) {
                    if (i > rotLen) {
                        break;
                    }
                    max = Math.max(max, price[i - 1] + cutRodAux(rotLen - i, dp));
                }
            }

            return max;
        }

    }

    private static class Solution3 extends Solution {

        @Override
        public int cutRod(int rotLen) {
            int[] dp = new int[rotLen + 1];
            dp[0] = 0;
            for (int i = 1; i <= rotLen; ++i) {
                int max = Integer.MIN_VALUE;
                for (int j = 1; j <= 10; ++j) {
                    if (j > i || j > rotLen) {
                        break;
                    }
                    max = Math.max(max, price[j - 1] + dp[i - j]);
                }
                dp[i] = max;
            }
            return dp[rotLen];
        }
    }

    private static void test(Solution s) {
        Solution1 checker = new Solution1();
        boolean pass = true;
        LinkedList<Integer> sl = new LinkedList<>();
        LinkedList<Integer> cl = new LinkedList<>();
        for (int i = 1; i <= 20; ++i) {
            int sR = s.cutRod(i);
            int cR = checker.cutRod(i);
            sl.add(sR);
            cl.add(cR);
            if (sR != cR) {
                pass = false;
                println("failed in : " + i);
                break;
            }
        }
        println("yours : " + sl);
        println("real : " + cl);
        println("pass : " + pass);

    }

    public static void main(String[] args) {
        Solution s = new Solution3();

        test(s);
    }

}
