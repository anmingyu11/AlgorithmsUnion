package _java;

import base.Base;

public class _0070ClimbingStairs extends Base {

    private abstract static class Solution {
        public abstract int climbStairs(int n);
    }

    // DP1
    private static class Solution1 extends Solution {

        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i < n; ++i) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n - 1];
        }
    }

    //dp 改
    private static class Solution2 extends Solution {

        @Override
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }

            int step1 = 1, step2 = 2;
            for (int i = 2; i < n; ++i) {
                int tmp = step1 + step2;
                step1 = step2;
                step2 = tmp;
            }
            return step2;
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution2();

        for (int i = 2; i < 10; ++i) {
            println(s.climbStairs(i));
        }
        // 2
        // 3
        // 5
        // 8
        // 13
        // 21
        // 34
        // 55
    }

}
