package base.util;

import java.util.Arrays;

import base.stdlib.StdOut;

public class PrintUtil {

    public static void print2DArr(int[][] arr2) {
        for (int[] objarr : arr2) {
            for (int i = 0; i < objarr.length; ++i) {
                int val = objarr[i];
                if (i == 0) {
                    if (i != objarr.length - 1) {
                        StdOut.printf("[ %8d,", val);
                    } else {
                        StdOut.printf("[ %8d", val);
                        StdOut.printf(" ]\n");
                    }
                } else if (i == objarr.length - 1) {
                    StdOut.printf(" %8d]\n", val);
                } else {
                    StdOut.printf(" %8d,", val);
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
                    StdOut.printf("[ %d,", val);
                } else if (i == objarr.length - 1) {
                    StdOut.printf("%7d ]\n", val);
                } else {
                    StdOut.printf(" %7d,", val);
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
                        StdOut.printf("[ %8.2f,", val);
                    } else {
                        StdOut.printf("[ %8.2f", val);
                        StdOut.printf(" ]\n");
                    }
                } else if (i == objarr.length - 1) {
                    StdOut.printf(" %8.2f]\n", val);
                } else {
                    StdOut.printf(" %8.2f,", val);
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

    public static void printArr(double[] arr) {
        println(Arrays.toString(arr));
    }

    public static void printArr(int[] arr) {
        println(Arrays.toString(arr));
    }

    public static void printArr(long[] arr) {
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
        StdOut.print(o);
    }

    public static void printf(String format, Object... args) {
        StdOut.printf(format, args);
    }

    public static void println(Object o) {
        StdOut.println(o);
    }

    public static void printWhitespaces(int count) {
        StdOut.println(StringUtil.spaces(count));
    }

    public static void err(Object o) {
        System.err.println(o);
        System.err.flush();
    }

}
