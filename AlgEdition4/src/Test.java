import base.stdlib.StdOut;

public class Test {

    public static void main(String[] args) {
        //ArrayTest();
    }

    private static void DrawTest() {

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
