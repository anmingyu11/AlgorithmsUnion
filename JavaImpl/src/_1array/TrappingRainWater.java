package _1array;

public class TrappingRainWater {

    public static int trap_dp(int[] height) {
        final int n = height.length;
        int mx, res = 0;
        int[] dp = new int[n];

        // loop1 : 找到在i处往左最高的height
        mx = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, height[i]);
            dp[i] = mx;
        }

        mx = 0;
        // loop2 : 找到在i处向右最高的height
        for (int i = n - 1; i >= 0; --i) {
            // 挑最大的height
            mx = Math.max(mx, height[i]);
            // 左右相比，哪个最小挑哪个
            dp[i] = Math.min(dp[i], mx);
            if (dp[i] > height[i]) {
                res += dp[i] - height[i];
            }
        }

        return res;
    }

    // 大致意思明白，但时钟无法get到其精妙之处。
    public static int trap_traversal(int[] height) {
        int res = 0;
        int n = height.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mn = Math.min(height[l], height[r]);
            if (mn == height[l]) {
                //左
                ++l;
                while (l < r && height[l] < mn) {
                    res += mn - height[l++];
                }
            } else {
                //右
                --r;
                while (l < r && height[r] < mn) {
                    res += mn - height[r--];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
