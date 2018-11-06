package array;

import base.Base;

public class IncreasingTripletSubsequence extends Base {

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first) {
                first = n;
            } else if (n <= second) {//n > first <= second
                second = n;
            } else { // n > second
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
