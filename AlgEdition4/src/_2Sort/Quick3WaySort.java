package _2Sort;

import base.BaseSort;

public class Quick3WaySort extends BaseSort {

    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int lt = lo, i = lo + 1, gt = hi;
        Comparable p = a[lo];
        while (i <= gt) {
            int cmp = compareTo(a[i], p);
            if (cmp < 0) {
                exch(a, i++, lt++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
                //gt进入到 < i时会放弃
            } else {
                ++i;
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        new Quick3WaySort().testSorted(generateArrRandom(10, 0, 10));
    }
}
