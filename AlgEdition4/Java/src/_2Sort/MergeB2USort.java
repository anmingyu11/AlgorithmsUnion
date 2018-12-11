package _2Sort;

import base.BaseSort;

public class MergeB2USort extends BaseSort {
    private Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        final int len = a.length;
        aux = new Comparable[a.length];
        for (int sz = 1; sz < len; sz += sz) {// 二分
            for (int lo = 0; lo < len - sz; lo += sz + sz) {//
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, len - 1));
            }
        }
    }

    private void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; ++k) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        new MergeB2USort().testSorted(generateArrRandom(10, -5, 10));
    }
}
