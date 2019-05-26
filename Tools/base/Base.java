package base;

import base.util.PrintUtil;

public class Base {

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void swap(int[][] nums, int ai, int aj, int bi, int bj) {
        int tmp = nums[bi][bj];
        nums[bi][bj] = nums[ai][aj];
        nums[ai][aj] = tmp;
    }

    public static void swap(double[][] nums, int i, int j) {
        double[] tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void reverse(int nums[], int begin, int end) {
        while (begin < end) {
            swap(nums, begin++, end--);
        }
    }

    public static void print2DArr(int[][] arr2) {
        PrintUtil.print2DArr(arr2);
    }

    public static void print2DArr(char[][] arr2) {
        PrintUtil.print2DArr(arr2);
    }

    public static void print2DArr(float[][] arr2) {
        PrintUtil.print2DArr(arr2);
    }

    public static void print2DArr(double[][] arr2) {
        PrintUtil.print2DArr(arr2);
    }

    public static <T> void printArr(int[][] arr2) {
        PrintUtil.print2DArr(arr2);
    }

    public static <T> void printArr(char[][] arr2) {
        PrintUtil.print2DArr(arr2);
    }

    public static void printArr(char[] arr1) {
        PrintUtil.printArr(arr1);
    }

    public static void printArr(double[] arr1) {
        PrintUtil.printArr(arr1);
    }

    public static void printArr(int[] arr) {
        PrintUtil.printArr(arr);
    }

    public static void printArr(long[] arr) {
        PrintUtil.printArr(arr);
    }

    public static <T> void printArr(boolean[] arr) {
        PrintUtil.printArr(arr);
    }

    public static <T> void printArr(T[] arr) {
        PrintUtil.printArr(arr);
    }

    public static <T> void printArr(T[][] arr2) {
        PrintUtil.printArr(arr2);
    }

    public static void print(Object o) {
        PrintUtil.print(o);
    }

    public static void printf(String format, Object... args) {
        PrintUtil.printf(format, args);
    }

    public static void println(Object o) {
        PrintUtil.println(o);
    }
}
