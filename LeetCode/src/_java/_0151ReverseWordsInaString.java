package _java;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import base.Base;

/**
 * Given an input string, reverse the string word by word.
 * <p>
 * Example 1:
 * <p>
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * <p>
 * Example 2:
 * <p>
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * <p>
 * Example 3:
 * <p>
 * Input: "a good   example"
 * Output: "example good a"
 * <p>
 * Explanation:
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * Note:
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces.
 * However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * Follow up:
 * For C programmers, try to solve it in-place in O(1) extra space.
 */
public class _0151ReverseWordsInaString extends Base {

    private static abstract class Solution {
        public abstract String reverseWords(String s);
    }

    private static class Solution1 extends Solution {

        @Override
        public String reverseWords(String s) {
            s = s.trim();
            String[] splits = s.split("\\s+");
            List<String> res = Arrays.asList(splits);
            Collections.reverse(res);
            return String.join(" ", res);
        }

    }

    /**
     * Solution3 和 4 的结合版
     * 从内置函数的实现原理上来看效率应该不是最好的。
     */
    private static class Solution2 extends Solution {

        @Override
        public String reverseWords(String s) {
            StringBuilder res = new StringBuilder(s.length());
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == ' ') {
                    continue;
                }
                int j;
                for (j = i; j < s.length() && s.charAt(j) != ' '; ++j) ;
                res.insert(0, s.substring(i, j));
                res.insert(0, ' ');
                i = j;
            }
            if (res.length() > 0) {
                res.deleteCharAt(0);
            }
            return res.toString();
        }

    }

    private static class Solution3 extends Solution {

        @Override
        public String reverseWords(String s) {
            StringBuilder sb = new StringBuilder();
            int lo, hi;
            for (lo = 0, hi = s.length() - 1; lo <= hi; ++lo) {
                char ch = s.charAt(lo);
                if (ch != ' ') {
                    break;
                }
            }
            for (int i = lo; i <= hi; ++i) {
                char ch = s.charAt(i);
                if (ch == ' ' && (i + 1 < s.length() && s.charAt(i + 1) == ' ' || i + 1 == s.length())) {
                    continue;
                } else {
                    sb.append(ch);
                }
            }
            StringBuilder res = new StringBuilder();
            for (int i = lo; i <= hi; ++i) {
                char ch = s.charAt(i);
                if (ch == ' ') {
                    continue;
                }
                int j;
                for (j = i; j <= hi && s.charAt(j) != ' '; ++j) ;
                if (res.length() > 0) {
                    res.append(' ');
                }
                res.append(reverse(s.substring(i, j)));
                i = j;
            }
            return reverse(res.toString());
        }

        private String reverse(String s) {
            int n = s.length(), lo = 0, hi = n - 1;
            char[] chs = new char[n];
            while (lo <= hi) {
                chs[lo] = s.charAt(hi);
                chs[hi] = s.charAt(lo);
                ++lo;
                --hi;
            }
            return new String(chs);
        }
    }

    /**
     * Deque
     */
    private static class Solution4 extends Solution {

        @Override
        public String reverseWords(String s) {
            LinkedList<String> dq = new LinkedList<>();

            int left = 0, right = s.length() - 1;
            for (; left <= right && s.charAt(left) == ' '; ++left) ;
            for (; left <= right && s.charAt(right) == ' '; --right) ;

            for (; left <= right; ++left) {
                char ch = s.charAt(left);
                if (ch == ' ') {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                for (; left <= right && (ch = s.charAt(left)) != ' '; ++left) sb.append(ch);
                dq.addFirst(sb.toString());
            }

            return String.join(" ", dq);
        }

    }

    /**
     * Most Fast one.
     */
    private static class Solution5 extends Solution {

        @Override
        public String reverseWords(String s) {
            StringBuilder res = new StringBuilder();

            for (int i = s.length() - 1; i >= 0; --i) {
                if (s.charAt(i) == ' ') {
                    continue;
                }
                int j;
                for (j = i; j >= 0 && s.charAt(j) != ' '; --j) ;
                if (res.length() > 0) {
                    res.append(' ');
                }
                res.append(s.substring(j + 1, i + 1));
                i = j;
            }
            return res.toString();
        }

    }

    public static void main(String[] args) {
        String str1 = "the sky is blue";
        String str2 = "  hello world!  ";
        String str3 = "a good   example";
        Solution s = new Solution5();

        println(s.reverseWords(str1));
        println(s.reverseWords(str2));
        println(s.reverseWords(str3));
    }
}
