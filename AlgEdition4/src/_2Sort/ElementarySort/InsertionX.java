package _2Sort.ElementarySort;

/******************************************************************************
 *  Compilation:  javac InsertionX.java
 *  Execution:    java InsertionX < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/21elementary/tiny.txt
 *                https://algs4.cs.princeton.edu/21elementary/words3.txt
 *
 *  Sorts a sequence of strings from standard input using an optimized
 *  version of insertion sort that uses half exchanges instead of
 *  full exchanges to reduce data movement..
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java InsertionX < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java InsertionX < words3.txt
 *  all bad bed bug dad ... yes yet zoo   [ one string per line ]
 *
 ******************************************************************************/

import base.stdlib.StdOut;

/**
 * The {@code InsertionX} class provides static methods for sorting
 * an array using an optimized version of insertion sort (with half exchanges
 * and a sentinel).
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * 优化的插入排序
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class InsertionX {

    // This class should not be instantiated.
    private InsertionX() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * 先将较大的元素向右移动
     * 再从左向右, 以i为基准,如果i比前面的小,就一直挪动前面的元素,直到找到合适的位置,将i对应的元素替换进去
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;

        int exchanges = 0;
        for (int i = n - 1; i > 0; --i) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                ++exchanges;
            }
        }
        if (exchanges == 0) {
            return;
        }

        for (int i = 2; i < n; ++i) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                --j;
            }
            a[j] = v;
        }

    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
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
        for (int i = 1; i < a.length; i++)
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
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 3, 6, 7, 4, 2, 5};
        InsertionX.sort(a);
        show(a);
    }

}