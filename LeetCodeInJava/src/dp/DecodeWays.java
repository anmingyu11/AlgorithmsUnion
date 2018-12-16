package dp;

import base.Base;

public class DecodeWays extends Base {
    static class Solution1 {

        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = s.charAt(0) != '0' ? 1 : 0;
            for (int i = 2; i <= n; ++i) {
                int first = Integer.parseInt(s.substring(i - 1, i));
                int second = Integer.parseInt(s.substring(i - 2, i));
                if (first > 0 && first < 10) {
                    dp[i] += dp[i - 1];
                }
                if (second >= 10 && second < 27) {
                    dp[i] += dp[i - 2];
                }
            }

            return dp[n];
        }

    }


    public static void main(String[] args) {
        String s1 = "12";
        String s2 = "226";
        String s3 = "1230451";
        println(new Solution1().numDecodings(s1));
        println(new Solution1().numDecodings(s2));
        println(new Solution1().numDecodings(s3));
    }
}
