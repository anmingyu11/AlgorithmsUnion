package base;

import java.util.LinkedList;
import java.util.List;

public class Tests extends Base {
    public static void main(String[] args) {
        linkedListInsertDeleteTest();
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
