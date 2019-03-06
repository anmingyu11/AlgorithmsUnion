package base.util;

import java.util.LinkedList;
import java.util.List;

import base.Base;
import base.alg4stdlib.StdOut;

public class PermutationUtil {

    // n!
    public static final int[] FACTORIALS =
            new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 1932053504};

    public static List<int[]> permutations(int n) {
        return permutations(n, 1);
    }

    public static List<int[]> permutations(int n, int solution) {
        Solution s;
        List<int[]> res;
        switch (solution) {
            case 1: {
                s = new Solution1();
                break;
            }
            default: {
                throw new IllegalArgumentException("your solution num is wrong");
            }
        }
        res = s.permutations(n);
        return res;
    }

    private static abstract class Solution {
        public abstract List<int[]> permutations(int n);
    }

    // 回溯法
    private static class Solution1 extends Solution {

        @Override
        public List<int[]> permutations(int n) {
            // 结果
            List<int[]> result = new LinkedList<>();
            // 标记用过的
            boolean[] used = new boolean[n + 1];
            // 存放结果的数组
            int[] cur = new int[n];
            // 从1遍历到n
            for (int i = 1; i <= n; ++i) {
                used[i] = true;
                cur[0] = i;
                backtrack(result, cur, used, 1, n);
                used[i] = false;
            }
            return result;
        }

        private void backtrack(List<int[]> result, int[] cur, boolean[] used, int pos, final int n) {
            if (pos == n) {
                result.add(cur.clone());
                return;
            }
            for (int i = 1; i <= n; ++i) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                cur[pos] = i;
                backtrack(result, cur, used, pos + 1, n);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {

        int n = 5;
        int factN = FACTORIALS[n];
        int size = 0;
        // 介个数据结构可能会很大,用定义域圈一下,还是C++好.
        {
            boolean print = false;
            List<int[]> permutations = permutations(n, 1);
            if (print) {
                for (int[] a : permutations) {
                    Base.printArr(a);
                }
            }
            size = permutations.size();
        }
        StdOut.println("n : " + FACTORIALS[n] + " size :" + size + " size == FACTORIAL[n] : " + (size == factN));

    }

}
