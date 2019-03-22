package base.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.Base;
import base.StopwatchCPU;
import base.interfaces.ISort;
import base.interfaces.ISortDouble;

public class SortUtil {

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
            if (!checkSorted(a)) {
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
            if (!checkSorted(a)) {
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

    public static boolean checkSorted(int[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSorted(double[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
