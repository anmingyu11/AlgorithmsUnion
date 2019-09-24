package _java;

import base.Base;

public class _470ImplementRand10usingRand7 extends Base {

    private abstract static class Solution {

        public int rand7() {
            return (int) (Math.random() * 7) + 1;
        }

        public abstract int rand10();

    }

    private static class Solution1 extends Solution {

        public int rand10() {
            int id = 40;
            while (id >= 40) {
                int r = rand7();
                int c = rand7();
                // 7 * (r - 1) = [0,42]
                // c = [1 , 7]
                // id = [0,49] that is 50 numbers
                // id -1 = [0,48] that is 49 numbers uniform
                id = 7 * (r - 1) + c - 1;
            }
            return 1 + id % 10;
        }

    }

    private static class Solution2 extends Solution {

        public int rand10() {
            return 0;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        int[] res = new int[50];
        for (int i = 0; i < 100000; ++i) {
            ++res[s.rand10()];
        }
        printArr(res);
    }
}