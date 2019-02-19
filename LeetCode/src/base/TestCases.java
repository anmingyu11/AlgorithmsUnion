package base;

import java.util.LinkedList;
import java.util.List;

public class TestCases {

    public static class Sort {

        public static List<int[]> getTestcases() {
            List<int[]> testcases = new LinkedList<>();
            testcases.add(new int[]{3, 2, 1, 5, 6, 4});
            testcases.add(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6});
            testcases.add(new int[]{99, 99});
            testcases.add(new int[]{1});
            testcases.add(new int[]{7, 6, 5, 4, 3, 2, 1});
            testcases.add(new int[]{1, 2, 3, 4, 5});
            testcases.add(new int[]{1, 2, 3, 6, 5});
            testcases.add(new int[]{-1, 2, 0});
            return testcases;
        }

        public static boolean checkSort(boolean up, int[] a) {
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
}
