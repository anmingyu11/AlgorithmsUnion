package dp;

import base.Base;

public class DistinctSubsequences extends Base {

    // naive backtrack
    static class Solution1 {

        private int ways;

        public int numDistinct(String s, String t) {
            backtrack(s, t, 0, 0, 0);
            return ways;
        }

        private void backtrack(String s, String t, int sPos, int tPos, int chosen) {
            if (s.length() - sPos < t.length() - tPos) {
                return;
            }
            if (chosen == t.length()) {
                ++ways;
                return;
            }

            for (int i = sPos; i < s.length(); ++i) {
                if (s.charAt(i) == t.charAt(tPos)) {
                    ++tPos;
                    ++chosen;
                    backtrack(s, t, i + 1, tPos, chosen);
                    --tPos;
                    --chosen;
                }
            }

        }

    }

    static class Solution2 {

        public int numDistinct(String s, String t) {
            // how many T[0,i] involved in S[0,j]
            int[][] dp = new int[t.length() + 1][s.length() + 1];

            // 双空串
            dp[0][0] = 1;
            for (int j = 1; j <= s.length(); ++j) {
                dp[0][j] = 1;
            }

            // i 代表 t的长度 , j 代表 s的长度。
            for (int i = 0; i < t.length(); ++i) {
                for (int j = 0; j < s.length(); ++j) {
                    if (t.charAt(i) == s.charAt(j)) {
                        // 没有s新字符，和没有s新字符也没有t新字符
                        dp[i + 1][j + 1] = dp[i][j] + dp[i + 1][j];
                    } else {
                        // 跟没有s新字符一样
                        dp[i + 1][j + 1] = dp[i + 1][j];
                    }
                }
            }

            return dp[t.length()][s.length()];
        }

    }

    public static void main(String[] args) {
        test1();
        test2();
        //test3();
        //test4();
    }

    private static void test1() {
        String s1 = "rabbbit", s2 = "rabbit";
        println(new Solution2().numDistinct(s1, s2));
    }

    private static void test2() {
        String s1 = "babgbag", s2 = "bag";
        println(new Solution2().numDistinct(s1, s2));
    }

    private static void test3() {
        String s1 = "", s2 = "";
        println(new Solution2().numDistinct(s1, s2));
    }

    private static void test4() {
        String s1 = "abc", s2 = "d";
        println(new Solution2().numDistinct(s1, s2));
    }
}
