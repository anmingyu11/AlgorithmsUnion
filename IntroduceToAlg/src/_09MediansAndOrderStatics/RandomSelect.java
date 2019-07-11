package _09MediansAndOrderStatics;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import base.Base;
import base.StopwatchCPU;
import base.util.ArraysUtil;
import base.util.MathUtil;
import base.util.ShuffleUtil;

public class RandomSelect extends Base {

    private abstract static class Solution {
        public abstract int select(int[] A, int k);

        public void test() {
            List<int[]> l = new LinkedList<>();
            l.addAll(ArraysUtil.generatePermutation(5));
            l.addAll(ArraysUtil.generateRandom(true, 100, 100));
            l.addAll(ArraysUtil.generateSpecial(true));

            println("Select test Start : testcases size : " + l.size());
            StopwatchCPU cpu = new StopwatchCPU();
            Random r = new Random();
            for (int[] a : l) {
                // your
                int k = (int) (a.length * r.nextDouble());
                int your = select(a, k);

                // real
                int[] b = a.clone();
                Arrays.sort(b);
                int real = b[k];
                if (your != real) {
                    println("failed at arr : " + Arrays.toString(b));
                    println(" k : " + k);
                    println(" your is : " + your);
                    println(" real is : " + real);
                    return;
                }
            }
            println("congratulations your select solution has passed");
            println("time used : " + cpu.elapsedTime2());
        }
    }

    // 期望为线性的随机选择算法
    private static class Solution1 extends Solution {
        @Override
        public int select(int[] A, int k) {
            if (k >= A.length) {
                throw new IllegalArgumentException();
            }
            return randomSelect(A, 0, A.length - 1, k);
        }

        public int randomSelect(int[] A, int lo, int hi, int k) {
            if (lo == hi) {
                return A[lo];
            }
            int p = randomPartition(A, lo, hi);
            if (p < k) {
                return randomSelect(A, p + 1, hi, k);
            } else if (p > k) {
                return randomSelect(A, lo, p - 1, k);
            } else {
                return A[p];
            }
        }

        public int randomPartition(int[] A, int lo, int hi) {
            swap(A, hi, ShuffleUtil.randomPick(lo, hi));
            int pivot = A[hi];
            int i = lo - 1;
            for (int j = lo; j < hi; ++j) {
                if (A[j] <= pivot) {
                    swap(A, ++i, j);
                }
            }
            swap(A, i + 1, hi);
            return i + 1;
        }
    }

    // 朴素的partition
    private static class Solution2 extends Solution {
        @Override
        public int select(int[] A, int k) {
            if (k >= A.length) {
                throw new IllegalArgumentException();
            }
            return select(A, 0, A.length - 1, k);
        }

        public int select(int[] A, int lo, int hi, int k) {
            if (lo == hi) {
                return A[lo];
            }
            int p = partition(A, lo, hi);
            if (p < k) {
                return select(A, p + 1, hi, k);
            } else if (p > k) {
                return select(A, lo, p - 1, k);
            } else {
                return A[p];
            }
        }

        public int partition(int[] A, int lo, int hi) {
            int pivot = A[hi];
            int i = lo - 1;
            for (int j = lo; j < hi; ++j) {
                if (A[j] <= pivot) {
                    swap(A, ++i, j);
                }
            }
            swap(A, i + 1, hi);
            return i + 1;
        }
    }

    private static class Solution3 extends Solution {

        @Override
        public int select(int[] A, int k) {
            return 0;
        }

        private int selectAux(int[] A, int lo, int hi) {
            return 0;
        }

        private int partition(int[] A, int r, int lo, int hi) {
            swap(A, hi, r);
            return 0;
        }

        private void insertionSort(int[] A, int lo, int hi) {
            for (int i = lo + 1; i <= hi; ++i) {
                int v = A[i];
                int j = i - 1;
                while (j >= 0 && A[j] > v) {
                    A[j + 1] = A[j--];
                }
                A[j + 1] = v;
            }
        }

        /**
         * A is sorted Array
         */
        private int median(int[] A, int lo, int hi) {
            final int n = hi - lo + 1;
            if (MathUtil.isEven(n)) {
                return A[lo + n / 2] < A[lo + n / 2 - 1] ? lo + n / 2 : lo + n / 2 - 1;
            } else {
                return lo + n / 2;
            }
        }
    }

    public static void main(String[] args) {

//        Solution s = new Solution1();
//        println("------------Expectation O(n) random pick------------");
//        s.test();
//        println("------------Expectation O(n) not random pick------------");
//        s = new Solution2();
//        s.test();
//        println("------------At Most Linear O(n)------------");
//        s = new Solution3();
//        s.test();
    }

}
