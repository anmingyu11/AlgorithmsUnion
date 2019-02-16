package _02GettingStarted;

import java.util.LinkedList;
import java.util.List;

import base.Base;

/**
 * 插入排序
 * 对应2.1
 */
public class InsertionSort extends Base {

    // Sort的Solution可公用
    private abstract static class Solution {
        public abstract void sort(int[] A);

        public void test() {
            test(Testcases.getTestcases());
        }

        private void test(List<int[]> testcases) {
            for (int[] t : testcases) {
                println("----------------");
                println("Before sort : ");
                printArr(t);
                sort(t);
                println("After sort : ");
                printArr(t);
                println(check(t) ? "Correct" : "Incorrect");
            }
        }

        /**
         * 是否按自然序排序的正确性检查
         *
         * @param A
         * @return
         */
        private boolean check(int[] A) {
            final int n = A.length;

            for (int i = 0; i < n - 1; ++i) {
                if (A[i + 1] < A[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 算法导论
     */
    private static class Solution1 extends Solution {

        @Override
        public void sort(int[] A) {
            for (int i = 1; i < A.length; ++i) {
                int key = A[i];
                int j = i - 1;
                while (j >= 0 && A[j] > key) {
                    A[j + 1] = A[j];
                    --j;
                }
                A[j + 1] = key;
            }
        }

    }

    /**
     * 出自算法4,这个的渐进复杂度虽然说也是Θ(n^2)
     * 但是比算法导论中的多一次操作,并不是最优的写法.
     */
    private static class Solution2 extends Solution {

        @Override
        public void sort(int[] A) {
            final int n = A.length;
            for (int i = 1; i < n; ++i) {
                for (int j = i; j > 0 && A[j] < A[j - 1]; --j) {
                    swap(A, j, j - 1);
                }
            }
        }

        private void swap(int[] A, int i, int j) {
            int t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        s.test();
    }

    private static class Testcases {

        static List<int[]> getTestcases() {
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

    }

}
