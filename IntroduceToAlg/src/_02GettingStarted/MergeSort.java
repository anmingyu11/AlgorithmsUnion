package _02GettingStarted;

import base.Base;
import base.interfaces.ISort;
import base.util.TestUtil;

/**
 * 归并排序
 * 对应2.3.1
 */
public class MergeSort extends Base {

    private abstract static class Solution implements ISort {
    }

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

        // 这个方法并不好,还是普林斯顿的版本更好一些, 更简短,且更容易理解.
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

    /**
     * 自底向上版的归并排序,不是分治法
     * 来自于 <<算法4>>>
     */
    private static class Solution3 extends Solution {

        @Override
        public void sort(int[] A) {
            final int n = A.length;
            int[] aux = new int[n];
            for (int sz = 1; sz < n; sz += sz) {
                for (int lo = 0; lo < n - sz; lo += sz + sz) {
                    merge(A, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
                }
            }

        }

        private void merge(int[] A, int[] aux, int lo, int mid, int hi) {
            for (int k = lo; k <= hi; ++k) {
                aux[k] = A[k];
            }

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; ++k) {
                if (i > mid) {
                    A[k] = aux[j++];
                } else if (j > hi) {
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

        Solution s = new Solution3();

        TestUtil.testSort(s);
    }

}
