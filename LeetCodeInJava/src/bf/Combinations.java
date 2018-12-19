package bf;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class Combinations extends Base {

    static class Solution1 {

        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> list = new LinkedList<>();
            backtrack(list, new LinkedList<>(), n, k, 0, 1);
            return list;
        }

        private void backtrack(List<List<Integer>> list, LinkedList<Integer> res, int n, int k, int pos, int cur) {
            if (pos == k) {
                list.add((List<Integer>) res.clone());
                return;
            }

            for (int i = cur; i <= n; ++i) {
                res.add(i);
                backtrack(list, res, n, k, pos + 1, i + 1);
                res.removeLast();
            }
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> list = new Solution1().combine(4, 2);
        println(list);
    }

}
