package _16Greedy;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class ActivitySelector extends Base {

    private abstract static class Solution {
        // 虚拟活动a0 ,开始时间0,结束时间0
        final int[] s = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        final int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
        final int n = s.length;
        final int[] S = new int[n];

        {
            for (int i = 1; i < n; ++i) {
                S[i] = i;
            }
        }

        public abstract List<Integer> activitySelect();
    }

    private static class Checker extends Solution {

        @Override
        public List<Integer> activitySelect() {
            return null;
        }
    }

    /**
     * 递归求解
     */
    private static class Solution1 extends Solution {

        @Override
        public List<Integer> activitySelect() {
            List<Integer> activities = new LinkedList<>();
            activitySelectAux(activities, 0);
            return activities;
        }

        public void activitySelectAux(List<Integer> list, int k) {
            int m = k + 1;
            while (m < n && s[m] < f[k]) {
                ++m;
            }
            if (m < n) {
                list.add(S[m]);
                activitySelectAux(list, m);
                return;
            } else {
                return;
            }
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public List<Integer> activitySelect() {
            LinkedList<Integer> A = new LinkedList<>();
            A.add(1);
            int k = 1;
            for (int i = 2; i < n; ++i) {
                if (s[i] >= f[k]) {
                    A.add(i);
                    k = i;
                }
            }
            return A;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        println(s.activitySelect()); // 1,4,8,11
    }

}
