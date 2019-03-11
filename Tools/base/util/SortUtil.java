package base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import base.Base;
import base.interfaces.ISort;
import base.interfaces.ISortDouble;

public class SortUtil {

    public static void testSort(ISort iSort) {
        testSort(iSort, true, 20);
    }

    public static void testSort(ISort iSort, int randomLen) {
        testSort(iSort, true, randomLen);
    }

    public static void testSort(ISort iSort, boolean addNeg) {
        testSort(iSort, addNeg, 20);
    }

    public static void testSort(ISort iSort, boolean addNeg, int randomArrLen) {
        final boolean debug = false;
        // hiahia
        List<int[]> perms = PermutationUtil.permutations(5);
        addRest(perms, addNeg);
        addRandom(perms, addNeg, randomArrLen);
        Base.println("Sort Test Start , number of array is : " + perms.size());
        for (int[] a : perms) {
            int[] origin = a.clone();
            if (debug) {
                Base.println("Sorting : " + Arrays.toString(origin));
            }
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

    public static void testSortUniformDistribution(ISortDouble iSort, double lo, double hi, int len, int size) {
        final boolean debug = false;
        List<double[]> l = new LinkedList<>();
        addRandomUniform(l, lo, hi, len, size);
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
                return;
            }
        }
        Base.println("congratulations your sort has passed.");
    }

    private static void addRandomUniform(List<double[]> l, double lo, double hi, int len, int size) {
        final boolean debug = false;
        Random r = new Random();
        double[] arr = new double[len];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < len; ++j) {
                // The general contract of nextDouble is that one double value,
                // chosen (approximately) uniformly from the range 0.0d (inclusive) to
                // 1.0d (exclusive), is pseudorandomly generated and returned.
                arr[j] = lo + r.nextDouble() * (hi - lo);
                arr[j] = BigDecimal.valueOf(arr[j]).setScale(3, RoundingMode.FLOOR).doubleValue();
            }
            l.add(arr.clone());
        }
        if (!debug) {
            return;
        }
        for (double[] a : l) {
            Base.printArr(a);
        }
    }

    private static void addRandom(List<int[]> l, boolean addNeg, int n) {
        // 正数
        final int posSize = n * 3;
        // 负数
        final int negSize = posSize * 3;
        // 混合
        final int mixSize = posSize;
        final int bound = 5;
        int[] arr = new int[n];
        for (int sz = 0; sz < posSize; ++sz) {
            Random r = new Random();
            for (int i = 0; i < n; ++i) {
                arr[i] = r.nextInt(bound);
            }
            l.add(arr.clone());
        }
        if (!addNeg) {
            return;
        }
        for (int sz = 0; sz < negSize; ++sz) {
            Random r = new Random();
            for (int i = 0; i < n; ++i) {
                arr[i] = (-1) * r.nextInt(bound);
            }
            l.add(arr.clone());
        }
        for (int sz = 0; sz < mixSize; ++sz) {
            Random r = new Random();
            int flag = 1;
            for (int i = 0; i < n; ++i) {
                double d = r.nextDouble();
                flag = r.nextDouble() < 0.5 ? -1 : 1;
                arr[i] = flag * r.nextInt(bound);
            }
            l.add(arr.clone());
        }
    }

    private static void addRest(List<int[]> l, boolean addNeg) {
        l.add(new int[]{99, 99});
        l.add(new int[]{1});
        if (!addNeg) {
            return;
        }
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

    public static boolean checkSorted(double[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
