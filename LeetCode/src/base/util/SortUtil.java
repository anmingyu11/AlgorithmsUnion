package base.util;

import java.util.List;

import base.Base;
import base.interfaces.ISort;

public class SortUtil {

    public static void testSort(ISort iSort) {
        // hiahia
        List<int[]> perms = PermutationUtil.permutations(5);
        addRest(perms);
        for (int[] a : perms) {
            int[] origin = a.clone();
            iSort.sort(a);
            if (!checkSorted(a)) {
                Base.println("your sort has failed");
                Base.print("failed at : ");
                Base.printArr(origin);
                Base.println("your is : ");
                Base.printArr(a);
                return;
            }
        }
        Base.println("congratulations your sort has passed.");
    }

    private static void addRest(List<int[]> l) {
        l.add(new int[]{3, 2, 1, 5, 6, 4});
        l.add(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6});
        l.add(new int[]{99, 99});
        l.add(new int[]{1});
        l.add(new int[]{7, 6, 5, 4, 3, 2, 1});
        l.add(new int[]{1, 2, 3, 4, 5});
        l.add(new int[]{1, 2, 3, 6, 5});
        l.add(new int[]{-1, 2, 0});
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
