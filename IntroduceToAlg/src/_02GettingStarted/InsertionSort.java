package _02GettingStarted;

import base.BaseSort;
import base.Testcases;

/**
 * 插入排序
 */
public class InsertionSort extends BaseSort {

    private static class Solution1 extends Solution {

        @Override
        public void sort(int[] A) {
            for (int i = 1; i < A.length; ++i) {
                int key = A[i];
                int j = i - 1;
                while (j >= 0 && A[j] > key) {
                    A[j + 1] = A[j];
                    --j;
                }
                A[j + 1] = key;
            }
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        s.test(Testcases.Sort.getTestcases());
    }
}
