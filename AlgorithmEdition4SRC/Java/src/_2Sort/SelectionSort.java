package _2Sort;

import base.BaseSort;

public class SelectionSort extends BaseSort {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; ++i) {
            int min = i;
            for (int j = i + 1; j < N; ++j) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        SelectionSort s = new SelectionSort();
        s.testSorted(generateArrRandom(10, -10, 10));
    }
}
