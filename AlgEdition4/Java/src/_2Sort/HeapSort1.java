package _2Sort;

import base.BaseSort;

//index startWith 0
public class HeapSort1 extends BaseSort {

    @Override
    public void sort(Comparable[] a) {
        int len = a.length;
        for (int i = len / 2; i >= 1; --i) {
            sink(a, i, len);
        }
        while (len > 1) {
            heapExch(a, 1, len--);
            sink(a, 1, len);
        }
    }

    private boolean heapLess(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private void heapExch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    private void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int child = 2 * k;
            if (child < n && heapLess(a, child, child + 1)) {
                ++child;
            }
            if (!heapLess(a, k, child)) {
                break;
            }
            heapExch(a, child, k);
            k = child;
        }
    }

    public static void main(String[] args) {
        new HeapSort1().testSorted(generateArrRandom(10, 0, 10));
    }
}
