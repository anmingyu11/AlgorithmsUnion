package _java;

import base.Base;

public class _0005LongestPalindromicSubstring________________ extends Base {

    private abstract static class Solution {
        public abstract String longestPalindrome(String s);
    }

    // Dp
    // Runtime: 35 ms, faster than 50.72% of Java online submissions for Longest Palindromic Substring.
    // Memory Usage: 39.5 MB, less than 19.20% of Java online submissions for Longest Palindromic Substring.
    private static class Solution1 extends Solution {

        @Override
        public String longestPalindrome(String s) {
            if (s.isEmpty()) {
                return s;
            }
            final int n = s.length();
            char[] str = s.toCharArray();
            boolean[][] dp = new boolean[n][n];
            // 单个字符串都是回文
            int l = 0, r = 0;
            for (int start = n - 1; start >= 0; --start) {
                if (r - l + 1 > n - start) {
                    break;
                }
                int i = start;
                // 从最后一个字符向前遍历
                // 查看 [i,n-1] 这个区间
                for (int j = n - 1; j >= i; --j) {
                    // s[i] == s[j]
                    if (!dp[i][j] && str[i] == str[j]) {
                        if (j - i < 2) {
                            dp[i][j] = true;
                        } else if (dp[i + 1][j - 1]) {
                            // 确定最大字符串长度的左右索引
                            dp[i][j] = true;
                        }
                        if (!dp[i][j]) {
                            continue;
                        }
                        if (j - i > r - l) {
                            l = i;
                            r = j;
                        }
                    }
                }
            }
            return s.substring(l, r + 1);
        }

    }

    //  人家的dp
    private static class Solution2 extends Solution {

        @Override
        public String longestPalindrome(String s) {
            if (s.isEmpty()) {
                return s;
            }
            final int n = s.length();
            boolean[][] dp = new boolean[n][n];
            char[] str = s.toCharArray();
            int l = 0, r = 0;
            for (int i = n - 1; i >= 0; --i) {
                for (int j = i; j < n; ++j) {
                    dp[i][j] = str[i] == str[j] && (j - i < 3 || dp[i + 1][j - 1]);
                    if (dp[i][j] && j - i > r - l) {
                        l = i;
                        r = j;
                    }
                }
            }
            return s.substring(l, r + 1);
        }

    }


    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "abcda";

        Solution s = new Solution2();

        println(s.longestPalindrome(s1));
        println(s.longestPalindrome(s2));
        println(s.longestPalindrome(s3));
    }
}
