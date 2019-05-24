package _java;

import base.Base;

/**
 * Given n non-negative integers a1, a2, ..., an ,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * <p>
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 */
public class _0011ContainerWithMostWater extends Base {

    private abstract static class Solution {
        public abstract int maxArea(int[] height);
    }

    /**
     * Runtime: 2 ms, faster than 96.85% of Java online submissions for Container With Most Water.
     * Memory Usage: 38.9 MB, less than 98.59% of Java online submissions for Container With Most Water.
     *
     * 用两个指针从两端开始向中间靠拢，如果左端线段短于或等于右端，那么左端右移，反之右端左移，直到左右两端移到中间重合，记录这个过程中每一次组成木桶的容积，返回其中最大的。
     *
     * 因为当左端线段L小于右端线段R时，我们把L右移，这时舍弃的是L与右端其他线段（R-1, R-2, ...）组成的木桶，
     * 这些木桶是没必要判断的，因为这些木桶的容积肯定都没有L和R组成的木桶容积大(L比R小,高度一定是l,则宽度减小,容量也会减小)。
     *
     * 严谨的证明（反证法）：
     * 假设：该算法并没有遍历到容量最大的情况
     *
     * 我们令容量最大时的指针为pleft和pright。根据题设，我们可以假设遍历时左指针先到达pleft，但是当左指针为pleft时，右指针还没有经过pright左指针就移动了
     * 已知当左指针停留在pleft时，它只有在两种场景下会发生改变
     * 左指针和右指针在pleft相遇，则右指针一定在前往pleft的途中经过pright，与题设矛盾
     * 右指针位于pright右侧且当前的值大于左指针。则在这种情况下，此时容器的盛水量比题设中最大的盛水量还要大，与题设矛盾
     * 因此该算法的遍历一定经过了最大的盛水量的情况
     */
    private static class Solution1 extends Solution {

        @Override
        public int maxArea(int[] height) {
            final int n = height.length;
            if (n < 2) {
                return 0;
            }
            int i = 0, j = n - 1;
            int area = 0;
            while (i < j) {
                area = Math.max(area, (j - i) * Math.min(height[i], height[j]));
                if (height[i] < height[j]) {
                    ++i;
                } else {
                    --j;
                }
            }
            return area;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] a2 = {1, 3, 2, 5, 25, 24, 5};

        Solution s = new Solution1();

        println(s.maxArea(a1));//49
        println(s.maxArea(a2));//24
    }
}
