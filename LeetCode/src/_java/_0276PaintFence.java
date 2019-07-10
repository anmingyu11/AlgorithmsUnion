package _java;

import base.Base;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * <p>
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * <p>
 * Return the total number of ways you can paint the fence.
 * <p>
 * Note:
 * n and k are non-negative integers.
 * <p>
 * Example:
 * <p>
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 * <pre>
 *             post1  post2  post3
 *  -----      -----  -----  -----
 *    1         c1     c1     c2
 *    2         c1     c2     c1
 *    3         c1     c2     c2
 *    4         c2     c1     c1
 *    5         c2     c1     c2
 *    6         c2     c2     c1
 *  </pre>
 */
public class _0276PaintFence extends Base {

    private abstract static class Solution {
        public abstract int numWays(int n, int k);
    }

    /**
     * If you are painting the ith post, you have two options:
     * <p>
     * 1. make it different color as i-1th post
     * 2. make it same color as i-1 th post (if you are allowed!)
     * <p>
     * simply add these for your answer:
     * <p>
     * num_ways(i) = num_ways_diff(i) + num_ways_same(i)
     * 对于i有两大类涂法,一种涂成i-1同颜色,一种涂成i-1不同颜色
     * <p>
     * Now just think of how to calculate each of those functions.
     * <p>
     * The first one is easy.
     * If you are painting the ith post,
     * how many ways can you paint it to make it different from the i-1 th post?k-1
     * <p>
     * num_ways_diff(i) = num_ways(i-1) * (k-1)
     * 不同的, dp[i-1]* (k-1)
     * <p>
     * The second one is hard, but not so hard when you think about it.
     * <p>
     * If you are painting the ith post,
     * how many ways can you paint it to make it the same as the i-1th post?
     * At first, we should think the answer is 1 -- it must be whatever color the last one was.
     * <p>
     * num_ways_same(i) = num_ways(i-1) * 1
     * 如果这样的话那就错了,因为dp[i-1]中有和i-2相同的项,这样就三个相同了.
     * <p>
     * But no!
     * This will fail in the cases where painting the last post the same results
     * in three adjacent posts of the same color!
     * <p>
     * We need to consider ONLY the cases where the last two colors were different. But we can do that!
     * <p>
     * num_ways_diff(i-1) <- all the cases where the i-1th and i-2th are different.
     * <p>
     * THESE are the cases where can just plop the same color to the end,
     * and no longer worry about causing three in a row to be the same.
     * <p>
     * num_ways_same(i) = num_ways_diff(i-1) * 1
     * 所以选择与i-2不同的项,即dp[i-2]*(k-1)
     * <p>
     * We sum these for our answer, like I said before:
     * <p>
     * num_ways(i) = num_ways_diff(i) + num_ways_same(i)
     * = num_ways(i-1) * (k-1) + num_ways_diff(i-1)
     * <p>
     * We know how to compute num_ways_diff, so we can substitute:
     * num_ways(i) = num_ways(i-1) * (k-1) + num_ways(i-2) * (k-1)
     * <p>
     * We can even simplify a little more:
     * <p>
     * num_ways(i) = (num_ways(i-1) + num_ways(i-2)) * (k-1)
     * <p>
     * As a note, trying to intuitively understand that last line is impossible.
     * If you think you understand it intuitively, you are fooling yourself.
     * Only the original equation makes intuitive sense.
     * <p>
     * Once you have this,
     * the code is trivial
     * (but overall, this problem is certainly not an easy problem, despite the leetcode tag!):
     */
    private static class Solution1 extends Solution {

        @Override
        public int numWays(int n, int k) {
            if (n == 0 || k == 0) {
                return 0;
            }
            if (n == 1) {
                return k;
            }
            if (n == 2) {
                return k * k;
            }
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = k;
            dp[2] = k * k;
            for (int i = 3; i <= n; ++i) {
                dp[i] = (
                        dp[i - 1] // 与 i-1不同色.
                                + dp[i - 2] // 与 i-2不同色.
                ) * (k - 1);
            }
            return dp[n];
        }

    }

    public static void main(String[] args) {

        Solution s = new Solution1();

        println(s.numWays(3, 2)); // 6
        println(s.numWays(0, 0)); // 0
        println(s.numWays(1, 1)); // 1
        println(s.numWays(4, 2)); // 10
        println(s.numWays(4, 1)); // 0
    }
}
