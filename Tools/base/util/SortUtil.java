package base.util;

import java.util.List;
import java.util.Random;

import base.Base;
import base.interfaces.ISort;

public class SortUtil {

    public static void testSort(ISort iSort) {
        // hiahia
        List<int[]> perms = PermutationUtil.permutations(5);
        addRest(perms);
        addRandom(perms);
        Base.println("Sort Test Start , number of array is : " + perms.size());
        for (int[] a : perms) {
            int[] origin = a.clone();
            iSort.sort(a);
            if (!checkSorted(a)) {
                Base.println("your sort has failed");
                Base.println("failed at : ");
                Base.printArr(origin);
                Base.println("your is : ");
                Base.printArr(a);
                return;
            }
        }
        Base.println("congratulations your sort has passed.");
    }

    private static void addRandom(List<int[]> l) {
        final int size = 30;
        final int n = 7;
        final int bound = 5;
        int[] arr = new int[n];
        for (int sz = 0; sz < size; ++sz) {
            Random r = new Random();
            for (int i = 0; i < n; ++i) {
                arr[i] = r.nextInt(bound);
            }
            l.add(arr.clone());
        }
    }

    private static void addRest(List<int[]> l) {
        l.add(new int[]{99, 99});
        l.add(new int[]{1});
        l.add(new int[]{-1, 2, 0});
        l.add(new int[]{-1, 1, 1, 1, 1, 1, 1});
        l.add(new int[]{-1, 1, -1, 1, -2, 1, 1, -1, 0});
        l.add(new int[]{-1, 0, 0, 0, 0, 0, 0, -1, -2, 1, 1, 0, 0, 0, 1});
    }

    public static boolean checkSorted(int[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
