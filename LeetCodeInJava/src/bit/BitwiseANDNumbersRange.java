package bit;

import base.Base;

public class BitwiseANDNumbersRange extends Base {
    abstract static class Solution {
        public abstract int rangeBitwiseAnd(int m, int n);
    }

    static class Solution1 extends Solution {

        @Override
        public int rangeBitwiseAnd(int m, int n) {
            int res = m;

            for (int i = m + 1; i <= n; ++i) {
                res &= i;
            }

            return res;
        }
    }

    static class Solution2 extends Solution {

        @Override
        public int rangeBitwiseAnd(int m, int n) {
            int mask = Integer.MAX_VALUE;

            while ((m & mask) != (n & mask)) {
                mask <<= 1;
            }

            return m & mask;
        }

    }

    static class Solution3 extends Solution {

        @Override
        public int rangeBitwiseAnd(int m, int n) {
            int i = 0;

            for (; m != n; ++i) {
                m >>= 1;
                n >>= 1;
            }

            return n << i;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 7};
        int[] arr2 = {0, 1};
        int[] arr3 = {26, 30};

        println(new Solution2().rangeBitwiseAnd(arr1[0], arr1[1]));
        println(new Solution2().rangeBitwiseAnd(arr2[0], arr2[1]));
        println(new Solution2().rangeBitwiseAnd(arr3[0], arr3[1]));
    }
}
