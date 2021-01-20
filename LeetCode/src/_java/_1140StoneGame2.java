package _java;

import java.util.Arrays;

import base.Base;

/**
 * Alice and Bob continue their games with piles of stones.
 * <p>
 * There are a number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].
 * <p>
 * The objective of the game is to end with the most stones.
 * <p>
 * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
 * <p>
 * On each player's turn, that player can take all the stones in the first X remaining piles,
 * where 1 <= X <= 2M.  Then, we set M = max(M, X).
 * <p>
 * The game continues until all the stones have been taken.
 * <p>
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 * <p>
 * Example 1:
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alice takes one pile at the beginning, Bob takes two piles,
 * then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total.
 * If Alice takes two piles at the beginning, then Bob can take all three piles left.
 * In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
 * <p>
 * Example 2:
 * Input: piles = [1,2,3,4,5,100]
 * Output: 104
 * <p>
 * Constraints:
 * <p>
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 104
 */
public class _1140StoneGame2 extends Base {

    private static abstract class Solution {
        public abstract int stoneGameII(int[] piles);
    }

    private static class Solution1 extends Solution {

        private int n;
        private int[] sums;
        private int[][] dp;

        public int stoneGameII(int[] piles) {
            this.n = piles.length;
            // sums dp, cal the fold right sum.
            sums = new int[n];
            // dp[i][j]
            // i denote index the player reach
            // j denote when reach index i, the player current m.
            dp = new int[n][n];
            sums[n - 1] = piles[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                sums[i] = sums[i + 1] + piles[i];
            }
            return auxiliary(0, 1);
        }

        private int auxiliary(int idx, int m) {
            if (idx + 2 * m >= n) {
                // converge condition, which is the most important.
                // it means when the player reach idx,m, will take as most stone as can take.
                return sums[idx];
            }
            if (dp[idx][m] > 0) {
                // dp mem
                return dp[idx][m];
            }
            int left = 1, right = 2 * m, min = 0x7fffffff;
            for (int x = left; x <= right; ++x) {
                // auxiliary represent the next player can gain.
                // min rep the next player can gain min.
                min = Math.min(min, auxiliary(idx + x, Math.max(x, m)));
            }
            // this is the problem defined.
            // if min denote the min stones next player can take.
            // sums[idx] - min : at idx,m , num of stones current player can take.
            // sums[i] means the all the piles from i to right.
            // min means the next player can gain when current player has been taken.
            // so when they subtract, cut the piles into two piles represent the current player can take or next player can take respectively.
            dp[idx][m] = sums[idx] - min;
            return dp[idx][m];
        }

    }

    // max
    private static class Solution2 extends Solution {

        private int n;
        private int[] sums;
        private int[][] dp;

        public int stoneGameII(int[] piles) {
            n = piles.length;
            sums = Arrays.copyOf(piles, n);
            dp = new int[n][n];
            for (int i = n - 2; i >= 0; --i) {
                sums[i] += sums[i + 1];
            }
            return auxiliary(0, 1);
        }

        // aux denote the curr player can gain max;
        private int auxiliary(int idx, int m) {
            if (idx + 2 * m >= n) {
                return sums[idx];
            }
            if (dp[idx][m] > 0) {
                return dp[idx][m];
            }
            int left = 1, right = 2 * m, max = 0x80000000;
            for (int x = left; x <= right; ++x) {
                int take = sums[idx] - sums[idx + x];
                max = Math.max(max, take + sums[idx + x] - auxiliary(idx + x, Math.max(m, x)));
            }
            dp[idx][m] = max;
            return dp[idx][m];
        }

    }

    public static void main(String[] args) {
        int[] piles = {2, 7, 9, 4, 4};
        final int n = piles.length;
        Solution s;
        s = new Solution1();
        println(s.stoneGameII(piles));
    }
}
