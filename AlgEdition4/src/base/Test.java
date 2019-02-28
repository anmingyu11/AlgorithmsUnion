package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import base.stdlib.StdOut;

public class Test {

    public static void main(String[] args) {
        genRandomArray();
    }

    private static void DrawTest() {
    }

    private static void genRandomArray() {
        List<Integer> l = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; ++i) {
            int num = r.nextInt(15);
            l.add(num);
        }
        StdOut.println(l);
    }

    private static void ByteTest() {
        StdOut.println("BYTE MAX : " + Byte.MAX_VALUE + " BYTE MAX : " + Byte.MIN_VALUE);
        Byte b = (byte) (Byte.MAX_VALUE + (byte) 3);
        StdOut.println("" + b);
    }

    private static void ArrayTest() {
        double[] da = new double[10];
        for (double d : da) {
            StdOut.println(" : " + d);
        }
    }
}
