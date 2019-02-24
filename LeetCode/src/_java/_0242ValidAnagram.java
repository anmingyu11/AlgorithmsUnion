package _java;

import java.util.Arrays;

import base.Base;

public class _0242ValidAnagram extends Base {

    private abstract static class Solution {
        public abstract boolean isAnagram(String s, String t);
    }

    // 排序
    // Runtime: 5 ms, faster than 57.14% of Java online submissions for Valid Anagram.
    // Memory Usage: 35.6 MB, less than 90.88% of Java online submissions for Valid Anagram.
    private static class Solution1 extends Solution {

        @Override
        public boolean isAnagram(String s, String t) {
            final int m = s.length(), n = t.length();
            if (m != n) {
                return false;
            }
            char[] a = s.toCharArray();
            char[] b = t.toCharArray();
            Arrays.sort(a);
            Arrays.sort(b);
            for (int i = 0; i < n; ++i) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    // 位图
    // 一开始想的是位图,但是发现不行啊,这个有一个重大的问题,就是每个字符出现的次数不止一次,好像只能用HashMap了
    // Runtime: 5 ms, faster than 57.14% of Java online submissions for Valid Anagram.
    // Memory Usage: 34.7 MB, less than 99.70% of Java online submissions for Valid Anagram.
    private static class Solution2 extends Solution {

        @Override
        public boolean isAnagram(String s, String t) {
            final int m = s.length(), n = t.length();
            if (m != n) {
                return false;
            }

            int[] charSet1 = new int[26];
            int[] charSet2 = new int[26];

            for (int i = 0; i < n; ++i) {
                ++charSet1[s.charAt(i) - 'a'];
                ++charSet2[t.charAt(i) - 'a'];
            }

            for (int i = 0; i < 26; ++i) {
                if (charSet1[i] != charSet2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    // 这个写法很精妙.
    // 值得学习.
    private static class Solution3 extends Solution {

        @Override
        public boolean isAnagram(String s, String t) {
            int[] alphabet = new int[26];
            for (int i = 0; i < s.length(); i++) {
                alphabet[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i < t.length(); i++) {
                alphabet[t.charAt(i) - 'a']--;
            }
            for (int i : alphabet) {
                if (i != 0) {
                    return false;
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        String s1 = "anagram", t1 = "nagaram";
        String s2 = "rat", t2 = "car";
        String s3 = "aa", t3 = "bb";

        Solution s = new Solution2();

        println(s.isAnagram(s1, t1)); // t
        println(s.isAnagram(s2, t2)); // f
        println(s.isAnagram(s3, t3)); // f
    }
}
