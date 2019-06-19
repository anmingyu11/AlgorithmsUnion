package _08SortingInLinearTime;

import base.interfaces.ISort;
import base.util.ArraysUtil;
import base.util.TestUtil;

/**
 * 计数排序(CountingSort):
 */
public class CountingSort implements ISort {

    @Override
    public void sort(int[] A) {
        if (ArraysUtil.min(A) < 0) {
            throw new IllegalArgumentException("The element of array can not be negative.");
        }
        int max = ArraysUtil.max(A);
        final int n = A.length;
        int[] B = new int[n];
        int[] C = new int[max + 1];
        for (int i = 0; i < n; ++i) {
            ++C[A[i]];
        }
        for (int i = 1; i < max + 1; ++i) {
            C[i] += C[i - 1];
        }
        for (int i = n - 1; i >= 0; --i) {
            B[C[A[i]] - 1] = A[i];
            --C[A[i]];
        }
        for (int i = 0; i < n; ++i) {
            A[i] = B[i];
        }
    }

    public static void main(String[] args) {
        TestUtil.testSort(new CountingSort(), false);
    }

}