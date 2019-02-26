package _java;

import java.util.Arrays;
import java.util.HashMap;

import base.Base;

public class _0961NRepeatedElementinSize2NArray extends Base {
    private abstract static class Solution {
        public abstract int repeatedNTimes(int[] A);
    }

    private static class Solution1 extends Solution {

        private class CounterMap extends HashMap<Integer, Integer> {
            int put(Integer num) {
                Integer time = get(num);
                if (time == null) {
                    time = 1;
                    put(num, time);
                } else {
                    put(num, ++time);
                }
                return time;
            }
        }

        public int repeatedNTimes(int[] A) {
            CounterMap counterMap = new CounterMap();

            final int n = A.length;
            for (int e : A) {
                if (counterMap.put(e) >= n / 2) {
                    return e;
                }
            }
            return -1;
        }

    }

    private static class Solution2 extends Solution {

        public int repeatedNTimes(int[] A) {
            final int n = A.length;
            Arrays.sort(A);
            if (A[0] == A[n / 2 - 1]) {
                return A[0];
            } else if (A[n / 2] == A[n - 1]) {
                return A[n - 1];
            } else {
                return A[n / 2];
            }
        }

    }

    // 66%
    private static class Solution3 extends Solution {

        @Override
        public int repeatedNTimes(int[] A) {
            int[] bucket = new int[10000];

            int max = 0;
            int cur = 0;
            for (int e : A) {
                ++bucket[e];
                if (bucket[e] > max) {
                    max = bucket[e];
                    cur = e;
                }
            }
            return cur;
        }
    }

    //如果在2N的列表中重复N次，则重复的数字总是可以保持在2的距离内。
    /**
     * [a,b,x,x]
     * [x,a,b,x] (distance between both the x is still 1, consider it as a circular list)
     * [x,a,x,b]
     * [x,x,a,b]
     * [a,x,b,x]
     * [a,x,x,b]
     */
    private static class Solution4 extends Solution {

        @Override
        public int repeatedNTimes(int[] A) {
            final int n = A.length;
            for (int i = 0; i < n - 2; ++i) {
                if (A[i] == A[i + 1] || A[i] == A[i + 2]) {
                    return A[i];
                }
            }
            return A[n - 1];
        }

    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 3};
        int[] a2 = {2, 1, 2, 5, 3, 2};
        int[] a3 = {5, 1, 5, 2, 5, 3, 5, 4};
        int[] a4 = {9, 5, 3, 3};

        Solution s = new Solution4();
        println(s.repeatedNTimes(a1)); // 3
        println(s.repeatedNTimes(a2)); // 2
        println(s.repeatedNTimes(a3)); // 5
        println(s.repeatedNTimes(a4)); // 3
    }
}
