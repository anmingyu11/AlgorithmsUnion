package base;

import java.util.LinkedList;
import java.util.List;

public class Testcases{

    private final static List<int[]> testcases = new LinkedList<int[]>();

    public static class Sort {

        public static List<int[]> getTestcases() {
            testcases.clear();
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
    }

    private Testcases() {
    }

}
