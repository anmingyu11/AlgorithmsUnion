package _java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
public class _0003LongestSubstringWithoutRepeatingCharacters extends Base {

    private abstract static class Solution {
        public abstract int lengthOfLongestSubstring(String s);
    }

    /**
     * Runtime: 7 ms, faster than 82.35% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 36.1 MB, less than 99.76% of Java online submissions for Longest Substring Without Repeating Characters.
     * Sliding Window
     */
    private static class Solution1 extends Solution {

        @Override
        public int lengthOfLongestSubstring(String s) {
            final int n = s.length();
            if (n < 2) {
                return n;
            }
            Set<Character> set = new HashSet<>();
            int max = 0, l = 0, r = 0;
            while (l < n && r < n) {
                char ch = s.charAt(r);
                if (set.add(ch)) {
                    max = Math.max(max, r++ - l + 1);
                } else {
                    set.remove(s.charAt(l++));
                }
            }
            return max;
        }

    }

    /**
     * Runtime: 6 ms, faster than 85.14% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 36.2 MB, less than 99.76% of Java online submissions for Longest Substring Without Repeating Characters.
     */
    private static class Solution2 extends Solution {

        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            if (n < 2) {
                return n;
            }
            int max = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0, j = 0; j < n; ++j) {
                char ch = s.charAt(j);
                if (map.containsKey(ch)) {
                    i = Math.max(i, map.get(ch));
                }
                map.put(ch, j + 1); // 这里为啥+1呢，因为如果是j的画，这里会多计算一个，比如aba,就会计算aba的长度，而不是ba的长度
                max = Math.max(max, j - i + 1);
            }
            return max;
        }

    }

    /**
     * Runtime: 2 ms, faster than 99.84% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 36.9 MB, less than 99.76% of Java online submissions for Longest Substring Without Repeating Characters.
     */
    private static class Solution3 extends Solution {

        public int lengthOfLongestSubstring(String s) {
            final int n = s.length();
            if (n < 2) {
                return n;
            }
            int[] set = new int[256];
            int max = 0;
            for (int i = 0, j = 0; j < n; ++j) {
                char ch = s.charAt(j);
                i = set[ch] != 0 ? Math.max(i, set[ch]) : i;
                set[ch] = j + 1;
                max = Math.max(max, j - i + 1);
            }
            return max;
        }

    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "aab";
        String s5 = "dvdf";
        String s6 = "aabaab!bb";

        Solution s = new Solution3();

        println(s.lengthOfLongestSubstring(s1));//3
        println(s.lengthOfLongestSubstring(s2));//1
        println(s.lengthOfLongestSubstring(s3));//3
        println(s.lengthOfLongestSubstring(s4));//2
        println(s.lengthOfLongestSubstring(s5));//3
        println(s.lengthOfLongestSubstring(s6));//3

    }
}
