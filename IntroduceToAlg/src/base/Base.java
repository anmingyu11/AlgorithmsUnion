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

    public static void print2DArr(int[][] arr2) {
        for (int[] objarr : arr2) {
            for (int i = 0; i < objarr.length; ++i) {
                int val = objarr[i];
                if (i == 0) {
                    System.out.printf("[ %d,", val);
                } else if (i == objarr.length - 1) {
                    System.out.printf("%7d ]\n", val);
                } else {
                    System.out.printf(" %7d,", val);
                }
            }
        }
        println("");
    }

    public static void print2DArr(char[][] arr2) {
        for (char[] objarr : arr2) {
            for (int i = 0; i < objarr.length; ++i) {
                char ch = objarr[i];
                if (i == 0) {
                    System.out.printf("[ %c,", ch);
                } else if (i == objarr.length - 1) {
                    System.out.printf("%5c ]\n", ch);
                } else {
                    System.out.printf(" %5c,", ch);
                }
            }
        }
        println("");
    }

    public static <T> void print2DArr(T[][] arr2) {
        for (T[] objarr : arr2) {
            println(Arrays.toString(objarr));
        }
        println("");
    }


    public static void printLiArr(int[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static void printLiArr(char[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static <T> void printLiArr(T[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }
}
