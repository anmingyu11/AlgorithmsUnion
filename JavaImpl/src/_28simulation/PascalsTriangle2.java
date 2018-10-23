package _28simulation;

import java.util.ArrayList;
import java.util.List;

import _00base.Base;

public class PascalsTriangle2 extends Base {

    public static List<Integer> getRow(int rowIndex) {

        List<Integer> result = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i < rowIndex + 1; ++i) {
            result.add(1);
            for (int j = i - 1; j > 0; --j) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
    }
}
