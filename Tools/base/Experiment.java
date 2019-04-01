package base;

import java.util.Arrays;

public class Experiment extends Base {

    public static void main(String[] args) {
        //testShiftLeft();
        //testIntDivid();
        //testShiftRight();
        //testBinarySearch();
        testErrorPrint();
    }

    private static void testErrorPrint(){
        System.err.println("gogoo");
    }

    private static void testBinarySearch() {
        int[] t = new int[]{1, 3, 4, 5};
        int[] target = {1, -1, 0, 2, 6};
        printArr(t);
        for (int tt : target) {
            println(" target : " + tt + " index : " + Arrays.binarySearch(t, tt));
        }
        // 应该插入的位置在 binarySearch() - 1
    }

    private static void testIntDivid() {
        println(-11 / 10 - 1);
    }

    private static void testShiftLeft() {
        println(1 << 8);
        println(Integer.toBinaryString(1 << 8));
    }

    private static void testShiftRight() {
        println(3 >> 1);
        println(4 >> 1);
        println(6 >> 1);
        println(10 >> 1);
        println(-10 >> 1);
    }
}
