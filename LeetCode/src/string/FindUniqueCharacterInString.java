package string;

import base.Base;

public class FindUniqueCharacterInString extends Base {

    abstract static class Solution {
        abstract public int firstUniqChar(String s);
    }

    static class Solution1 extends Solution {

        @Override
        public int firstUniqChar(String s) {
            int[] chSet = new int[26];
            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                ++chSet[ch - 'a'];
            }

            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                if (chSet[ch - 'a'] == 1) {
                    return i;
                }
            }

            return -1;
        }

    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        String s2 = "loveleetcode";
        String s3 = "";

        println(new Solution1().firstUniqChar(s1));
        println(new Solution1().firstUniqChar(s2));
        println(new Solution1().firstUniqChar(s3));
    }

}
