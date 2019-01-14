package bit;

import base.Base;

public class PowerOfTwo extends Base {

    static class Solution {

        public boolean isPowerOfTwo(int n) {
            if (n <= 0) {
                return false;
            }
            return (n & (n - 1)) == 0;
        }

    }

    public static void main(String[] args) {
        int n1 = 1, n2 = 16, n3 = 218;
        println(new Solution().isPowerOfTwo(n1));
        println(new Solution().isPowerOfTwo(n2));
        println(new Solution().isPowerOfTwo(n3));
    }
}
