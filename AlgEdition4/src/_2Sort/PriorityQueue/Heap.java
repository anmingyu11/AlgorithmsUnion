package _2Sort.PriorityQueue;

/******************************************************************************
 *  Compilation:  javac Heap.java
 *  Execution:    java Heap < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tiny.txt
 *                https://algs4.cs.princeton.edu/24pq/words3.txt
 *
 *  Sorts a sequence of strings from standard input using heapsort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Heap < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Heap < words3.txt
 *  all bad bed bug dad ... yes yet zoo   [ one string per line ]
 *
 ******************************************************************************/

import base.stdlib.StdOut;

/**
 * The {@code Heap} class provides a static methods for heapsorting
 * an array.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * 堆有序:
 * 当一棵二叉树的每个结点都大于等于它的两个子节点时,它被称为堆有序.
 * 上浮:
 * 子节点比父节点小,堆有序打破,需要通过上浮来恢复堆
 * 只要子节点比父节点大,堆有序
 * 下沉:
 * 父结点比子节点大,堆有序打破,需要通过下沉来恢复堆.
 * 只要父结点比子节点小,则堆有序.
 * 堆有序化:
 * 1. 设指针p=0;p从0扫描到n,第二个指针j =p ,j从p扫描到0,通过上浮使堆有序化,时间复杂度跟插入排序差不多.
 * 2. 设指针p=k/2,从k/2扫描到0,用sink方法使堆有序化:
 * 证(我写的大土话,非形式化证明):
 * k=N/2, k的两个子节点是2*k,2*k+1,即,k的子节点分别是N,N+1,因为k-1 < k,所以在扫描过程中,k会遍历堆中的所有结点,
 * sink又能使k结点堆有序化,所以,这个方法可以使堆有序.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Heap {

    // This class should not be instantiated.
    private Heap() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * 堆有序化后:根结点是堆有序的二叉树中的最大结点,所以根据sink构建的堆,可以构建成最小二根堆或最大二根堆,
     * 如果自然正序排序,则交换1和n的位置,n是最大值,然后对子堆(n-1)进行堆有序化.
     *
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int n = pq.length;
        for (int k = n / 2; k >= 1; --k) {
            sink(pq, k, n);
        }
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; heapsorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    }

}