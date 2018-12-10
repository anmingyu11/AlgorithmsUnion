package base;

import java.util.Arrays;

public class Base {
    public static void swap(int nums[], int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void swap(int nums[][], int ai, int aj, int bi, int bj) {
        int tmp = nums[bi][bj];
        nums[bi][bj] = nums[ai][aj];
        nums[ai][aj] = tmp;
    }

    public static void reverse(int nums[], int begin, int end) {
        while (begin < end) {
            swap(nums, begin++, end--);
        }
    }

    public static <T> void print(int[][] arr2) {
        for (int[] objarr : arr2) {
            println(Arrays.toString(objarr));
        }
        println("");
    }

    public static <T> void print(char[][] arr2) {
        for (char[] objarr : arr2) {
            println(Arrays.toString(objarr));
        }
        println("");
    }


    public static void print(int[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static void print(char[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static <T> void print(T[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static <T> void print(T[][] arr2) {
        for (T[] objarr : arr2) {
            println(Arrays.toString(objarr));
        }
        println("");
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }
}
