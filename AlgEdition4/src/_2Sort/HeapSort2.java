package _2Sort;

import base.BaseSort;

public class HeapSort2 extends BaseSort {
    @Override
    public void sort(Comparable[] a) {
        int n = a.length - 1;
        for (int i = n / 2; i >= 0; --i) {
            sink(a, i, n);
        }
        while (n > 0) {
            exch(a, 0, n--);
            sink(a, 0, n);
        }
    }

    private void sink(Comparable[] a, int k, int n) {
        while (2 * k + 1 <= n) {
            int child = 2 * k + 1;
            if (child < n && less(a, child, child + 1)) {
                ++child;
            }
            if (!less(a, k, child)) {
                break;
            }
            exch(a, k, child);
            k = child;
        }
    }

    public static void main(String[] args) {
        new HeapSort2().testSorted(generateArrRandom(10, 0, 10));
    }
}
