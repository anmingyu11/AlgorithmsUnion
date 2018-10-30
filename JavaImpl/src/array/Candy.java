package array;

import java.util.Arrays;

import base.Base;

public class Candy extends Base {

    public static int candy1(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int i = 0; i < candies.length; ++i) {
                if (i != candies.length - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    flag = true;
                }
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    flag = true;
                }
            }
        }

        int sum = 0;
        for (int c : candies) {
            sum += c;
        }
        return sum;
    }

    public static int candy2(int[] ratings) {
        final int n = ratings.length;
        int[] left2right = new int[n];
        int[] right2left = new int[n];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < left2right.length; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = right2left.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += Math.max(right2left[i], left2right[i]);
        }
        return sum;
    }

    public static int candy3(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int sum = candies[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            sum += candies[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        //println(candy1(new int[]{1, 0, 2}));
        //println(candy2(new int[]{1, 0, 2}));
        println(candy3(new int[]{1, 0, 2}));
    }

}
