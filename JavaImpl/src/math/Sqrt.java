package math;

import base.Base;

public class Sqrt extends Base {
    static class Solution1 {
        //对于一个非负数n，它的平方根不会大于（n/2+1）。在[0, n/2+1]这个范围内可以进行二分搜索，求出n的平方根。
        public static int mySqrt(int x) {
            if (x < 1) {
                return 0;
            }
            if (x == 1) {
                return 1;
            }

            long left = 0, right = x / 2 + 1;
            while (left <= right) {
                long mid = (left + right) / 2;
                if (mid * mid > x) {
                    right = mid - 1;
                } else if (mid * mid < x) {
                    left = mid + 1;
                } else {
                    return (int) mid;
                }
            }

            //println("left : " + left + " right : " + right);
            return (int) right;
        }
    }

    static class Solution2 {
        public static int mySqrt(int x) {
            if (x == 0) return 0;
            double res = 1, pre = 0;
            while (Math.abs(res - pre) > 1e-6) {
                pre = res;
                res = (res + x / res) / 2;
            }
            return (int) res;
        }
    }

    public static void main(String[] args) {
        //println("input " + Integer.MAX_VALUE + " " + Solution1.mySqrt(Integer.MAX_VALUE));
        //println("input 6 " + Solution1.mySqrt(6));
        //println("input 8 : 2 " + Solution1.mySqrt(8));
        println("input 8 : 2 " + Solution2.mySqrt(8));
    }
}
