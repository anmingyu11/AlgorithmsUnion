package base;

import java.util.List;

public class BaseSort extends Base {

    protected abstract static class Solution {
        public abstract void sort(int[] A);

        public void test(List<int[]> testcases) {
            for (int[] t : testcases) {
                println("----------------");
                println("Before sort : ");
                printArr(t);
                sort(t);
                println("After sort : ");
                printArr(t);
            }
        }
    }

    protected static void test(Solution s) {
        s.test(Testcases.Sort.getTestcases());
    }
}
