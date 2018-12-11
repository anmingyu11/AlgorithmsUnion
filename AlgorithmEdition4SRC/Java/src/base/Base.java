package base;

import base.stdlib.StdOut;

public class Base {

    public static void print(Object o) {
        StdOut.print(o);
    }

    public static void println(Object o) {
        StdOut.println(o);
    }

    public static Integer[] generateArrSequence(int min, int max) {
        return NumberGenerator.generateArrSequence(min, max);
    }

    public static Integer[] generateArrRandom(int len, int min, int max) {
        return NumberGenerator.generateArrRandom(len, min, max);
    }

}
