package _java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import base.Base;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring,
 * "pwke" is a subsequence and not a substring.
 */
public class _0003LongestSubstringWithoutRepeatingCharacters____MultiS extends Base {

    private abstract static class Solution {
        public abstract int lengthOfLongestSubstring(String s);
    }

    /**
     * Runtime: 8 ms, faster than 82.57% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 36 MB, less than 99.82% of Java online submissions for Longest Substring Without Repeating Characters.
     */
    private static class Solution1 extends Solution {

        @Override
        public int lengthOfLongestSubstring(String s) {
            final int n = s.length();
            if (n < 2) {
                return n;
            }
            Set<Character> set = new HashSet<>();
            LinkedList<Character> list = new LinkedList<>();
            int longest = 0;
            for (int i = 0; i < n; ++i) {
                char ch = s.charAt(i);
                list.addLast(ch);
                if (!set.add(ch)) {
                    for (char f = list.removeFirst(); f != ch; f = list.removeFirst()) {
                        set.remove(f);
                    }
                    /*
                    while (true) {
                        char f = list.removeFirst();
                        if (f == ch) {
                            break;
                        } else {
                            set.remove(f);
                        }
                    }
                    */
                }
                longest = Math.max(list.size(), longest);
            }
            return longest;
        }

    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "aab";
        String s5 = "dvdf";
        String s6 = "aabaab!bb";

        Solution s = new Solution1();

        println(s.lengthOfLongestSubstring(s1));//3
        println(s.lengthOfLongestSubstring(s2));//1
        println(s.lengthOfLongestSubstring(s3));//3
        println(s.lengthOfLongestSubstring(s4));//2
        println(s.lengthOfLongestSubstring(s5));//3
        println(s.lengthOfLongestSubstring(s6));//3

    }
}
