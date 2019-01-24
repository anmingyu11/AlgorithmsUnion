package _2Sort;

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

import base.BaseSort;
import base.StopwatchCPU;
import base.stdlib.StdRandom;

/**
 * The {@code Quick} class provides static methods for sorting an
 * array and selecting the ith smallest element in an array using quicksort.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/23quick">Section 2.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Quick extends BaseSort {

    // This class should not be instantiated.
    private Quick() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int p = partition(a, lo, hi);
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

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


    private void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int p = partition1(arr, lo, hi);
        sort(arr, lo, p - 1);
        sort(arr, p + 1, hi);
    }

    private int partition1(int[] arr, int lo, int hi) {
        int p = arr[lo];// 枢轴记录
        int i = lo, j = hi;
        while (i < j) {
            while (i < j && arr[j] >= p) {
                --j;
            }
            arr[i] = arr[j];// 交换比枢轴小的记录到左端
            while (i < j && arr[i] <= p) {
                ++i;
            }
            arr[j] = arr[i];// 交换比枢轴大的记录到右端
        }
        // 扫描完成，枢轴到位
        arr[i] = p;
        // 返回的是枢轴的位置
        return i;
    }

    private int partition2(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {

            while (v > a[++i]) {
                if (i == hi) {
                    break;
                }
            }

            while (v < a[--j]) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            swap(a, i, j);
        }

        swap(a, lo, j);

        return j;
    }

    private int partition3(int[] nums, int lo, int hi) {
        int v = nums[lo];
        //i==lo 因为要剔除一个两个元素的情况，如果跳过第一个就没了。
        int i = lo, j = hi;
        while (i < j) {
            while (nums[i] <= v) {
                ++i;
                if (i == hi) {
                    break;
                }
            }
            while (nums[j] >= v) {
                --j;
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }

        swap(nums, j, lo);

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
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
    public Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) {
                hi = i - 1;
            } else if (i < k) {
                lo = i + 1;
            } else {
                return a[i];
            }
        }
        return a[lo];
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
        int[] arr1 = new int[]{3, 2, 1, 5, 6, 4};
        int[] arr2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int[] arr3 = new int[]{99, 99};
        int[] arr4 = new int[]{1};
        int[] arr5 = new int[]{7, 6, 5, 4, 3, 2, 1};
        int[] arr6 = new int[]{-1, 2, 0};

        StopwatchCPU cpu = new StopwatchCPU();
        //new Quick().sort(arr1);
        new Quick().sort(arr2);
        new Quick().sort(arr3);
        new Quick().sort(arr4);
        new Quick().sort(arr5);
        new Quick().sort(arr6);
        //println(Arrays.toString(arr1));
        //println(Arrays.toString(arr2));
        //println(Arrays.toString(arr3));
        //println(Arrays.toString(arr4));
        //println(Arrays.toString(arr5));
        //println(Arrays.toString(arr6));
        println(cpu.elapsedTime2());
    }

}