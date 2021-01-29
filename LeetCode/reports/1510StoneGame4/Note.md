# 1510 Stone Game 4

## Description

## Solution

#### Solution1

```java
class Solution1 {

    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[0] = false;
        for (int i = 1; i <= n; ++i) {
            if (dp[i]) {
                continue;
            }
            for (int k = 1; k * k <= i; ++k) {
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

}
```

#### Solution2

```java
class Solution2 {

    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= n; ++i) {
            if (dp[i]) {
                continue;
            }
            if (dp[n]) {
                break;
            }
            for (int k = 1; i + k * k <= n; ++k) {
                dp[i + k * k] = true;
            }
        }
        return dp[n];
    }
}
```

## Report

这个dp 问题本身是比较简单的，但是可优化的点非常多，并且有些非常有效的技巧。
    - `dp[i]` 代表当剩余的石头是 i 的时候，能否取得胜利。
    - 如果是dp是自底向上实现的，这里则有两种方式，第一种是减法，即从最小的值开始减去 
      square number，查看`dp[i - square]`是否为否这样的话一定会遍历所有的`dp[i]`。
      复杂度为O(n*sqrt(n)) 
    - 第二种是加法，即从0开始开始加法运算，这样可以跳过大多数的`index`，
      因为每个玩家都会做出最优选择，可以直接跳过。
      加了短路条件if(dp[n])break; 后，就会在程序找到答案时提前终止。
      
- 还有一个找 square number 的 trick
for(k = 0; k*k <= i;++k)
