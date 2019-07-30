package _java;

import base.Base;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * <p>
 * Once you pay the cost,
 * you can either climb one or two steps.
 * You need to find minimum cost to reach the top of the floor,
 * and you can either start from the step with index 0, or the step with index 1.
 * <p>
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 */
public class _0746MinCostClimbingStairs extends Base {

    private abstract static class Solution {
        public abstract int minCostClimbingStairs(int[] cost);
    }

    /**
     * Runtime: 1 ms, faster than 99.92% of Java online submissions for Min Cost Climbing Stairs.
     * Memory Usage: 40.5 MB, less than 11.35% of Java online submissions for Min Cost Climbing Stairs.
     */
    private static class Solution1 extends Solution {

        @Override
        public int minCostClimbingStairs(int[] cost) {
            final int n = cost.length;
            int[] dp = new int[n + 2];
            for (int i = 2; i < n + 2; ++i) {
                int costI = cost[i - 2];
                dp[i] = Math.min(dp[i - 1] + costI, dp[i - 2] + costI);
            }
            return Math.min(dp[n + 1], dp[n]);
        }
    }

    /**
     * Intuition
     *
     * There is a clear recursion available: the final cost f[i] to climb the staircase from some step i is f[i] = cost[i] + min(f[i+1], f[i+2]). This motivates dynamic programming.
     *
     * Algorithm
     *
     * Let's evaluate f backwards in order. That way, when we are deciding what f[i] will be, we've already figured out f[i+1] and f[i+2].
     *
     * We can do even better than that. At the i-th step, let f1, f2 be the old value of f[i+1], f[i+2],
     * and update them to be the new values f[i], f[i+1].
     * We keep these updated as we iterate through i backwards. At the end, we want min(f1, f2).
     */
    private static class Solution2 extends Solution {

        @Override
        public int minCostClimbingStairs(int[] cost) {
            final int n = cost.length;
            int f1 = 0, f2 = 0;
            for (int i = n - 1; i >= 0; --i) {
                int f0 = cost[i] + Math.min(f1, f2);
                f2 = f1;
                f1 = f0;
            }
            return Math.min(f1, f2);
        }

    }

    public static void main(String[] args) {
        int[] cost1 = {10, 15, 20};
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        Solution s = new Solution2();

        println(s.minCostClimbingStairs(cost1)); // 15
        println(s.minCostClimbingStairs(cost2)); // 6
    }
}
