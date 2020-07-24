package _java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import base.Base;

/**
 * Write an algorithm to determine if a number n is "happy".
 * <p>
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Return True if n is a happy number, and False if not.
 * <p>
 * Example:
 * <p>
 * Input: 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class _0202HappyNumber extends Base {

    private static abstract class Solution {
        public abstract boolean isHappy(int n);
    }

    /**
     * Runtime: 1 ms, faster than 91.20% of Java online submissions for Happy Number.
     * Memory Usage: 36.6 MB, less than 42.03% of Java online submissions for Happy Number.
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean isHappy(int n) {
            Map<Integer, Integer> map = new HashMap<>();
            int cycle = 0;
            while (true) {
                int nextN = 0;
                for (int N = n; N != 0; N /= 10) {
                    int r = N % 10;
                    nextN += r * r;
                }
                if (nextN == 1) {
                    return true;
                }
                map.put(n, nextN);
                if (map.containsKey(nextN)) {
                    cycle = nextN;
                    break;
                }
                n = nextN;
            }
            return false;

        }

    }

    /**
     * Elegent HashSet.
     */
    private static class Solution2 extends Solution {
        private int getNext(int n) {
            int next = 0;
            for (int v = n; v != 0; v /= 10) {
                int r = v % 10;
                next += r * r;
            }
            return next;
        }

        public boolean isHappy(int n) {
            HashSet<Integer> set = new HashSet<>();
            int v = n;
            for (; v != 1 && set.add(v); v = getNext(v)) ;
            return v == 1;
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Happy Number.
     * Memory Usage: 36.3 MB, less than 76.88% of Java online submissions for Happy Number.
     */
    private static class Solution3 extends Solution {

        private int next(int v) {
            int next = 0;
            for (; v != 0; v /= 10) {
                int r = v % 10;
                next += r * r;
            }
            return next;
        }

        public boolean isHappy(int n) {
            int slow = n, fast = n;
            do {
                slow = next(slow);
                fast = next(next(fast));
            } while (slow != 1 && slow != fast);
            return slow == 1;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution3();

        boolean h1 = s.isHappy(19);
        boolean h2 = s.isHappy(3);
        boolean h3 = s.isHappy(1111111);
        println(h1);
        println(h2);
        println(h3);
    }
}
