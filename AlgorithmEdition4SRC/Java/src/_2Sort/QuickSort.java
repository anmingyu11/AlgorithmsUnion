package _2Sort;

import base.BaseSort;

public class QuickSort extends BaseSort {

    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int p = 0;
        try {
            p = partition(a, lo, hi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private int partition(Comparable[] a, int lo, int hi) throws Exception {
        int l = lo; // pivot是lo所以要跳过lo
        int r = hi + 1; // 因为用的--r所以要+1
        Comparable p = a[lo];

        while (true) {

            while (less(a[++l], p)) {
                //如果令l==r 则会越界。
                if (l == hi) {
                    break;
                }
            }
            // a[l] >= p

            while (less(p, a[--r])) {
                //r找到的是最后一个a[r] < p
            }
            // a[r] <= p

            // check if pointers cross
            if (l >= r) {
                break;
            }

            exch(a, l, r);
        }

        exch(a, lo, r);

        return r;
    }

    public static void main(String[] args) {
        Integer[] nums = generateArrRandom(10, 0, 15);
        nums[0] = 7;
        new QuickSort().testSorted(nums);
    }
}
