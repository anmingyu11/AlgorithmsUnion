package _java;

import base.Base;

public class _1563StoneGame5 extends Base {

    private static abstract class Solution {
        public abstract int stoneGameV(int[] stoneValue);
    }

    private static class Solution1 extends Solution {
        private int[] dp;
        private int[][] dp2;

        public int stoneGameV(int[] stoneValue) {
            final int n = stoneValue.length;
            // DP Histogram.
            dp = new int[n + 1];
            dp2 = new int[n][n];
            dp[n - 1] = stoneValue[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                dp[i] = dp[i + 1] + stoneValue[i];
            }
            return auxiliary(0, n - 1);
        }

        private int auxiliary(int lo, int hi) {
            if (lo >= hi) {
                return 0;
            }
            if (dp2[lo][hi] > 0) {
                return dp2[lo][hi];
            }
            int max = 0x80000000;
            for (int c = lo; c < hi; ++c) {
                int res = 0;
                int left = dp[lo] - dp[c + 1];
                int right = dp[c + 1] - dp[hi + 1];
                if (left > right) {
                    res = right + auxiliary(c + 1, hi);
                } else if (right > left) {
                    res = left + auxiliary(lo, c);
                } else {
                    res = left + Math.max(auxiliary(c + 1, hi), auxiliary(lo, c));
                }
                max = Math.max(max, res);
            }
            dp2[lo][hi] = max;
            return max;
        }
    }

    private static class Solution2 extends Solution {

        public int stoneGameV(int[] stoneValue) {
            final int n = stoneValue.length;

            // DP Histogram.
            int[] dp = new int[n + 1];
            int[][] dp2 = new int[n][n];

            dp[n - 1] = stoneValue[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                dp[i] = dp[i + 1] + stoneValue[i];
            }

            for (int i = 0; i < n - 1; ++i) {
                dp2[i][i + 1] = Math.min(stoneValue[i], stoneValue[i + 1]);
            }

            for (int k = 2; k <= n - 1; ++k) {
                for (int i = 0; i <= n - 1 - k; ++i) {
                    int max = 0x80000000;
                    for (int c = 0; c < k; ++c) {
                        int left = dp[i] - dp[i + c + 1];
                        int right = dp[i + c + 1] - dp[i + k + 1];

                        if (left < right) {
                            max = Math.max(max, left + dp2[i][i + c]);
                        } else if (right < left) {
                            max = Math.max(max, right + dp2[i + c + 1][i + k]);
                        } else {
                            max = Math.max(max, left + Math.max(dp2[i][i + c], dp2[i + c + 1][i + k]));
                        }
                    }
                    dp2[i][i + k] = max;
                }
            }

            return dp2[0][n - 1];
        }

    }


    private static class Solution3 extends Solution {

        public int stoneGameV(int[] stone) {
            int n = stone.length - 1;
            // Head
            int e = 0;
            // Tail
            int s = n;
            // sum Left
            int sumL = 0;
            // sum Right
            int sumR = 0;

            while (e < s) {
                int diff = (sumL + stone[e]) - (sumR + stone[s]);
                if (diff <= 0) sumL += stone[e++];
                if (diff >= 0) sumR += stone[s--];
            }

            int p = score(stone, 0, e - 1);
            int q = score(stone, n, s + 1);
            return Math.max(p, q);
        }

        /* dynamic programming for scores */
        private int score(int[] stone, int e, int m) {
            if (m < 0 || m >= stone.length) {
                return 0;
            }

            int n = Math.abs(e - m) + 1;
            int sig = (e == 0) ? 1 : -1;

            int[][] dp = new int[n][n];
            int[] col = new int[n];  // keep track of index of left bound
            int[] row = new int[n];  // keep track of index of down prime

            /* set the main diagonal and the upper triangular */
            dp[0][0] = stone[e];
            for (int c = 1; c < n; ++c) {
                col[c] = c;
                dp[c][c] = stone[e + c * sig];
                dp[0][c] = dp[0][c - 1] + dp[c][c];
            }

            /* calculate and store scores to the lower triangular */
            for (int c = 1; c < n; ++c) {
                for (int r = c - 1; r >= 0; --r) {

                    dp[r][c] = dp[r][r] + dp[r + 1][c];
                    // if current cell is absolutely invalid
                    if (dp[r][c] * 2 > dp[0][c]) r = 0;

                    // update <reference point: col[?]>
                    while (dp[r][col[r]] * 2 <= dp[r][c]) col[r]++;

                    // locate <left> and <down> preliminarily
                    int left = (col[r] == r) ? 0 : dp[col[r] - 1][r];
                    int down = (col[r] == c) ? 0 : dp[c][col[r] + 1];

                    // pinpoint <down>
                    if (col[r] > 0 && dp[r][col[r] - 1] * 2 == dp[r][c]) {
                        down = dp[c][col[r]];
                    }
                    if (row[c] > 0 && dp[row[c]][c] * 2 <= dp[r][c]) {
                        down = Math.max(down, dp[c][row[c]]);
                    }
                    // store <score> at dp(c, r)
                    dp[c][r] = dp[r][c] + Math.max(left, down);

                    // update <down'>
                    if (dp[c][r] < dp[c][r + 1]) row[c] = r + 1;
                }
                // pass the max towards corner
                dp[c][0] = Math.max(dp[c][0], dp[c - 1][0]);
            }
            return dp[n - 1][0];
        }
    }

    public static void main(String[] args) {
        int[] A1 = {6, 2, 3, 4, 5, 5};
        int[] A2 = {5, 5};
        Solution s = new Solution3();
        //println(s.stoneGameV(A1)); // 18
        println(s.stoneGameV(A1)); // 0
    }

}
