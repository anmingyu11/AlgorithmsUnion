package base.util;

import java.util.Random;

import base.Base;

/**
 * Knuth置乱算法
 * 对应5.3节
 * 此算法的证明要比此算法本身更有价值
 * <p>
 * LeetCode原题:
 * https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleUtil {

    /**
     * Pick from [lo,hi]
     * @param lo
     * @param hi
     * @return
     */
    public static int randomPick(int lo, int hi) {
        Random r = new Random();
        return lo + (int) ((hi + 1 - lo) * r.nextDouble());
    }

    public static void shuffle(int[] A) {
        shuffle(A, 1);
    }

    private static void shuffle(int[] A, int solution) {
        Solution s;
        switch (solution) {
            case 1: {
                s = new Solution1();
                break;
            }
            case 2: {
                s = new Solution2();
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        s.shuffle(A);
    }

    private abstract static class Solution {
        Random r = new Random();

        public abstract void shuffle(int[] A);
    }

    // swap from [0 , i]
    private static class Solution1 extends Solution {

        @Override
        public void shuffle(int[] A) {
            final int n = A.length;
            for (int i = 0; i < n; ++i) {
                // swap from [i , n-1]
                Base.swap(A, i, i + (int) ((n - i) * r.nextDouble()));
            }
        }

    }

    // swap from [0 , i]
    private static class Solution2 extends Solution {

        @Override
        public void shuffle(int[] A) {
            final int n = A.length;
            for (int i = 0; i < n; ++i) {
                Base.swap(A, i, (int) ((i + 1) * r.nextDouble()));
            }
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            int j = randomPick(0, 10);
            Base.print(j + " ");
        }
//        int[] arr = new int[15];
//        for (int i = 0; i < 15; ++i) {
//            arr[i] = i;
//        }
//
//        Solution s = new Solution2();
//
//        s.shuffle(arr);
//        printArr(arr);

    }
}
