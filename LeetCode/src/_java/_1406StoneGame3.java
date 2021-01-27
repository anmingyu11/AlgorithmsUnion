package _java;

import base.Base;

public class _1406StoneGame3 extends Base {

    private static abstract class Solution {
        public abstract String stoneGameIII(int[] stoneValue);
    }

    private static class Solution1 extends Solution {
        int[] sums;
        Integer[] dp;
        int n;

        public String stoneGameIII(int[] stoneValue) {
            n = stoneValue.length;
            sums = new int[n];
            dp = new Integer[n];
            sums[n - 1] = stoneValue[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                sums[i] += stoneValue[i] + sums[i + 1];
            }
            int alice = auxiliary(0);
            int bob = sums[0] - alice;
            if (alice > bob) {
                return "Alice";
            } else if (bob > alice) {
                return "Bob";
            } else {
                return "Tie";
            }

        }

        private int auxiliary(int idx) {
            if (idx >= n) {
                return 0;
            }
            if (dp[idx] != null) {
                return dp[idx];
            }
            int min = 0x7fffffff;
            for (int k = 1; k <= 3; ++k) {
                min = Math.min(min, auxiliary(idx + k));
            }
            dp[idx] = sums[idx] - min;
            return dp[idx];
        }
    }

    private static class Solution2 extends Solution {

        public String stoneGameIII(int[] stoneValue) {
            final int n = stoneValue.length;
            int[] sums = new int[n];
            int[] dp = new int[n]; //  integer is slower than int.

            sums[n - 1] = stoneValue[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                sums[i] = sums[i + 1] + stoneValue[i];
            }

            // converge
            dp[n - 1] = sums[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                int max = 0x80000000;
                for (int k = 1; k <= 3; ++k) {
                    int res = 0;
                    if (i + k >= n) {
                        res = sums[i];
                    } else {
                        res = sums[i] - dp[i + k];
                    }
                    max = Math.max(max, res);
                }
                dp[i] = max;
            }

            int alice = dp[0];
            int bob = sums[0] - dp[0];

            if (alice > bob) {
                return "Alice";
            } else if (alice < bob) {
                return "Bob";
            } else {
                return "Tie";
            }
        }

    }

    private static class Solution3 extends Solution {
        public String stoneGameIII(int[] stoneValue) {
            final int n = stoneValue.length;
            int[] dp = new int[n];

            // converge
            int sums = stoneValue[n - 1];
            dp[n - 1] = sums;
            for (int i = n - 2; i >= 0; --i) {
                int max = 0x80000000;
                sums += stoneValue[i];
                for (int k = 1; k <= 3; ++k) {
                    int res = 0;
                    if (i + k >= n) {
                        res = sums;
                    } else {
                        res = sums - dp[i + k];
                    }
                    max = Math.max(max, res);
                }
                dp[i] = max;
            }

            int alice = dp[0];
            int bob = sums - dp[0];

            if (alice > bob) {
                return "Alice";
            } else if (alice < bob) {
                return "Bob";
            } else {
                return "Tie";
            }
        }
    }

    private static class Solution4 extends Solution {

        public String stoneGameIII(int[] stoneValue) {
            final int n = stoneValue.length;
            int best = 0x70000000, sums = 0;
            int dp0 = 0, dp1 = 0, dp2 = 0;

            for (int i = n - 1; i >= 0; --i) {
                sums += stoneValue[i];
                int minDp = Math.min(dp0, Math.min(dp1, dp2));
                best = sums - minDp;
                dp2 = dp1;
                dp1 = dp0;
                dp0 = best;
            }

            int alice = best;
            int bob = sums - best;

            if (alice > bob) {
                return "Alice";
            } else if (alice < bob) {
                return "Bob";
            } else {
                return "Tie";
            }
        }
    }


    public static void main(String[] args) {

    }
}
