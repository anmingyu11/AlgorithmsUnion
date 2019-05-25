package _java;

import base.Base;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class _0344ReverseString extends Base {

    private abstract static class Solution {
        public abstract void reverseString(char[] s);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Reverse String.
     * Memory Usage: 50.9 MB, less than 37.30% of Java online submissions for Reverse String.
     */
    private static class Solution1 extends Solution {

        @Override
        public void reverseString(char[] s) {
            final int n = s.length;
            if (n < 2) {
                return;
            }
            for (int i = 0; i < n / 2; ++i) {
                char tmp = s[i];
                s[i] = s[n - 1 - i];
                s[n - 1 - i] = tmp;
            }
        }

    }

    public static void main(String[] args) {
        char[] str1 = {'h', 'e', 'l', 'l', 'o'};
        char[] str2 = {'H', 'a', 'n', 'n', 'a', 'h'};

        Solution s = new Solution1();

        s.reverseString(str1);
        printArr(str1);
        s.reverseString(str2);
        printArr(str2);
    }
}
