package _java;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _1002FindCommonCharacters__________ extends Base {

    private abstract static class Solution {
        public abstract List<String> commonChars(String[] A);
    }

    // 你管咋的,能过就行呗
    private static class Solution1 extends Solution {

        @Override
        public List<String> commonChars(String[] A) {
            final int n = A.length;
            int[] set = new int[26];
            int[] set2;
            // 基准
            for (int i = 0; i < A[0].length(); ++i) {
                int c = A[0].charAt(i) - 'a';
                ++set[c];
            }
            // 比较
            for (int i = 1; i < n; ++i) {
                String s = A[i];
                set2 = new int[26];
                for (int j = 0; j < s.length(); ++j) {
                    int c = s.charAt(j) - 'a';
                    ++set2[c];
                }
                for (int j = 0; j < 26; ++j) {
                    set[j] = Math.min(set2[j], set[j]);
                }
            }

            List<String> l = new LinkedList<>();
            for (int i = 0; i < 26; ++i) {
                while (set[i] != 0) {
                    l.add(Character.toString((char) ('a' + i)));
                    --set[i];
                }
            }
            return l;
        }
    }

    public static void main(String[] args) {

        String[] s1 = {"bella", "label", "roller"};
        String[] s2 = {"cool", "lock", "cook"};

        Solution s = new Solution1();

        println(s.commonChars(s1));
        println(s.commonChars(s2));
    }
}
