package _java;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _0017LetterCombinationsOfAPhoneNumber extends Base {
    private abstract static class Solution {
        public abstract List<String> letterCombinations(String s);
    }

    // Runtime: 2 ms, faster than 79.63% of Java online submissions for Letter Combinations of a Phone Number.
    // Memory Usage: 37.2 MB, less than 78.29% of Java online submissions for Letter Combinations of a Phone Number.
    private static class Solution1 extends Solution {

        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            List<String> ans = new LinkedList<>();
            if (digits.length() == 0){
                return ans;
            }
            backtrack(ans, digits, new StringBuilder(), 0);
            return ans;
        }

        private void backtrack(List<String> ans, String digits, StringBuilder cur, int pos) {
            if (cur.length() == digits.length()) {
                ans.add(cur.toString());
                return;
            }

            for (int i = pos; i < digits.length(); ++i) {
                int num = digits.charAt(i) - '0';
                String set = mapping[num];
                for (int j = 0; j < set.length(); ++j) {
                    cur.append(set.charAt(j));
                    backtrack(ans, digits, cur, i + 1);
                    cur.deleteCharAt(cur.length() - 1);
                }
            }

        }

    }

    public static void main(String[] args) {
        String s1 = "23";
        String s2 = "";

        Solution s = new Solution1();

        println(s.letterCombinations(s1));
        println(s.letterCombinations(s2));
    }
}
