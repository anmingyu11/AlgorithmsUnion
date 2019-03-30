package base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import base.Base;

public class ArraysUtil {

    public static int max(int[] A) {
        final int n = A.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= n / 2; ++i) {
            max = Math.max(max, Math.max(A[i], A[n - 1 - i]));
        }
        return max;
    }

    public static int min(int[] A) {
        final int n = A.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n / 2; ++i) {
            min = Math.min(min, Math.min(A[i], A[n - 1 - i]));
        }
        return min;
    }

    public static boolean equal(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param addNeg
     * @param arrLen 随机数组的长度, 总共生成的数组数量为: 正数arrLen * 3, 负数为 arrLen * 3, 正负混合为 arrLen * 3
     * @return
     */
    public static List<int[]> generateRandom(boolean addNeg, int arrLen) {
        return generateRandom(addNeg, arrLen, Integer.MAX_VALUE);
    }

    /**
     * @param addNeg
     * @param arrLen 随机数组的长度, 总共生成的数组数量为: 正数arrLen * 3, 负数为 arrLen * 3, 正负混合为 arrLen * 3
     * @param bound
     * @return
     */
    public static List<int[]> generateRandom(boolean addNeg, int arrLen, int bound) {
        List<int[]> l = new LinkedList<>();
        // 正数
        final int posSize = arrLen * 3;
        // 负数
        final int negSize = arrLen * 3;
        // 混合
        final int mixSize = arrLen * 6;
        int[] arr = new int[arrLen];
        for (int sz = 0; sz < posSize; ++sz) {
            Random r = new Random();
            for (int i = 0; i < arrLen; ++i) {
                arr[i] = r.nextInt(bound);
            }
            l.add(arr.clone());
        }
        if (!addNeg) {
            return l;
        }
        for (int sz = 0; sz < negSize; ++sz) {
            Random r = new Random();
            for (int i = 0; i < arrLen; ++i) {
                arr[i] = (-1) * r.nextInt(bound);
            }
            l.add(arr.clone());
        }
        for (int sz = 0; sz < mixSize; ++sz) {
            Random r = new Random();
            int flag = 1;
            for (int i = 0; i < arrLen; ++i) {
                double d = r.nextDouble();
                flag = r.nextDouble() < 0.5 ? -1 : 1;
                arr[i] = flag * r.nextInt(bound);
            }
            l.add(arr.clone());
        }
        return l;
    }

    public static List<int[]> generateSpecial(boolean addNeg) {
        List<int[]> l = new LinkedList<>();
        l.add(new int[]{});
        l.add(new int[]{99, 99});
        l.add(new int[]{1});
        l.add(new int[]{1, 12, 123, 1234});
        if (!addNeg) {
            return l;
        }
        l.add(new int[]{-1, 2, 0});
        l.add(new int[]{-1, 1, 1, 1, 1, 1, 1});
        l.add(new int[]{-1, 1, -1, 1, -2, 1, 1, -1, 0});
        l.add(new int[]{-1, 0, 0, 0, 0, 0, 0, -1, -2, 1, 1, 0, 0, 0, 1});
        return l;
    }

    public static List<double[]> generateRandomUniform(double lo, double hi, int len, int size) {
        List<double[]> l = new LinkedList<>();
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
            return l;
        }
        for (double[] a : l) {
            Base.printArr(a);
        }
        return l;
    }

    public static List<int[]> generatePermutation(int n) {
        return PermutationUtil.permutations(n);
    }

    public static int[] generateFromTo(int lo, int hi) {
        int[] a = new int[hi - lo + 1];
        int j = 0;
        for (int i = lo; i <= hi; ++i) {
            a[j++] = i;
        }
        return a;
    }

}
