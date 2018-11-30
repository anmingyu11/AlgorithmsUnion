package base;

import java.util.LinkedList;
import java.util.List;

public class Tests extends Base {
    public static void main(String[] args) {
        //linkedListInsertDeleteTest();
        testBitMani();
    }

    private static void testBitMani() {
        println("and");
        println(1 & 1);
        println(1 & 0);
        println(0 & 1);
        println(0 & 0);
        println("or");
        println(1 | 1);
        println(1 | 0);
        println(0 | 1);
        println(0 | 0);
        println("xor");
        println(1 ^ 1);
        println(1 ^ 0);
        println(0 ^ 1);
        println(0 ^ 0);
    }

    private static void linkedListInsertDeleteTest() {
        List<Integer> list = new LinkedList<>();
        list.add(0, 1);
        list.add(0, 2);
        list.add(0, 3);
        println(list);

        list.remove(0);
        println(list);
    }
}
