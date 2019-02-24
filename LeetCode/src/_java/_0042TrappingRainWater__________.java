package _java;

import java.util.Stack;

import base.Base;

public class _0042TrappingRainWater__________ extends Base {
    private abstract static class Solution {
        public abstract int trap(int[] height);
    }

    // 暴力 105 ms 6%
    private static class Solution1 extends Solution {

        public int trap(int[] height) {
            int trap = 0;
            int len = height.length;
            if (len < 3) {
                return trap;
            }
            // 确定左右边界
            int lo = 0, hi = len - 1;
            // 越过左面所有的0
            while (height[lo] == 0) {
                ++lo;
                if (lo + 2 > hi) {
                    return trap;
                }
            }
            // 前面的条件将lo限制到了 lo < hi - 2
            // 越过左面所有的递增区间
            while (height[lo] < height[lo + 1]) {
                ++lo;
                if (lo + 2 > hi) {
                    return trap;
                }
            }
            // 越过右面的0
            while (height[hi] == 0) {
                --hi;
                if (lo + 2 > hi) {
                    return trap;
                }
            }
            // 越过右面的递减区间
            while (height[hi - 1] > height[hi]) {
                --hi;
                if (lo + 2 > hi) {
                    return trap;
                }
            }

            for (int i = lo; i <= hi; ++i) {
                // 没毛病...但是想不通
                int maxLeft = 0, maxRight = 0;
                for (int j = hi; j >= i; --j) {
                    maxLeft = Math.max(maxLeft, height[j]);
                }
                for (int j = lo; j <= i; ++j) {
                    maxRight = Math.max(maxRight, height[j]);
                }
                trap += Math.min(maxLeft, maxRight) - height[i];
            }
            return trap;
        }
    }

    // 动态规划 11ms beats 41% 还是无法掌握其中的奥妙.
    private static class Solution2 extends Solution {

        public int trap(int[] height) {
            int trap = 0;
            int len = height.length;
            if (len < 3) {
                return trap;
            }

            int[] dpL = new int[len];
            int[] dpR = new int[len];

            dpL[0] = height[0];
            for (int i = 1; i < len; ++i) {
                dpL[i] = Math.max(dpL[i - 1], height[i]);
            }
            dpR[len - 1] = height[len - 1];
            for (int i = len - 2; i >= 0; --i) {
                dpR[i] = Math.max(dpR[i + 1], height[i]);
            }

            for (int i = 1; i < len; ++i) {
                trap += Math.min(dpL[i], dpR[i]) - height[i];
            }

            return trap;
        }
    }

    // 双指针 9ms beats98.29%
    private static class Solution3 extends Solution {

        @Override
        public int trap(int[] height) {
            int trap = 0;
            int len = height.length;
            if (len < 3) {
                return trap;
            }
            // 确定左右边界
            int lo = 0, hi = len - 1;
            // 越过左面所有的0
            while (height[lo] == 0) {
                ++lo;
                if (lo + 2 > hi) {
                    return trap;
                }
            }
            // 前面的条件将lo限制到了 lo < hi - 2
            // 越过左面所有的递增区间
            while (height[lo] < height[lo + 1]) {
                ++lo;
                if (lo + 2 > hi) {
                    return trap;
                }
            }
            // 越过右面的0
            while (height[hi] == 0) {
                --hi;
                if (lo + 2 > hi) {
                    return trap;
                }
            }
            // 越过右面的递减区间
            while (height[hi - 1] > height[hi]) {
                --hi;
                if (lo + 2 > hi) {
                    return trap;
                }
            }

            while (lo < hi) {
                int min = Math.min(height[lo], height[hi]);
                if (min == height[lo]) {
                    ++lo;
                    while (lo < hi && height[lo] < min) {
                        trap += min - height[lo];
                        ++lo;
                    }
                } else {
                    --hi;
                    while (lo < hi && height[hi] < min) {
                        trap += min - height[hi];
                        --hi;
                    }
                }
            }

            return trap;
        }

    }

    // 栈 15ms 24% 太难以理解了 以后慢慢体会吧
    private static class Solution4 extends Solution {

        public int trap(int[] height) {
            Stack<Integer> s = new Stack<>();
            int i = 0, len = height.length, trap = 0;
            while (i < len) {
                if (s.empty() || height[i] <= height[s.peek()]) {
                    // 如果栈是空,或者i一直是递减的,那么就压入栈
                    s.push(i++);
                } else {
                    // 说明栈既不是空,也不是递减的.
                    // 弹出栈首元素, 这个可能是坑.
                    int pit = s.pop();
                    if (s.empty()) {
                        continue;
                    }
                    // 左右夹逼,一层一层的算.
                    trap += (Math.min(height[i], height[s.peek()]) - height[pit]) * (i - s.peek() - 1);
                }
            }
            return trap;
        }
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] arr2 = new int[]{5, 2, 1, 2, 1, 5};
        int[] arr3 = new int[]{9, 1, 0};
        int[] arr4 = new int[]{0, 6, 0, 4, 0};

        Solution s = new Solution4();

        println(s.trap(arr1)); // 6
        println(s.trap(arr2)); // 14
        println(s.trap(arr3)); // 0
        println(s.trap(arr4)); // 4

    }
}
