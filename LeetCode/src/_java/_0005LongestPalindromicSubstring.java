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
public class _0005LongestPalindromicSubstring extends Base {

    private abstract static class Solution {
        public abstract String longestPalindrome(String s);
    }

    /**
     * Runtime: 48 ms, faster than 34.38% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 37.3 MB, less than 93.15% of Java online submissions for Longest Palindromic Substring.
     */
    private static class Solution1 extends Solution {

        public String longestPalindrome(String s) {
            final int n = s.length();
            if (n < 1) {
                return s;
            }
            boolean[][] dp = new boolean[n][n];
            char[] str = s.toCharArray();// 这个要比用charAt快一些
            int max = 0;
            int l = 0, r = 0;
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i <= j; ++i) {
                    int len = j - i;
                    dp[i][j] = (str[i] == str[j])
                            && (len < 3 || dp[i + 1][j - 1]);
                    if (dp[i][j] && len > max) {
                        max = len;
                        l = i;
                        r = j;
                    }
                }
            }
            return s.substring(l, r + 1);
        }

    }


    /**
     * Runtime: 7 ms, faster than 69.51% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Longest Palindromic Substring.
     */
    private static class Solution2 extends Solution {

        private char[] chArr;

        public String longestPalindrome(String s) {
            final int n = s.length();
            if (n < 1) {
                return s;
            }
            chArr = s.toCharArray();
            int l = 0, r = 0;
            for (int i = 0; i < n; ++i) {
                int len1 = expand(i, i);
                int len2 = expand(i, i + 1);
                if (Math.max(len1, len2) < r - l + 1) {
                    continue;
                }
                if (len1 > len2) {
                    len1 -= 1;
                    l = i - len1 / 2;
                    r = i + len1 / 2;
                } else {
                    len2 -= 2;
                    l = i - len2 / 2;
                    r = i + 1 + len2 / 2;
                }
            }
            return s.substring(l, r + 1);
        }

        private int expand(int lo, int hi) {
            final int n = chArr.length;
            int len = 0;
            for (int k = 0; k <= n / 2; ++k) {
                int l = lo - k, r = hi + k;
                if (l < 0 || r >= n
                        || chArr[l] != chArr[r]) {
                    return len;
                } else {
                    len = r - l + 1;
                }
            }
            return len;
        }

    }

    /**
     * Runtime: 6 ms, faster than 87.80% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Longest Palindromic Substring.
     * Offical solution
     */
    private static class Solution3 extends Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) return "";
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }
    }

    /**
     * 马拉车
     * <p>
     * Runtime: 6 ms, faster than 87.80% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Longest Palindromic Substring.
     */
    private static class Solution4 extends Solution {

        // 摆脱字符串长度奇偶分别处理.
        public String preProcess(String s) {
            StringBuilder sb = new StringBuilder();
            sb.append("^");
            for (int i = 0; i < s.length(); ++i) {
                sb.append("#");
                sb.append(s.charAt(i));
            }
            sb.append("#$");
            // origin len is n, after len is n + n - 1 + 4(^#str#$) = 2 * n + 3
            // ^ $ to ensure edge not the same.
            return sb.toString();
        }

        // 马拉车算法
        public String longestPalindrome(String s) {
            String str = preProcess(s); // 避免需要奇偶分别处理的情况。
            final int n = str.length();
            int[] P = new int[n];
            int C = 0, R = 0;
            for (int i = 1; i < n - 1; ++i) {
                // this is the dp part
                int iMirror = 2 * C - i;
                if (R > i) {
                    P[i] = Math.min(R - i, P[iMirror]);
                } else {
                    P[i] = 0;
                }
                // expand
                while (str.charAt(i - P[i]-1) == str.charAt(i + P[i]+1)) {
                    ++P[i];
                }
                // update C and R
                if (i + P[i] > R) {
                    C = i;
                    R = i + P[i];
                }
            }
            int maxLen = 0, center = 0;
            for (int i = 0; i < n; ++i) {
                if (P[i] > maxLen) {
                    maxLen = P[i];
                    center = i;
                }
            }
            center = (center - maxLen) / 2;
            return s.substring(center, center + maxLen);
        }

    }

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "abcda";
        String s4 = "abba";
        String s5 = "bb";
        String s6 = "";
        String s7 = "abcba";
        String s8 = "babcbabcbaccba";

        Solution s = new Solution4();

        println(s.longestPalindrome(s1)); // aba
        println(s.longestPalindrome(s2)); // bb
        println(s.longestPalindrome(s3)); // a
        println(s.longestPalindrome(s4)); // abba
        println(s.longestPalindrome(s5)); // bb
        println(s.longestPalindrome(s6)); //
        println(s.longestPalindrome(s7)); // abcba
        println(s.longestPalindrome(s8));
    }
}
