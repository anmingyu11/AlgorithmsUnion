package _15dp;

import java.util.Arrays;

import base.Base;
import base.StopwatchCPU;

public class CutTheRod extends Base {

    static class Solution {

        private final int[] prices = new int[]{
                0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30
        };

        private final int[] realResult = new int[]{
                0, 1, 5, 8, 10, 13, 17, 18, 22, 25, 30
        };

        //The CUT-ROD
        private int naiveRecur(int n) {
            if (n == 0) {
                return 0;
            }

            int res = prices[n];
            // 这里i不能小于等于n 因为如果这样的话naiveRecur就会又来一次naiveRecur(n),会造成栈溢出，
            // res = prices[n]已经是不切割的最大值，也就是意味着这是i = 0时的结果，那么i就可以从1开始计算
            for (int i = 1; i < n; ++i) {
                res = Math.max(res, naiveRecur(i) + prices[n - i]);
            }

            return res;
        }

        private int top2BottomMemorized(int n) {
            int[] r = new int[n + 1];
            Arrays.fill(r, Integer.MIN_VALUE);
            return top2BottomMemorizedSub(n, r);
        }

        private int top2BottomMemorizedSub(int n, int[] r) {
            if (n == 0) {
                return r[n] = 0;
            }
            if (r[n] >= 0) {
                return r[n];
            }

            int res = prices[n];
            for (int i = 1; i < n; ++i) {
                res = Math.max(top2BottomMemorizedSub(i, r) + prices[n - i], res);
            }

            r[n] = res;
            return res;
        }

        //子问题解决完了之后再解决上一层问题
        private int bottom2TopDP(int n) {
            int[] dp = new int[n + 1];

            dp[0] = 0;
            for (int i = 1; i <= n; ++i) {
                int max = Integer.MIN_VALUE;
                for (int j = i; j >= 0; --j) {
                    //dp[i-j] i-j的切分最大值，和j切分的最大值
                    max = Math.max(max, dp[i - j] + prices[j]);
                }
                dp[i] = max;
            }

            return dp[n];
        }

        private int[] bottom2TopDPExtends(int n) {
            int[] dp = new int[n + 1];
            int[] res = new int[n + 1];

            dp[0] = 0;
            for (int i = 1; i <= n; ++i) {
                int max = Integer.MIN_VALUE;
                for (int j = i; j >= 0; --j) {
                    if (max < prices[j] + dp[i - j]) {
                        max = prices[j] + dp[i - j];
                        res[i] = j;
                    }
                }
                dp[i] = max;
            }

            return res;
        }

    }

    public static void main(String[] args) {
        //realTestSolution(3);
        ////*2
        //realTestSolution(2);
        ////*4
        //realTestSolution(1);
        realPrintAResult();
    }

    private static void realPrintAResult() {
        for (int i = 1; i <= 10; ++i) {
            printAResult(i);
        }
    }

    //不知如何验证正确性
    private static void printAResult(int len) {
        Solution s = new Solution();
        int[] res = s.bottom2TopDPExtends(len);
        int n = res.length - 1;
        //println(Arrays.toString(res));
        while (n > 0) {
            int tmpN = n - res[n];
            if (tmpN <= 0) {
                print(res[n]);
            } else {
                print(res[n] + " : ");
            }
            n = tmpN;
        }
        println("");
    }

    private static void realTestSolution(int way) {
        StopwatchCPU s = new StopwatchCPU();
        for (int i = 1; i <= 10; ++i) {
            testSolution(way, i);
        }
        println("elapse time : " + s.elapsedTime2());
    }

    private static void testSolution(int way, int n) {
        Solution s = new Solution();
        int res = 0;
        switch (way) {
            case 1:
                res = s.naiveRecur(n);
                break;
            case 2:
                res = s.top2BottomMemorized(n);
                break;
            case 3:
                res = s.bottom2TopDP(n);
                break;
            default:
                throw new IllegalArgumentException();
        }
        println("yourResult : r" + n + " = " + res + "    realResult : " + s.realResult[n]);
    }

}
