package dp;

import java.util.ArrayList;
import java.util.List;

import base.Base;

public class Triangle extends Base {

    //自底向上
    static class Solution1 {
        public static int minimumTotal(List<List<Integer>> triangle) {
            final int len = triangle.size();
            for (int lay = len - 2; lay >= 0; --lay) {
                for (int i = 0; i < lay + 1; ++i) {
                    int old = triangle.get(lay).get(i);
                    triangle.get(lay).set(i,
                            old + Math.min(
                                    triangle.get(lay + 1).get(i),
                                    triangle.get(lay + 1).get(i + 1)));
                }
            }

            return triangle.get(0).get(0);
        }
    }

    //自底向上,带记忆数组，不破坏原有结构，Space复杂度为O(n)
    static class Solution2 {
        public static int minimumTotal(List<List<Integer>> triangle) {
            final int len = triangle.size();
            int[] min = new int[len + 1];
            for (int lay = len - 1; lay >= 0; --lay) {
                for (int i = 0; i < lay + 1; ++i) {
                    min[i] = Math.min(min[i], min[i + 1]) + triangle.get(lay).get(i);
                }
            }

            return min[0];
        }

    }

    public static List<List<Integer>> getTriangle() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        List<Integer> r3 = new ArrayList<>();
        List<Integer> r4 = new ArrayList<>();
        r1.add(2);
        r2.add(3);
        r2.add(4);
        r3.add(6);
        r3.add(5);
        r3.add(7);
        r4.add(4);
        r4.add(1);
        r4.add(8);
        r4.add(3);
        triangle.add(r1);
        triangle.add(r2);
        triangle.add(r3);
        triangle.add(r4);
        return triangle;
    }

    public static void testSolution1() {
        List<List<Integer>> triangle = getTriangle();
        println(triangle);
        println(Solution1.minimumTotal(triangle));
        println(triangle);
    }

    public static void testSolution2() {
        List<List<Integer>> triangle = getTriangle();
        println(triangle);
        println(Solution2.minimumTotal(triangle));
        println(triangle);
    }

    public static void main(String[] args) {
        testSolution1();
        println("--------------");
        testSolution2();
        println("--------------");
    }
}
