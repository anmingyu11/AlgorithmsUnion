package base;

import java.util.Arrays;

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
        for (int[] objarr : arr2) {
            for (int i = 0; i < objarr.length; ++i) {
                int val = objarr[i];
                if (i == 0) {
                    if (i != objarr.length - 1) {
                        System.out.printf("[ %8d,", val);
                    } else {
                        System.out.printf("[ %8d", val);
                        System.out.printf(" ]\n");
                    }
                } else if (i == objarr.length - 1) {
                    System.out.printf(" %8d]\n", val);
                } else {
                    System.out.printf(" %8d,", val);
                }
            }
        }
        println("");
    }

    public static void print2DArr(char[][] arr2) {
        for (char[] objarr : arr2) {
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

    public static void print2DArr(double[][] arr2) {
        for (double[] objarr : arr2) {
            for (int i = 0; i < objarr.length; ++i) {
                double val = objarr[i];
                if (i == 0) {
                    if (i != objarr.length - 1) {
                        System.out.printf("[ %8.2f,", val);
                    } else {
                        System.out.printf("[ %8.2f", val);
                        System.out.printf(" ]\n");
                    }
                } else if (i == objarr.length - 1) {
                    System.out.printf(" %8.2f]\n", val);
                } else {
                    System.out.printf(" %8.2f,", val);
                }
            }
        }
        println("");
    }

    public static <T> void printArr(int[][] arr2) {
        for (int[] objarr : arr2) {
            println(Arrays.toString(objarr));
        }
        println("");
    }

    public static <T> void printArr(char[][] arr2) {
        for (char[] objarr : arr2) {
            println(Arrays.toString(objarr));
        }
        println("");
    }

    public static void printArr(char[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static void printArr(int[] arr) {
        println(Arrays.toString(arr));
    }

    public static <T> void printArr(T[] arr1) {
        println(Arrays.toString(arr1));
    }

    public static <T> void printArr(T[][] arr2) {
        for (T[] objarr : arr2) {
            println(Arrays.toString(objarr));
        }
        println("");
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void println(Object o) {
        System.out.println(o);
    }
}
