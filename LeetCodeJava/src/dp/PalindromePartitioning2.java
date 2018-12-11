package dp;

import base.Base;

public class PalindromePartitioning2 extends Base {

    public static int minCut(String s) {
        char[] c = s.toCharArray();
        final int n = c.length;
        boolean[][] dp = new boolean[n][n];
        int cut[] = new int[n];

        for (int i = 0; i < n; ++i) {
            int min = i;
            for (int j = 0; j <= i; ++j) {
                if (c[i] == c[j] && (j + 1 > i - 1 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }

        return cut[n - 1];
    }

    public static void main(String[] args) {
        String s1 = "aabcbaa";
        println(minCut(s1));
        String s2 = "aab";
        println(minCut(s2));
        String s3 = "abaccb";
        println(minCut(s3));
    }
}
