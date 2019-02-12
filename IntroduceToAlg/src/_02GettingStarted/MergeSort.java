package _02GettingStarted;

import base.BaseSort;

/**
 * 归并排序
 */
public class MergeSort extends BaseSort {

    private static class Solution1 extends Solution {

        @Override
        public void sort(int[] A) {
            mergeSort(A, 0, A.length - 1);
        }

        private void mergeSort(int[] A, int p, int r) {
            if (p >= r) {
                return;
            }
            //求中间点
            int q = (p + r) / 2; // p + (r-p)/2
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }

        // 这个方法并不好,如果数组里有元素也是那个无穷大的话就麻烦了,还是普林斯顿的版本更好一些,还省了点复杂度.
        private void merge(int[] A, int p, int q, int r) {
            int n1 = q - p + 1;
            int n2 = r - q; // r - (q + 1) +1

            int[] L = new int[n1 + 1];
            int[] R = new int[n2 + 1];
            for (int i = 0; i < n1; ++i) {
                L[i] = A[p + i];
            }
            for (int i = 0; i < n2; ++i) {
                R[i] = A[q + 1 + i];
            }
            L[n1] = Integer.MAX_VALUE;
            R[n2] = Integer.MAX_VALUE;
            int j = 0, i = 0;

            for (int k = p; k <= r; ++k) {
                if (L[i] < R[j]) {
                    A[k] = L[i];
                    ++i;
                } else {
                    A[k] = R[j];
                    ++j;
                }
            }
        }

    }

    //普林斯顿版
    private static class Solution2 extends Solution {

        @Override
        public void sort(int[] A) {
            int[] aux = new int[A.length];
            sort(A, aux, 0, A.length - 1);
        }

        private void sort(int[] A, int[] aux, int lo, int hi) {
            if (hi <= lo) {
                return;
            }

            int mid = (lo + hi) / 2;
            sort(A, aux, lo, mid);
            sort(A, aux, mid + 1, hi);
            merge(A, aux, lo, mid, hi);
        }

        private void merge(int[] A, int[] aux, int lo, int mid, int hi) {
            for (int i = lo; i <= hi; ++i) {
                aux[i] = A[i];
            }

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; ++k) {
                if (j > hi) {
                    A[k] = aux[i++];
                } else if (i > mid) {
                    A[k] = aux[i++];
                } else if (aux[i] < aux[j]) {
                    A[k] = aux[i++];
                } else {
                    A[k] = aux[j++];
                }
            }
        }
    }

    public static void main(String[] args) {

        test(new Solution2());

    }
}
