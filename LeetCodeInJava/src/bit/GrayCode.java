package bit;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class GrayCode extends Base {

    static class Solution {

        public List<Integer> grayCode(int n) {
            final int size = 1 << n;  // 2^n
            List<Integer> result = new LinkedList<>();

            for (int i = 0; i < size; ++i) {
                result.add(binary_to_gray(i));
            }

            return result;
        }

        private int binary_to_gray(int n) {
            return n ^ (n >> 1);
        }

    }

    public static void main(String[] args) {

    }
}
