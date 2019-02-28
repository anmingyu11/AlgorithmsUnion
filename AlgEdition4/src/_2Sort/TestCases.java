package _2Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestCases {

    public static List<Integer[]> getTestcases() {
        List<Integer[]> testcases = new LinkedList<>();
        testcases.add(new Integer[]{3, 2, 1, 5, 6, 4});
        testcases.add(new Integer[]{3, 2, 3, 1, 2, 4, 5, 5, 6});
        testcases.add(new Integer[]{99, 99});
        testcases.add(new Integer[]{1});
        testcases.add(new Integer[]{7, 6, 5, 4, 3, 2, 1});
        testcases.add(new Integer[]{1, 2, 3, 4, 5});
        testcases.add(new Integer[]{1, 2, 3, 6, 5});
        testcases.add(new Integer[]{-1, 2, 0});
        testcases.add(new Integer[]{11, 1, 1, 1, 3, 2, 5, 67, 78});
        testcases.add(new Integer[]{9, 9, 9, 9, 9, 9, 1, 2, 3, 1, 1});
        testcases.add(new Integer[]{1, 1, 1, 1, 9});
        return testcases;
    }

    public static List<Integer[]> getTestcases100Ran(int n) {
        List<Integer[]> testcases = new LinkedList<>();
        testcases.add(new Integer[]{3, 2, 1, 5, 6, 4});
        testcases.add(new Integer[]{3, 2, 3, 1, 2, 4, 5, 5, 6});
        testcases.add(new Integer[]{99, 99});
        testcases.add(new Integer[]{1});
        testcases.add(new Integer[]{7, 6, 5, 4, 3, 2, 1});
        testcases.add(new Integer[]{1, 2, 3, 4, 5});
        testcases.add(new Integer[]{1, 2, 3, 6, 5});
        testcases.add(new Integer[]{-1, 2, 0});
        testcases.add(new Integer[]{11, 1, 1, 1, 3, 2, 5, 67, 78});
        testcases.add(new Integer[]{9, 9, 9, 9, 9, 9, 1, 2, 3, 1, 1});
        testcases.add(new Integer[]{1, 1, 1, 1, 9});
        for (int i = 0; i < 100; ++i) {
            Random r = new Random(System.currentTimeMillis());
            Integer[] arr = new Integer[n];
            for (int j = 0; j < n; ++j) {
                arr[j] = r.nextInt(10000);
            }
            testcases.add(arr);
        }
        return testcases;
    }

    public static boolean checkSort(boolean up, Integer[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            if (up) {
                if (a[i] > a[i + 1]) {
                    return false;
                }
            } else {
                if (a[i] < a[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

}
