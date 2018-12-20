package bf;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import base.Base;

public class LetterCombinationsOfAPhoneNumber extends Base {

    static class Solution1 {

        Map<Integer, char[]> map = new HashMap<>(10);

        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public Solution1() {
            map.put(2, new char[]{'a', 'b', 'c'});
            map.put(3, new char[]{'d', 'e', 'f'});
            map.put(4, new char[]{'g', 'h', 'i'});
            map.put(5, new char[]{'j', 'k', 'l'});
            map.put(6, new char[]{'m', 'n', 'o'});
            map.put(7, new char[]{'p', 'q', 'r', 's'});
            map.put(8, new char[]{'t', 'u', 'v'});
            map.put(9, new char[]{'w', 'x', 'y', 'z'});
        }

        public List<String> letterCombinations(String s) {
            List<String> res = new LinkedList<>();
            if (s.length() == 0) {
                return res;
            }
            char[] digits = s.toCharArray();
            backtrack(res, new StringBuilder(), digits, 0);
            return res;
        }

        private void backtrack(List<String> res, StringBuilder sb, char[] digits, int pos) {
            if (sb.length() == digits.length) {
                res.add(sb.toString());
                return;
            }

            for (int i = pos; i < digits.length; ++i) {
                // 当前数字
                int curNum = digits[i] - '0';
                // 当前数字对应的字符
                char[] charSet = map.get(curNum);
                for (int j = 0; j < charSet.length; ++j) {
                    char ch = charSet[j];
                    sb.append(ch);
                    backtrack(res, sb, digits, i + 1);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }

    }

    // BFS Iterate
    static class Solution2 {

        public List<String> letterCombinations(String digits) {

            LinkedList<String> res = new LinkedList<>();
            if (digits.length() == 0) {
                return res;
            }
            String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            res.add("");

            while (res.peek().length() != digits.length()) {
                String remove = res.remove();
                char[] charSet = mapping[Character.getNumericValue(digits.charAt(remove.length()))].toCharArray();
                for (char c : charSet) {
                    res.addLast(remove + c);
                }
            }

            return res;

        }

    }

    // Solution3 use StringBuilder
    static class Solution3 {

        public List<String> letterCombinations(String digits) {
            LinkedList<String> res = new LinkedList<>();
            if (digits.length() == 0) {
                return res;
            }

            String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            res.add("");
            while (digits.length() != res.peek().length()) {
                StringBuilder remove = new StringBuilder(res.remove());
                char[] charSet = mapping[digits.charAt(remove.length()) - '0'].toCharArray();
                for (char ch : charSet) {
                    res.addLast(remove.append(ch).toString());
                    remove.deleteCharAt(remove.length() - 1);
                }
            }

            return res;
        }

    }

    static class Solution4 {

        public List<String> letterCombinations(String digits) {
            LinkedList<String> res = new LinkedList<>();
            if (digits.length() == 0) {
                return res;
            }
            res.add("");
            String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

            for (int i = 0; i < digits.length(); ++i) {
                int x = Character.getNumericValue(digits.charAt(i));
                while (res.peek().length() == i) {
                    StringBuilder sb = new StringBuilder(res.remove());
                    char[] charSet = mapping[x].toCharArray();
                    for (char ch : charSet) {
                        res.add(sb.append(ch).toString());
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            }

            return res;
        }

    }

    public static void main(String[] args) {
        String s1 = "23";
        String s2 = "7";
        Solution4 solution = new Solution4();

        println(solution.letterCombinations(s1));
        println(solution.letterCombinations(s2));
    }

}
