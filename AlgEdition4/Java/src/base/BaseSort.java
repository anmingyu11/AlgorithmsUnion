package base;

import java.util.Arrays;

public abstract class BaseSort extends Base {

    public abstract void sort(Comparable[] a);

    public static int compareTo(Comparable v, Comparable w) {
        return v.compareTo(w);
    }

    public static boolean greater(Comparable[] a, int i, int j) {
        return greater(a[i], a[j]);
    }

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static boolean less(Comparable[] a, int i, int j) {
        return less(a[i], a[j]);
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        println(Arrays.toString(a));
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; ++i) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public void testSorted(Comparable[] arr) {
        println("before sorted : ");
        show(arr);
        sort(arr);
        println("afterSort");
        show(arr);
    }

}
