package base.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.Base;
import base.StopwatchCPU;
import base.interfaces.IQueue;
import base.interfaces.ISort;
import base.interfaces.ISortDouble;
import base.interfaces.IStack;

public class TestUtil {

    public static void testQueue(IQueue<Integer> q) {
        List<int[]> testcases = new LinkedList<>();
        testcases.addAll(ArraysUtil.generatePermutation(5));
        testcases.addAll(ArraysUtil.generateSpecial(true));

        StopwatchCPU cpu = new StopwatchCPU();
        boolean pass = true;
        for (int[] tc : testcases) {
            final int n = tc.length;
            for (int i = 0; i < n; ++i) {
                q.enqueue(tc[i]);
            }
            for (int i = 0; i < n; ++i) {
                int val = q.dequeue();
                if (tc[i] != val) {
                    pass = false;
                    Base.print("Queue Failed failed at :");
                    Base.printArr(tc);
                    break;
                }
            }
            if (!pass) {
                break;
            }
        }

        Base.println("pass : " + pass);
        Base.println("timeUsed : " + cpu.elapsedTime2());
    }

    public static void testStack(IStack<Integer> stack) {
        List<int[]> testcases = new LinkedList<>();
        testcases.addAll(ArraysUtil.generatePermutation(5));
        testcases.addAll(ArraysUtil.generateSpecial(true));

        StopwatchCPU cpu = new StopwatchCPU();
        boolean pass = true;
        for (int[] tc : testcases) {
            final int n = tc.length;
            for (int i = 0; i < n; ++i) {
                stack.push(tc[i]);
            }
            for (int i = n - 1; i >= 0; --i) {
                int top = stack.pop();
                if (top != tc[i]) {
                    pass = false;
                    Base.print("failed at : ");
                    Base.printArr(tc);
                    break;
                }
            }
            if (!pass) {
                break;
            }
        }

        Base.println("pass : " + pass);
        Base.println("timeUsed : " + cpu.elapsedTime2());
    }

    public static void testSort(ISort iSort) {
        testSort(iSort, true);
    }

    public static void testSort(ISort iSort, boolean printTimeUsed) {
        testSort(iSort, true, 20, printTimeUsed);
    }

    public static void testSort(ISort iSort, int randomLen, boolean printTimeUsed) {
        testSort(iSort, true, randomLen, printTimeUsed);
    }

    public static void testSort(ISort iSort, boolean addNeg, boolean printTimeUsed) {
        testSort(iSort, addNeg, 20, printTimeUsed);
    }

    public static void testSort(ISort iSort, boolean addNeg, int randomArrLen, boolean printTimeUsed) {
        final boolean debug = false;
        // hiahia
        List<int[]> l = PermutationUtil.permutations(5);
        l.addAll(ArraysUtil.generateSpecial(addNeg));
        l.addAll(ArraysUtil.generateRandom(addNeg, randomArrLen));
        Base.println("Sort Test Start , number of array is : " + l.size());
        StopwatchCPU cpu = null;
        if (printTimeUsed) {
            cpu = new StopwatchCPU();
        }
        int i = 0;
        for (int[] a : l) {
            ++i;
            int[] origin = a.clone();
            if (debug) {
                Base.println("Sorting : " + Arrays.toString(origin));
            }
            iSort.sort(a);
            if (!SortUtil.checkSorted(a)) {
                Base.println("your sort has failed");
                Base.println("failed at " + i + " testcase : ");
                Base.printArr(origin);
                Base.println("your is : ");
                Base.printArr(a);
                Base.println("correct is : ");
                Arrays.sort(a);
                Base.printArr(a);
                return;
            }
        }
        Base.println("congratulations your sort has passed.");
        if (printTimeUsed) {
            Base.println("Print time used : " + cpu.elapsedTime2());
        }
    }

    public static void testSortUniformDistribution(ISortDouble iSort, double lo, double hi, int len, int size) {
        final boolean debug = false;
        List<double[]> l = new LinkedList<>();
        l.addAll(ArraysUtil.generateRandomUniform(lo, hi, len, size));
        if (debug) {
            iSort.sort(l.get(0));
            return;
        }
        for (double[] a : l) {
            double[] origin = a.clone();
            iSort.sort(a);
            if (!SortUtil.checkSorted(a)) {
                Base.println("your sort has failed");
                Base.println("failed at : ");
                Base.printArr(origin);
                Base.println("your is : ");
                Base.printArr(a);
                Base.println("correct is : ");
                Arrays.sort(a);
                Base.printArr(a);
                return;
            }
        }
        Base.println("congratulations your sort has passed.");
    }
}
