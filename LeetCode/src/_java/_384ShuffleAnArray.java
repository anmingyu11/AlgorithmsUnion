package _java;

import java.util.Random;

import base.Base;

//Simulation
public class _384ShuffleAnArray extends Base {

    abstract static class Solution {

        public Solution() {
        }

        protected Solution(int[] nums) {
        }

        abstract public int[] reset();

        abstract public int[] shuffle();
    }

    static class Solution1 extends Solution {
        int[] origin;
        int[] shuffle;

        public Solution1(int[] nums) {
            shuffle = nums;
            origin = nums.clone();
        }

        public int[] reset() {
            //这里也是一个点。
            shuffle = origin;
            origin = origin.clone();
            return origin;
        }

        public int[] shuffle() {
            Random ra = new Random();
            for (int i = 0; i < shuffle.length; ++i) {
                //这里非常重要,避免重复 一定是i+1,这是一个全排列，n!
                int raI = (int) (ra.nextDouble() * (i + 1));
                int t = shuffle[i];
                shuffle[i] = shuffle[raI];
                shuffle[raI] = t;
            }
            return shuffle;
        }

        private void swap(int i, int j) {
            int t = shuffle[i];
            shuffle[i] = shuffle[j];
            shuffle[j] = t;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4, 5};
        int[] nums2 = new int[]{};
        int[] nums = nums1;
        testOfRandom();
        // Solution1 so = new Solution1(nums1);
        // printArr(nums);
        // so.shuffle();
        // printArr(nums);
        // so.reset();
        // printArr(nums);
    }

    private static void testOfRandom() {
        Random r = new Random();
        for (int i = 1; i < 10; ++i) {
            double d = r.nextDouble();
            int ip1 = i + 1;
            print(" double : " + d + " ip1 : " + ip1);
            println(" res : " + (int) (d * (ip1)));
        }
    }
}
