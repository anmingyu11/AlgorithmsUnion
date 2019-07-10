package _java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import base.Base;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Example 1:
 * <p>
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 * <p>
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 * <p>
 * Input: ")("
 * Output: [""]
 */
public class _0301RemoveInvalidParentheses extends Base {

    private abstract static class Solution {
        public abstract List<String> removeInvalidParentheses(String s);
    }

    private static class Solution1 extends Solution {

        private Set<String> validExpressions = new HashSet<>();
        private int minimumRemoved;

        private void recurse(
                String s,
                int index,
                int leftCount,
                int rightCount,
                StringBuilder expression,
                int removedCount) {
            // If we have reached the end of string.
            if (index == s.length() && leftCount == rightCount) {
                if (removedCount <= minimumRemoved) {
                    // Convert StringBuilder to a String. This is an expensive operation.
                    // So we only perform this when needed.
                    String possibleAnswer = expression.toString();
                    // If the current count beats the overall minimum we have till now
                    if (removedCount < minimumRemoved) {
                        validExpressions.clear();
                        minimumRemoved = removedCount;
                    }
                    validExpressions.add(possibleAnswer);
                }
            } else {
                char currentCharacter = s.charAt(index);
                int length = expression.length();
                // If the current character is neither an opening bracket nor a closing one,
                // simply recurse further by adding it to the expression StringBuilder
                if (currentCharacter != '(' && currentCharacter != ')') {
                    expression.append(currentCharacter);
                    recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                    expression.deleteCharAt(length);
                } else {
                    // Recursion where we delete the current character and move forward
                    recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                    expression.append(currentCharacter);
                    // If it's an opening parenthesis, consider it and recurse
                    if (currentCharacter == '(') {
                        recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                    } else if (rightCount < leftCount) {
                        // For a closing parenthesis, only recurse if right < left
                        recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                    }
                    // Undoing the append operation for other recursions.
                    expression.deleteCharAt(length);
                }
            }
        }

        public List<String> removeInvalidParentheses(String s) {
            validExpressions = new HashSet<>();
            minimumRemoved = Integer.MAX_VALUE;
            recurse(s, 0, 0, 0, new StringBuilder(), 0);
            return new LinkedList<>(validExpressions);
        }

    }

    public static void main(String[] args) {
        String b1 = "()())()";
        String b2 = "(a)())()";
        String b3 = ")(";

        Solution s = new Solution1();

        println(s.removeInvalidParentheses(b1)); // "()()()", "(())()"
        println(s.removeInvalidParentheses(b2)); // "(a)()()", "(a())()"
        println(s.removeInvalidParentheses(b3)); // ""
    }
}
