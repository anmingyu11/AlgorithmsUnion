package math;

import base.Base;

public class PowXN extends Base {

    static class Solution1 {

        public static double myPow(double x, int n) {
            return n < 0 ? 1 / power(x, -n) : power(x, n);
        }

        public static double power(double x, int n) {
            println("n : " + n);
            if (n == 0) {
                return 1;
            }

            double half = power(x, n / 2);

            if (n % 2 == 0) {
                return half * half;
            } else {
                return x * half * half;
            }
        }
    }

    static class Solution2 {

        public static double myPow(double x, int n) {
            if (n == 0) {
                return 1;
            }
            double half = myPow(x, n / 2);
            if (n % 2 == 0) {
                return half *= half;
            } else if (n > 0) {
                return half *= half * x;
            } else {
                return half * half / x;
            }
        }

    }

    static class Solution3 {

        public static double myPow(double x, int n) {
            double res = 1.0f;
            for (int i = n; i != 0; i /= 2) {
                if (i % 2 != 0) {
                    res *= x;
                }
                x *= x;
            }

            return n < 0 ? 1 / res : res;
        }

    }

    public static void main(String[] args) {
        println(Solution1.myPow(2.0f, 10));
        //println(Solution2.myPow(2.0f, 20));
        //println(Solution3.myPow(2.0f, 20));
    }

}
