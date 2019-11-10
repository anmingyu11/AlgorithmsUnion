package _java;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _1253Reconstructa2RowBinaryMatrix extends Base {
    private static abstract class Solution {
        public abstract List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum);
    }

    private static class Solution1 extends Solution {

        public List<List<Integer>> reconstructMatrix(
                int upper, int lower, int[] colsum) {
            final int n = colsum.length;
            List<List<Integer>> res = new LinkedList<>();
            List<Integer> l1 = new LinkedList<>();
            List<Integer> l2 = new LinkedList<>();
            int sum = 0;
            final int oSum = upper + lower;
            for (int i = 0; i < n; ++i) {
                sum += colsum[i];
                if (sum > oSum) {
                    return res;
                }
                if (colsum[i] == 2) {
                    --upper;
                    --lower;
                }
            }
            for (int i = 0; i < n; ++i) {
                if (colsum[i] == 2) {
                    l1.add(1);
                    l2.add(1);
                } else if (colsum[i] == 1) {
                    if (upper != 0) {
                        l1.add(1);
                        l2.add(0);
                        --upper;
                    } else if (lower != 0) {
                        l1.add(0);
                        l2.add(1);
                        --lower;
                    }
                } else {
                    l1.add(0);
                    l2.add(0);
                }
            }
            if (upper >0 || lower >0){
                return res;
            }
            res.add(l1);
            res.add(l2);
            return res;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();
        println(s.reconstructMatrix(2, 1, new int[]{1, 1, 1}));
        println(s.reconstructMatrix(2, 3, new int[]{2, 2, 1, 1}));
        println(s.reconstructMatrix(5, 5, new int[]{2, 1, 2, 0, 1, 0, 1, 2, 0, 1}));
        println(s.reconstructMatrix(4, 7, new int[]{2, 1, 2, 2, 1, 1, 1}));
    }
}