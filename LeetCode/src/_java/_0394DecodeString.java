package _java;

import java.util.LinkedList;

import base.Base;

/**
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string],
 * where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid;
 * No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore,
 * you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 * <p>
 * Examples:
 * <p>
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class _0394DecodeString extends Base {

    private abstract static class Solution {
        public abstract String decodeString(String s);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Decode String.
     * Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Decode String.
     * <p>
     * 好好思考.
     */
    private static class Solution1 extends Solution {
        private int idx;
        private char[] strs;

        public String decodeString(String s) {
            idx = 0;
            strs = s.toCharArray();
            return decode();
        }

        public String decode() {
            StringBuilder sb = new StringBuilder();
            int num = 0;
            for (int i = idx; i < strs.length; ++i) {
                char c = strs[i];
                if (c == '[') {
                    idx = i + 1;
                    String next = decode();
                    for (int j = 0; j < num; ++j) {
                        sb.append(next);
                    }
                    num = 0;
                    i = idx;
                } else if (c == ']') {
                    idx = i;
                    return sb.toString();
                } else if (Character.isDigit(c)) {
                    num = 10 * num + c - '0';
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

    }

    /**
     * Use Stack
     */
    private static class Solution2 extends Solution {

        public String decodeString(String s) {
            StringBuilder sb = new StringBuilder();
            char[] strs = s.toCharArray();
            final int n = strs.length;
            int idx = 0;
            LinkedList<Integer> timesStack = new LinkedList<>();
            LinkedList<StringBuilder> resStack = new LinkedList<>();
            while (idx < n) {
                char c = strs[idx];
                if (c == '[') {
                    resStack.addLast(sb);
                    sb = new StringBuilder();
                    ++idx;
                } else if (c == ']') {
                    StringBuilder last = resStack.removeLast();
                    int times = timesStack.removeLast();
                    for (int i = 0; i < times; ++i) {
                        last.append(sb.toString());
                    }
                    sb = last;
                    ++idx;
                } else if (Character.isDigit(c)) {
                    int times = 0;
                    while (Character.isDigit(s.charAt(idx))) {
                        times = 10 * times + s.charAt(idx) - '0';
                        ++idx;
                    }
                    timesStack.addLast(times);
                } else {
                    sb.append(c);
                    ++idx;
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        String s4 = "";
        String s5 = "100[leetcode]";
        String s6 = "3[a]2[b4[F]c]";
        //String s5 = "[]";
        Solution s = new Solution2();

        println(s.decodeString(s1));// aaabcbc
        println(s.decodeString(s2));// accaccacc
        println(s.decodeString(s3));// abcabccdcdcdef
        println(s.decodeString(s4));//
        println(s.decodeString(s5));//
        println(s.decodeString(s6));//

    }

}