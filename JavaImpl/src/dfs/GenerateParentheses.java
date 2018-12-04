package dfs;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class GenerateParentheses extends Base {

    static class Solution {

        public List<String> generateParenthesis(int n) {
            int len = 2 * n;
            List<String> res = new LinkedList<>();
            char[] ps = new char[len];
            backtrack(res, ps, 0, 0);
            return res;
        }

        private void backtrack(List<String> res, char[] ps, int open, int close) {
            final int n = close + open;
            if (n == ps.length) {
                res.add(new String(ps));
            }

            if (open < ps.length / 2) {
                ps[n] = '(';
                backtrack(res, ps, open + 1, close);
            }
            if (close < open) {
                ps[n] = ')';
                backtrack(res, ps, open, close + 1);
            }
        }

    }

    public static void main(String[] args) {
        int n = 3;
        println(new Solution().generateParenthesis(n));
    }

}
