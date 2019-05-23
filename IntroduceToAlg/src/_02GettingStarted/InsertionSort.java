package _02GettingStarted;

import base.Base;
import base.interfaces.ISort;
import base.util.TestUtil;

/**
 * 插入排序
 * 对应2.1
 */
public class InsertionSort extends Base {

    // Sort的Solution可公用
    private abstract static class Solution implements ISort {
    }

    /**
     * 算法导论
     */
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

    /**
     * 出自算法4,这个的渐进复杂度虽然说也是Θ(n^2)
     * 但是比算法导论中的多一次操作,并不是最优的写法.
     */
    private static class Solution2 extends Solution {

        @Override
        public void sort(int[] A) {
            final int n = A.length;
            for (int i = 1; i < n; ++i) {
                for (int j = i; j > 0 && A[j] < A[j - 1]; --j) {
                    swap(A, j, j - 1);
                }
            }
        }

        private void swap(int[] A, int i, int j) {
            int t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        TestUtil.testSort(s);
    }
}
