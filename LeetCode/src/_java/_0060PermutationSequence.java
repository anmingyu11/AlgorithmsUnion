package _java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _0060PermutationSequence extends Base {

    private static abstract class Solution {
        public abstract String getPermutation(int n, int k);

        // little trick
        // 14! 整型溢出
        // 21! 长整型溢出
        List<Long> factorial() {

            final int k = 25;
            List<Long> fac = new ArrayList<>();
            fac.add(1L);
            int i = 2;

            while (i <= k) {
                fac.add(fac.get(i - 2) * i++);
            }
            return fac;
        }

    }

    // 回溯 1285ms 竟然能过 beats 15.8%
    private static class Solution1 extends Solution {

        @Override
        public String getPermutation(int n, int k) {
            int[] nums = new int[n];
            for (int i = 1; i <= n; ++i) {
                nums[i - 1] = i;
            }
            List<List<Integer>> list = permute(nums, k);

            List<Integer> r = list.get(k - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r.size(); ++i) {
                sb.append(r.get(i));
            }
            return sb.toString();
        }

        List<List<Integer>> permute(int[] nums, int k) {
            List<List<Integer>> list = new LinkedList<>();
            int[] invalid = new int[nums.length];
            backtrack(list, new LinkedList<>(), nums, invalid, 0, k);
            return list;
        }

        private void backtrack(List<List<Integer>> list, LinkedList<Integer> permute, int[] nums, int[] invalid, int pos, int k) {
            if (list.size() == k) {
                return;
            }
            if (pos == nums.length) {
                list.add((List<Integer>) permute.clone());
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (invalid[i] == 0) {
                    invalid[i] = -1;
                    permute.add(nums[i]);
                    backtrack(list, permute, nums, invalid, pos + 1, k);
                    permute.removeLast();
                    invalid[i] = 0;
                }
            }
        }
    }

    // 康托展开 7ms beats 97%
    private static class Solution2 extends Solution {

        int[] fact = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 1932053504};

        public String getPermutation(int n, int k) {
            //StringBuilder是一样的.
            char[] rs = new char[n];
            LinkedList<Integer> invalid = new LinkedList<>();

            for (int i = 1; i <= n; ++i) {
                invalid.add(i);
            }

            // 现在有k-1个排列要比 第k个排列小,实际上计算的是比k小的排列.
            // 比如 1234是第一大的数, 1243是第二大的数
            --k;
            int j = n - 1, i = 0;
            while (j >= 0) {
                int bit = k / fact[j];
                k %= fact[j--];
                rs[i++] = (char) ('0' + invalid.remove(bit));
            }

            return String.valueOf(rs);
        }

    }

    // 7ms 97% 一样.
    private static class Solution3 extends Solution {

        int[] fact = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 1932053504};

        public String getPermutation(int n, int k) {
            char[] rs = new char[n];
            int[] invalid = new int[n];

            --k;
            int j = n - 1, i = 0;
            while (j >= 0) {
                int bit = k / fact[j];
                k %= fact[j--];
                int index = 0;
                while (bit >= 0) {
                    if (invalid[index] == 0) {
                        --bit;
                    }
                    ++index;
                }
                invalid[index - 1] = -1;
                rs[i++] = (char) ('0' + index);
            }

            return String.valueOf(rs);
        }
    }

    public static void main(String[] args) {

        Solution s = new Solution3();

        println(s.getPermutation(5, 16)); // 14352
        println(s.getPermutation(3, 3));  // 213
        println(s.getPermutation(3, 2));  // 132
        println(s.getPermutation(4, 9));  // 2314
    }

}
