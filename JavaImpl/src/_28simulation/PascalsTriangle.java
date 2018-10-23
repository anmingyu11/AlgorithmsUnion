package _28simulation;

import java.util.ArrayList;
import java.util.List;

import _00base.Base;

public class PascalsTriangle extends Base {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allrows = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i < numRows; ++i) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; ++j) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            allrows.add(new ArrayList<>(row));
        }

        return allrows;
    }

    public static void main(String[] args) {
        List<List<Integer>> all = generate(5);
        println(all);
    }
}
