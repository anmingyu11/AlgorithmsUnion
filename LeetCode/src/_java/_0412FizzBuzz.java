package _java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import base.Base;

/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 * But for multiples of three it should output “Fizz”
 * instead of the number and for the multiples of five output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class _0412FizzBuzz extends Base {

    private abstract static class Solution {
        public abstract List<String> fizzBuzz(int n);
    }

    // Runtime: 1 ms, faster than 100.00% of Java online submissions for Fizz Buzz.
    // Memory Usage: 38.7 MB, less than 11.39% of Java online submissions for Fizz Buzz.
    private static class Solution1 extends Solution {

        @Override
        public List<String> fizzBuzz(int n) {
            List<String> res = new LinkedList<>();
            if (n < 1) {
                return res;
            }
            for (int i = 1; i <= n; ++i) {
                if (i % 5 == 0) {
                    if (i % 3 == 0) {
                        // both 5 , 3
                        res.add("FizzBuzz");
                    } else {
                        // only 5
                        res.add("Buzz");
                    }
                } else if (i % 3 == 0) {
                    // only 3
                    res.add("Fizz");
                } else {
                    res.add(String.valueOf(i));
                }
            }
            return res;
        }

    }

    // HashMap, 写法值得借鉴
    // Runtime: 5 ms, faster than 5.37% of Java online submissions for Fizz Buzz.
    // Memory Usage: 38.7 MB, less than 12.03% of Java online submissions for Fizz Buzz.
    private static class Solution2 extends Solution {

        public List<String> fizzBuzz(int n) {
            List<String> ans = new LinkedList<>();
            HashMap<Integer, String> fizzBizzDict =
                    new HashMap<Integer, String>() {
                        {
                            put(3, "Fizz");
                            put(5, "Buzz");
                        }
                    };
            for (int i = 1; i <= n; i++) {
                StringBuilder sb = new StringBuilder();
                for (Integer key : fizzBizzDict.keySet()) {
                    if (i % key == 0) {
                        sb.append(fizzBizzDict.get(key));
                    }
                }
                if (sb.length() == 0) {
                    sb.append(i);
                }
                ans.add(sb.toString());
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.fizzBuzz(15));
    }
}
