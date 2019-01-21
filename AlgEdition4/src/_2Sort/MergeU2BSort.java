package _2Sort;

import base.BaseSort;

public class MergeU2BSort extends BaseSort {
    private static Comparable[] aux;//辅助

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, lo, mid); // sort left
        sort(a, mid + 1, hi); // sort right
        merge(a, lo, mid, hi); // merge
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; ++k) {
            if (i > mid) a[k] = aux[j++];//左边用尽
            else if (j > hi) a[k] = aux[i++];//右边用尽
            else if (less(aux[j], aux[i])) a[k] = aux[j++];//右边小于左边
            else a[k] = aux[i++];//左边小于右边
        }
    }

    public static void main(String[] args) {
        MergeU2BSort sort = new MergeU2BSort();
        sort.testSorted(generateArrRandom(10, -10, 10));
    }

}
