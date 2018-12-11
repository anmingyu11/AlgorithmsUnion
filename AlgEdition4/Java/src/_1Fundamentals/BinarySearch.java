package _1Fundamentals;

import java.util.Arrays;

import base.Base;
import base.BaseSort;

public class BinarySearch {

    private BinarySearch() {
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a   the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(Comparable[] a, Comparable key) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(a[mid]);
            if (cmp > 0) {
                lo = mid + 1;
            } else if (cmp < 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }

        return lo;
    }

    /**
     * Returns the index of the specified key in the specified array.
     * This function is poorly named because it does not give the <em>rank</em>
     * if the array has duplicate keys or if the key is not in the array.
     *
     * @param key the search key
     * @param a   the array of integers, must be sorted in ascending order
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     * @deprecated Replaced by {@link #indexOf(Comparable[], Comparable)}.
     */
    @Deprecated
    public static int rank(Comparable[] a, Comparable key) {
        return indexOf(a, key);
    }

    /**
     * Reads in a sequence of integers from the whitelist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Integer[] nums = BaseSort.generateArrSequence(0, 20);
        Base.println(Arrays.toString(nums) + " : len " + nums.length);
        int i = indexOf(nums, 21);
        Base.println("i : " + i);
    }

}
