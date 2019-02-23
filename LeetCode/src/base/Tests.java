package base;

import java.util.LinkedList;
import java.util.List;

public class Tests extends Base {
    public static void main(String[] args) {
        //linkedListInsertDeleteTest();
        //testBitMani();
        //testStrEmpty();
        //testSubStr();
        //testZero();
        testBits();
        diffBetween(
                "98909827968595339456944893859149094902689398937839883538183810810780707982784676057536747174237321720571007032685668066758674466986636554651163276306626562416221603859725909578457125682552954605422520849804812479847044453428339323905384638363699366436503636357535673516346233993298316330843021297028227452732697246523622362231322281216213206020001921763154815181495141713801147114310901048",
                "98909827968595339456944893859149094902689398937839883538183810810780707982784676057536747174237321720571007032685668066758674466986636554651163276306626562416221603859725909578457125682552954605422520849804812479847044453428339323905384638363699366436503636357535673516346233993298316330843021297028227452732697246523622362231322812216213206020001921763154815181495141713801147114310901048"
        );
    }

    private static void diffBetween(String a, String b) {
        final int n = a.length();
        int diff = 0;
        for (int i = 0; i < n; ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                diff = i;
            }
        }
        println(a.substring(0, diff));
        println(b.substring(0, diff));
    }

    private static void testBits() {
        int i = Integer.MAX_VALUE;
        int i2 = 123;

        println(Integer.bitCount(i));
        println(Integer.bitCount(i2));
    }

    private static void testZero() {
        println(Integer.toBinaryString(-0));
        println(Integer.toBinaryString(0));
    }

    private static void testStrEmpty() {
        String s = "";
        println(s.length());
    }

    private static void testSubStr() {
        String s = "012";
        //println(s.substring(3, s.length()));
        println(Integer.parseInt(s));
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
        List<Integer> list = new LinkedList<Integer>();
        list.add(0, 1);
        list.add(0, 2);
        list.add(0, 3);
        println(list);

        list.remove(0);
        println(list);
    }
}
