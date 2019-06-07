package _java;

import base.Base;

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to
 * two non-empty substrings recursively.
 * <p>
 * Below is one possible representation of s1 = "great":
 * <p>
 * <pre>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * </pre>
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * <p>
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * <p>
 * <pre>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 *           / \
 *          a   t
 * </pre>
 * We say that "rgeat" is a scrambled string of "great".
 * <p>
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".:w
 * <p>
 * We say that "rgtae" is a scrambled string of "great".
 * <p>
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Example 2:
 * <p>
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 */
public class _0087ScrambleString extends Base {

    private abstract static class Solution {
        public abstract boolean isScramble(String s1, String s2);
    }

    /**
     * Runtime: 2 ms, faster than 94.15% of Java online submissions for Scramble String.
     * Memory Usage: 41.1 MB, less than 25.57% of Java online submissions for Scramble String.
     * 递归解法比较容易
     * O(4^n)
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean isScramble(String s1, String s2) {
            final int n = s1.length();
            if (n != s2.length()) {
                return false;
            }
            if (s1.equals(s2)) {
                return true;
            }
            int[] letter = new int[26];
            for (int i = 0; i < n; ++i) {
                ++letter[s1.charAt(i) - 'a'];
                --letter[s2.charAt(i) - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                if (letter[i] != 0) {
                    return false;
                }
            }

            for (int i = 1; i < n; ++i) {
                if (isScramble(s1.substring(0, i), s2.substring(0, i))
                        && isScramble(s1.substring(i), s2.substring(i))) {
                    return true;
                }
                if (isScramble(s1.substring(0, i), s2.substring(n - i))
                        && isScramble(s1.substring(i), s2.substring(0, n - i))) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * 三维动态规划的题目，我们提出维护量res[i][j][n]，其中i是s1的起始字符，j是s2的起始字符，而n是当前的字符串长度，
     * res[i][j][len]表示的是以i和j分别为s1和s2起点的长度为len的字符串是不是互为scramble。
     * <p>
     * 有了维护量我们接下来看看递推式，也就是怎么根据历史信息来得到res[i][j][len]。
     * 判断这个是不是满足，其实我们首先是把当前s1[i...i+len-1]字符串劈一刀分成两部分，然后分两种情况：
     * 第一种是左边和s2[j...j+len-1]左边部分是不是scramble，以及右边和s2[j...j+len-1]右边部分是不是scramble；
     * 第二种情况是左边和s2[j...j+len-1]右边部分是不是scramble，以及右边和s2[j...j+len-1]左边部分是不是scramble。
     * <p>
     * 如果以上两种情况有一种成立，说明s1[i...i+len-1]和s2[j...j+len-1]是scramble的。
     * 而对于判断这些左右部分是不是scramble我们是有历史信息的，
     * 因为长度小于n的所有情况我们都在前面求解过了（也就是长度是最外层循环）。
     * O(n^4)
     * Runtime: 12 ms, faster than 16.40% of Java online submissions for Scramble String.
     * Memory Usage: 36.6 MB, less than 83.48% of Java online submissions for Scramble String.
     */
    private static class Solution2 extends Solution {

        @Override
        public boolean isScramble(String s1, String s2) {
            int n = s1.length();
            if (n != s2.length()) {
                return false;
            }
            if (s1.equals(s2) || n == 0) {
                return true;
            }
            boolean[][][] dp = new boolean[n][n][n + 1];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
                }
            }

            for (int len = 2; len <= n; ++len) {
                for (int i = 0; i < n - len + 1; ++i) {
                    for (int j = 0; j < n - len + 1; ++j) {
                        for (int k = 1; k < len; ++k) {
                            dp[i][j][len] |=
                                    dp[i][j][k] && dp[i + k][j + k][len - k]
                                            || dp[i][j + len - k][k] && dp[i + k][j][len - k];
                            // s1(i...i+k)与s2(i...i+k),s1(i+k...len)与s2(i+k...len);
                            // 第一种情况:
                            // ([i...k][k...len])([j...k][k...len]) 先判断左面部分,然后判断右面部分,区间是i...i+len,j...j+len
                            // 第二种情况:
                            // ([i...k][j+len-k...len])([i+k...len][j...len-k])
                        }
                    }
                }
            }
            return dp[0][0][n];
        }

    }

    public static void main(String[] args) {

    }
}