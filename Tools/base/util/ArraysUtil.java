package base.util;

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

}
