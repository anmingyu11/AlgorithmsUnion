# 1140 Stone Game 2

## Description.

Alice and Bob continue their games with piles of stones.  

There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  

The objective of the game is to end with the most stones. 

Alice and Bob take turns, with Alice starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  
If Alice takes one pile at the beginning, Bob takes two piles,
then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total.
If Alice takes two piles at the beginning, then Bob can take all three piles left.
In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 

Example 2:

Input: piles = [1,2,3,4,5,100]
Output: 104

## Solution

#### Solution1

```java
class Solution1 {

    private int n;
    private int[] sums;
    private int[][] dp;

    public int stoneGameII(int[] piles) {
        this.n = piles.length;
        // sums dp, cal the fold right sum.
        sums = new int[n];
        // dp[i][j]
        // i denote index the player reach
        // j denote when reach index i, the player current m.
        dp = new int[n][n];
        sums[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            sums[i] = sums[i + 1] + piles[i];
        }
        return auxiliary(0, 1);
    }

    private int auxiliary(int idx, int m) {
        if (idx + 2 * m >= n) {
            // converge condition, which is the most important.
            // it means when the player reach idx,m, will take as most stone as can take.
            return sums[idx];
        }
        if (dp[idx][m] > 0) {
            // dp mem
            return dp[idx][m];
        }
        int left = 1, right = 2 * m, min = 0x7fffffff;
        for (int x = left; x <= right; ++x) {
            // auxiliary represent the next player can gain.
            // min rep the next player can gain min.
            min = Math.min(min, auxiliary(idx + x, Math.max(x, m)));
        }
        // this is the problem defined.
        // if min denote the min stones next player can take.
        // sums[idx] - min : at idx,m , num of stones current player can take.
        // sums[i] means the all the piles from i to right.
        // min means the next player can gain when current player has been taken.
        // so when they subtract, cut the piles into two piles represent the current player can take or next player can take respectively.
        dp[idx][m] = sums[idx] - min;
        return dp[idx][m];
    }

}
```

```java
class Solution2 {

    private int n;
    private int[] sums;
    private int[][] dp;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        sums = Arrays.copyOf(piles, n);
        dp = new int[n][n];
        for (int i = n - 2; i >= 0; --i) {
            sums[i] += sums[i + 1];
        }
        return auxiliary(0, 1);
    }

    // aux denote the curr player can gain max;
    private int auxiliary(int idx, int m) {
        if (idx + 2 * m >= n) {
            return sums[idx];
        }
        if (dp[idx][m] > 0) {
            return dp[idx][m];
        }
        int left = 1, right = 2 * m, max = 0x80000000;
        for (int x = left; x <= right; ++x) {
            int take = sums[idx] - sums[idx + x];
            max = Math.max(max, take + sums[idx + x] - auxiliary(idx + x, Math.max(m, x)));
        }
        dp[idx][m] = max;
        return dp[idx][m];
    }

}
```

## Report

这道题很是精妙。
总共有两个dp 部分

1. dp求和部分;
2. 以及记录在当在第i处m时能拿到的最大值;

这里最重要的是终止条件和收敛条件。

1. 收敛：当满足终止条件时，即 `i + 2 * m >= n`时，函数收敛，即在此时，player会拿走剩下的所有石头;
2. 这里的dp是有先决条件的，因为每个player都会选择最优，只要player有机会就一定会拿走所有石头;
3. 在递归函数非收敛的状态下，会在当前`m`的情况下遍历所有情况即`1 <= x <= 2 * m`，下一个玩家能拿到的最少石头数，
这时候即当前玩家在 `i , m` 的条件下能够拿到的最大石头数;
4. 因为每次计算的是 `sums[i] - min` 而 `sums[i]` 是从i开始到数组最右侧的最大值,
min是下个玩家要拿到的最小值，此时就是当前玩家在当前节点能够拿到的最大值。
