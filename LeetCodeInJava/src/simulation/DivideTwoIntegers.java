package simulation;

import base.Base;

public class DivideTwoIntegers extends Base {

    public static int divide(int dividend, int divisor) {
        byte sign = 1;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }

        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        if (ldividend == 0 || ldividend < ldivisor) {
            return 0;
        }

        long result = ldivide(ldividend, ldivisor);

        int rresult;
        if (result > Integer.MAX_VALUE) {
            rresult = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            rresult = (int) (sign * result);
        }

        return rresult;
    }

    private static long ldivide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) {
            return 0;
        }

        long sum = ldivisor;
        long multiply = 1;

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        while ((sum + sum) < ldividend) {
            sum += sum;
            multiply += multiply;
        }

        return multiply + ldivide(ldividend - sum, ldivisor);
    }

    public static int divide2(int dividend, int divisor) {
        boolean sig = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        int sign = sig ? -1 : 1;

        long result = Math.abs((long) dividend) / Math.abs((long) divisor);
        if (result > Integer.MAX_VALUE) {
            return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            return (int) (result * sign);
        }
    }

    public static void main(String[] args) {
        int dividend1 = 10, divisor1 = 3;
        int dividend2 = 7, divisor2 = -3;

        println(divide2(-2147483648, -1));
    }

}
