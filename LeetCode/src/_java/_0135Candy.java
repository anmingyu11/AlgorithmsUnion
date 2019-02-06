package _java;

import java.util.Arrays;

import base.Base;

public class _0135Candy extends Base {
    private abstract static class Solution {
        public abstract int candy(int[] ratings);
    }

    // 莫名其妙的做出来了 100% beats
    private static class Solution1 extends Solution {

        @Override
        public int candy(int[] ratings) {
            final int len = ratings.length;
            if (len <= 1) {
                return len;
            }
            if (len == 2) {
                return ratings[0] == ratings[1] ? 2 : 3;
            }

            int[] candy = new int[len];
            if (ratings[0] > ratings[1]) {
                candy[0] = 2;
                candy[1] = 1;
            } else if (ratings[0] < ratings[1]) {
                candy[0] = 1;
                candy[1] = 2;
            } else {
                candy[1] = 1;
                candy[0] = 1;
            }

            for (int i = 2; i < len; ++i) {
                candy[i] = 1;
                if (ratings[i] > ratings[i - 1]) {
                    candy[i] = candy[i - 1] + 1;
                }
            }
            int sum = 0;
            for (int i = len - 1; i > 0; --i) {
                if (ratings[i - 1] > ratings[i] && candy[i - 1] <= candy[i]) {
                    candy[i - 1] = candy[i] + 1;
                }
                sum += candy[i];
            }
            sum += candy[0];

            return sum;
        }

    }

    // 双数组
    // Runtime: 4 ms, faster than 29.89% of Java online submissions for Candy.
    // Memory Usage: 25.4 MB, less than 61.23%
    private static class Solution2 extends Solution {

        public int candy(int[] ratings) {
            final int len = ratings.length;
            int[] lr = new int[len], rl = new int[len];
            Arrays.fill(lr, 1);
            Arrays.fill(rl, 1);

            for (int i = 1; i < len; ++i) {
                if (ratings[i] > ratings[i - 1]) {
                    lr[i] = lr[i - 1] + 1;
                }
            }
            for (int i = len - 1; i > 0; --i) {
                if (ratings[i - 1] > ratings[i]) {
                    rl[i - 1] = rl[i] + 1;
                }
            }

            int sum = 0;
            for (int i = 0; i < len; ++i) {
                sum += Math.max(lr[i], rl[i]);
            }
            return sum;
        }

    }

    // 一次遍历 算坑,很值得学,判断坑的方法简单而又粗暴, 没有细想就缴枪了, 这段代码是有错误的,没太想明白. 以后慢慢体会吧
    private static class Solution3 extends Solution {

        private int count(int n) {
            return n * (n + 1) / 2;
        }

        public int candy(int[] ratings) {
            final int len = ratings.length;
            if (len < 2) {
                return len;
            }

            int up = 0, down = 0, oldSlope = 0;
            int candies = len;

            for (int i = 0; i < len - 1; ++i) {
                int slope = Integer.compare(ratings[i + 1], ratings[i]);

                // 算坡
                // 1. oldSlope < 0 && slope == 0 那么下一个对应的candy一定是1, 直接算坡就行了
                // 2. oldSlope < 0 && slope > 0 坑
                if (((oldSlope < 0 && slope == 0) || (oldSlope < 0 && slope >= 0))) {
                    candies += count(up) + count(down);
                    up = 0;
                    down = 0;
                }

                if (slope > 0) {
                    ++up;
                } else if (slope < 0) {
                    ++down;
                }

                oldSlope = slope;
            }
            candies += count(up) + count(down);
            return candies;
        }
    }

    public static void main(String[] args) {
        int[] set1 = {1, 0, 2};
        int[] set2 = {1, 2, 2};
        int[] set3 = {1, 0, 2, 0, 3, 5, 7, 2, 6};
        int[] set4 = {1, 2, 3, 4, 5};
        int[] set5 = {5, 4, 3, 2, 1};
        int[] set6 = {1, 1, 1, 5, 4, 3, 2, 1};

        Solution s = new Solution3();

        println(s.candy(set3)); // 18
        println(s.candy(set6)); // 18
        println(s.candy(set5)); // 15
        println(s.candy(set1)); // 5
        println(s.candy(set2)); // 4
        println(s.candy(set4)); // 15

    }

}
