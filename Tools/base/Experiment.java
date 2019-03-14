package base;

public class Experiment extends Base {

    public static void main(String[] args) {
        //testShiftLeft();
    }

    private static void testShiftLeft() {
        println(1 << 8);
        println(Integer.toBinaryString(1 << 8));
    }
}
