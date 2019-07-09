package _05ProbabilityAndRandomlize;

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
public class KnuthShuffle extends Base {

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
                swap(A, i, i + (int) ((n - i) * r.nextDouble()));
            }
        }

    }

    // swap from [0 , i]
    private static class Solution2 extends Solution {

        @Override
        public void shuffle(int[] A) {
            final int n = A.length;
            for (int i = 0; i < n; ++i) {
                swap(A, i, (int) ((i + 1) * r.nextDouble()));
            }
        }

    }

    /**
     * 这个问题的正确性验证难住我了.
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[15];
        for (int i = 0; i < 15; ++i) {
            arr[i] = i;
        }

        Solution s = new Solution2();

        s.shuffle(arr);
        printArr(arr);

    }
}
