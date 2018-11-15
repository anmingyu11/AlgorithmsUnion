package greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import base.Base;

public class LongestSubstringWithoutRepeatingCharacters extends Base {
    static class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int len = s.length();
            int res = 0, l = 0, r = 0;
            HashSet<Character> set = new HashSet<>();
            while (r < len && l < len) {
                if (!set.contains(s.charAt(r))) {
                    set.add(s.charAt(r++));
                    res = Math.max(res, r - l);
                } else {
                    set.remove(s.charAt(l++));
                }
            }

            return res;
        }
    }

    static class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int len = s.length(), res = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int l = 0, r = 0; r < len && l < len; ) {
                char containsR = s.charAt(r);
                if (map.containsKey(containsR)) {
                    l = Math.max(map.get(containsR), l);
                }

                res = Math.max(res, r - l + 1);
                map.put(containsR, ++r);
            }

            return res;
        }
    }

    static class Solution3 {

        public int lengthOfLongestSubstring(String s) {
            int len = s.length(), res = 0;
            int[] charset = new int[128];
            for (int l = 0, r = 0; r < len && l < len; ) {
                char containsR = s.charAt(r);
                l = Math.max(charset[containsR], l);
                res = Math.max(r - l + 1, res);
                charset[containsR] = ++r;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        String str2 = "abcbcabd";
        Solution1 s1 = new Solution1();
        println(str1 + " : " + s1.lengthOfLongestSubstring(str1));
        println(str2 + " : " + s1.lengthOfLongestSubstring(str2));
        Solution2 s2 = new Solution2();
        println(str2 + " : " + s2.lengthOfLongestSubstring(str2));
    }
}
