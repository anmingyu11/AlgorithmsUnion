package bit;

import base.Base;

public class PowerOfThree extends Base {

    abstract static class Solution {
        public abstract boolean isPowerOfThree(int n);
    }

    static class Solution1 extends Solution {

        @Override
        public boolean isPowerOfThree(int n) {
            // 1 是 3的0次蜜
            if (n < 1) {
                return false;
            }

            while (n % 3 == 0) {
                n /= 3;
            }

            return n == 1;
        }
    }

    // 正则，3进制
    static class Solution2 extends Solution {

        @Override
        public boolean isPowerOfThree(int n) {
            return Integer.toString(n, 3).matches("^10*$");
        }
    }

    //数学公式
    static class Solution3 extends Solution {

        @Override
        public boolean isPowerOfThree(int n) {
            return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        }
    }

    static class Solution4 extends Solution {

        @Override
        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467 % n == 0;
        }
    }

    public static void main(String[] args) {
        int n1 = 27, n2 = 0, n3 = 9, n4 = 45;

        println(new Solution4().isPowerOfThree(n1));
        println(new Solution4().isPowerOfThree(n2));
        println(new Solution4().isPowerOfThree(n3));
        println(new Solution4().isPowerOfThree(n4));
    }
}
