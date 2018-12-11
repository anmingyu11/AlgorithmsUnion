package _2Sort;

import base.BaseSort;

public class InsertionSort extends BaseSort {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; ++i) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); --j) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        new InsertionSort().testSorted(generateArrRandom(10, 0, 20));
    }
}


