package _2Sort.QuickSort;

/******************************************************************************
 *  Compilation:  javac Quick.java
 *  Execution:    java Quick < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                https://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using quicksort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Quick < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Quick < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *
 *  Remark: For a type-safe version that uses static generics, see
 *
 *    https://algs4.cs.princeton.edu/23quicksort/QuickPedantic.java
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.List;

import _2Sort.TestCases;
import base.stdlib.StdOut;

/**
 * The {@code Quick} class provides static methods for sorting an
 * array and selecting the ith smallest element in an array using quicksort.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/23quick">Section 2.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * 快速排序
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Quick {

    // This class should not be instantiated.
    private Quick() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        // StdRandom.shuffle(a); 不做随机化处理了.
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        // 这处必要
        if (lo >= hi) {
            return lo;
        }
        int i = lo, j = hi;
        Comparable v = a[lo];
        while (true) {

            // a[i] <= a[lo]
            while (!less(v, a[i])) {
                ++i;
                if (i == hi) {
                    break;
                }
            }

            // a[j] >= a[lo]
            while (!less(a[j], a[lo])) {
                --j;
                if (j == lo) {
                    break;
                }// redundant since a[lo] acts as sentinel i = lo, j = hi+1
            }

            // check if pointers cross
            if (i >= j) {
                break;
            }

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * Rearranges the array so that {@code a[k]} contains the kth smallest key;
     * {@code a[0]} through {@code a[k-1]} are less than (or equal to) {@code a[k]}; and
     * {@code a[k+1]} through {@code a[n-1]} are greater than (or equal to) {@code a[k]}.
     *
     * @param a the array
     * @param k the rank of the key
     * @return the key of rank {@code k}
     * @throws IllegalArgumentException unless {@code 0 <= k < a.length}
     */
    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        int lo = 0, hi = a.length - 1;
        while (true) {
            int p = partition(a, lo, hi);
            if (p < k) {
                lo = p + 1;
            } else if (p > k) {
                hi = p - 1;
            } else {
                return a[p];
            }
        }

    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        //testSort();
        testSelect();
    }

    private static void testSort() {
        List<Integer[]> testcases = TestCases.getTestcases100Ran(20);

        boolean sort = false;
        for (Integer[] arr : testcases) {
            Integer[] before = arr.clone();
            Quick.sort(arr);
            sort = TestCases.checkSort(true, arr);
            if (!sort) {
                StdOut.println("sort failed at:");
                StdOut.println(Arrays.toString(before));
                break;
            }
        }
        if (sort) {
            StdOut.println("congratulations.your sort has passed.");
        }
    }

    private static void testSelect() {
        Integer[] arr = {5, 6, 1, 2, 3, 4, 8, 7, 10, 9, 0};
        final int n = arr.length;
        for (int i = 0; i < n; ++i) {
            if (i < n - 1) {
                StdOut.print(select(arr, i) + " , ");
            } else {
                StdOut.println(select(arr, i));
            }

        }
    }

}