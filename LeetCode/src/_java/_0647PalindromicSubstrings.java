package _java;

import base.Base;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes
 * or end indexes are counted as different substrings even they consist of same characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class _0647PalindromicSubstrings extends Base {

    private abstract static class Solution {
        public abstract int countSubstrings(String s);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Palindromic Substrings.
     * Memory Usage: 33.4 MB, less than 100.00% of Java online submissions for Palindromic Substrings.
     */
    private static class Solution1 extends Solution {

        private int count = 0;

        @Override
        public int countSubstrings(String s) {
            final int n = s.length();
            if (n == 0) {
                return 0;
            }
            for (int i = 0; i < n; ++i) {
                palindrome(s, i, i);
                palindrome(s, i, i + 1);
            }
            return count;
        }

        private void palindrome(String s, int i, int j) {
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {    //Check for the palindrome string
                ++count;    //Increment the count if palindromin substring found
                --i;    //To trace string in left direction
                ++j;    //To trace string in right direction
            }
        }
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "aaa";

        Solution s = new Solution1();

        println(s.countSubstrings(s1));//3
        println(s.countSubstrings(s2));//6
    }
}
