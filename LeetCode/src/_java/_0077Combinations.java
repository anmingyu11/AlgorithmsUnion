package _java;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _0077Combinations extends Base {

    private abstract static class Solution {
        public abstract List<List<Integer>> combine(int n, int k);
    }

    // Runtime: 13 ms, faster than 74.10% of Java online submissions for Combinations.
    // Memory Usage: 44.2 MB, less than 7.75% of Java online submissions for Combinations.
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> ans = new LinkedList<>();
            backTrack(ans, new LinkedList<>(), n, k, 1);
            return ans;
        }

        private void backTrack(List<List<Integer>> ans, LinkedList<Integer> cur, int n, int k, int pos) {
            if (cur.size() == k) {
                ans.add((List<Integer>) cur.clone());
                return;
            }
            for (int i = pos; i <= n; ++i) {
                cur.add(i);
                backTrack(ans, cur, n, k, i + 1);
                cur.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int n1 = 4, k1 = 2;

        Solution s = new Solution1();

        println(s.combine(n1, k1));
    }
}
