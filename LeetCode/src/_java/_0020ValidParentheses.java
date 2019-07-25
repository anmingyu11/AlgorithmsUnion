package _java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import base.Base;

/**
 * Given a string containing just the characters
 * '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 */
public class _0020ValidParentheses extends Base {

    private abstract static class Solution {
        public abstract boolean isValid(String s);
    }

    /**
     * Runtime: 1 ms, faster than 98.31% of Java online submissions for Valid Parentheses.
     * Memory Usage: 34.4 MB, less than 99.97% of Java online submissions for Valid Parentheses.
     */
    private static class Solution1 extends Solution {
        private Map<Character, Character> map;

        public boolean isValid(String s) {
            map = new HashMap<>();
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');
            char[] brackets = s.toCharArray();
            LinkedList<Character> stack = new LinkedList<>();
            for (char b : brackets) {
                Character b2 = map.get(b);
                if (b2 == null) {
                    // 新的括号开始.
                    stack.addLast(b);
                } else {
                    // 取最后一个元素看是否匹配最后一个括号.
                    Character last = stack.isEmpty() ? null : stack.removeLast();
                    if (last == null || !last.equals(b2)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

    }

    /**
     * Runtime: 1 ms, faster than 98.38% of Java online submissions for Valid Parentheses.
     * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Valid Parentheses.
     * 精妙.
     */
    private static class Solution2 extends Solution {

        @Override
        public boolean isValid(String s) {
            LinkedList<Character> stack = new LinkedList<>();
            for (char b : s.toCharArray()) {
                switch (b) {
                    case '(': {
                        stack.addLast(')');
                        break;
                    }
                    case '{': {
                        stack.addLast('}');
                        break;
                    }
                    case '[': {
                        stack.addLast(']');
                        break;
                    }
                    default: {
                        if (stack.isEmpty() || stack.removeLast() != b) {
                            return false;
                        }
                    }
                }
            }
            return stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        String b1 = "()";
        String b2 = "()[]{}";
        String b3 = "(]";
        String b4 = "([)]";
        String b5 = "{[]}";
        String b6 = "";
        String b7 = "[";
        String b8 = "]";

        Solution s = new Solution2();

        println(s.isValid(b1));//t
        println(s.isValid(b2));//t
        println(s.isValid(b3));//f
        println(s.isValid(b4));//f
        println(s.isValid(b5));//t
        println(s.isValid(b6));//t
        println(s.isValid(b7));//f
        println(s.isValid(b8));//f
    }
}
