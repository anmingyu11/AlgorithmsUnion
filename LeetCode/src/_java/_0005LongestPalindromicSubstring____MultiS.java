package _java;

import base.Base;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class _0005LongestPalindromicSubstring____MultiS extends Base {

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

    /**
     * Runtime: 6 ms, faster than 87.09% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Longest Palindromic Substring.
     * <p>
     * 经典的数组下标处理,非常值得回味.
     */
    private static class Solution3 extends Solution {

        private String str;

        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            this.str = s;
            int start = 0, end = 0, max = 0;
            for (int i = 0; i < s.length(); ++i) {
                int l1 = searchAroundCenter(i, i);
                int l2 = searchAroundCenter(i, i + 1);
                int l = Math.max(l1, l2);
                if (l > max) {
                    max = l;
                    start = i - (l - 1) / 2;
                    end = i + l / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int searchAroundCenter(int lo, int hi) {
            if (hi == str.length()) {
                return 0;
            }
            while (lo >= 0 && hi < str.length()
                    && str.charAt(lo) == str.charAt(hi)) {
                --lo;
                ++hi;
            }
            //println("l : " + lo + " r : " + hi);
            return (hi - 1) - (lo + 1) + 1;
        }

    }

    //Todo 马拉车
    private static class Solution4 extends Solution {

        @Override
        public String longestPalindrome(String s) {
            return null;
        }

    }

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "abcda";
        String s4 = "abba";
        String s5 = "bb";

        Solution s = new Solution3();

        println(s.longestPalindrome(s1)); // aba
        //println(s.longestPalindrome(s2)); // bb
        //println(s.longestPalindrome(s3)); // a
        //println(s.longestPalindrome(s4)); // abba
        //println(s.longestPalindrome(s5)); // bb
    }
}
