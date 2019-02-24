package _java;

import base.Base;

public class _0312BurstBalloons______________ extends Base {

    private abstract static class Solution {
        public abstract int maxCoins(int[] nums);
    }

    private static class Solution1 extends Solution {

        @Override
        public int maxCoins(int[] ballons) {
            final int n = ballons.length;
            int max = Integer.MIN_VALUE;
            boolean[] bursted = new boolean[n];
            for (int i = 0; i < n; ++i) {
                int cq = conquer(ballons, bursted, 0, n - 1, i);
                max = Math.max(cq, max);
            }
            return max;
        }

        private int conquer(int[] balloons, boolean[] bursted, int lo, int hi, int burst) {
            bursted[burst] = true;
            // 选左右
            int leftV, rightV;

            return 0;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 8};

        Solution s = new Solution1();

        println(s.maxCoins(arr));
    }
}
