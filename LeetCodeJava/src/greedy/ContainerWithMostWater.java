package greedy;

import base.Base;

public class ContainerWithMostWater extends Base {

    public static int maxArea(int[] height) {
        int maxArea = 0, l = 0, r = height.length - 1;
        while (l <= r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] container = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        println("maxArea : " + maxArea(container));
    }

}
