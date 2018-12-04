package dfs;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class CombinationSum3 extends Base {

    static class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> res = new LinkedList<>();
            if (n > 9 * k || k > n) {
                return res;
            }
            backTrack(res, new LinkedList<>(), 1, k, n);
            return res;
        }

        private void backTrack(List<List<Integer>> res, LinkedList<Integer> curList, int pos, int count, int target) {
            if (curList.size() > count) {
                return;
            }
            if (target < 0) {
                return;
            } else if (target == 0) {
                if (curList.size() == count) {
                    res.add((List<Integer>) curList.clone());
                }
                return;
            }

            for (int i = pos; i <= 9; ++i) {
                curList.add(i);
                backTrack(res, curList, i + 1, count, target - i);
                curList.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int k1 = 3, n1 = 7;
        int k2 = 3, n2 = 9;
        println(new Solution().combinationSum3(k1, n1));
        println(new Solution().combinationSum3(k2, n2));
    }
}
