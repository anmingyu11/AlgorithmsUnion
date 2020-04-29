# 1314 Matrix Block Sum

## 题目描述

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number `N` on the chalkboard. On each player's turn, that player makes a *move* consisting of:

- Choosing any `x` with `0 < x < N` and `N % x == 0`.
- Replacing the number `N` on the chalkboard with `N - x`.

Also, if a player cannot make a move, they lose the game.

Return `True` if and only if Alice wins the game, assuming both players play optimally.

**Example 1:**

```
Input: 2
Output: true
Explanation: Alice chooses 1, and Bob has no more moves.
```

**Example 2:**

```
Input: 3
Output: false
Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
```

**Note:**

1. `1 <= N <= 1000`

## Solution

#### 1. 数学归纳法

分两步证明：

1. 如果alice会在N输，那么alice肯定能够在N+1赢，因为alice可以马上将N-1确保自己会赢。
2. 对于任何奇数N，它只有奇数因子，因此在第一步之后，它将是一个偶数。

> odd - 1 = even
>
> even - 1 = odd

所以：

1. N=1 alice输，那么alice一定能在N=2赢。
2. N=3， N=3 只有一个因子1 那么只能让Bob选择2，所以alice输。
3. 显然 N=4 alice赢。
4. 以此类推，只要N=even alice 就能赢。

```java
    private static class Solution1 extends Solution {
        public boolean divisorGame(int N) {
            return (N & 1) == 0;
        }
    }
```

时间复杂度
$$
O(1)
$$

#### 2. 动态规划

动态规划的办法并没有想出来，其实很简单，但是没做出来，这里进行了深刻的思考。

首先思维陷入了一个误区，即整个题的关键一句话在于：

>  assuming both players play optimally.

如何 play optimally ，在于如何能通过你的 move 击败对方，那么这就可以抽象成一个动态规划问题，首先对于1,肯定会输，那么dp要从低位开始，以类似Climbing Stairs的方式，时间复杂度为$O(n^2)$，这样可以避免递归。

我为啥没想到呢，

第一，思维陷入了谁先到1谁先赢，这个其实说对也对说对也不对，但是如果这么想的话就把问题由怎样做出最优选择去赢变成了怎样先到1，这偏离了原来的问题目标，必然不会得到正确的结果。

第二，太久没做dp相关的题，对于dp的形式和思维方式理解有误，如果不能一下看出来这个问题的本质或者简化形式，那就只能先从递归法开始，然后查看可优化的点。

第三，在做题的时候过于放松了，不想思考，不想动，这样是不行的，需要训练自己。

```java
    private static class Solution2 extends Solution {

        public boolean divisorGame(int N) {
            boolean[] dp = new boolean[N + 1];
            dp[1] = false;
            for (int i = 2; i <= N; ++i) {
                for (int x = i - 1; x > 0; --x) {
                    if (i % x == 0 && !dp[i - x]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[N];
        }
    }
```

时间复杂度
$$
O(N^2)
$$

## 理论与技巧

1. odd -1 = even
2. even - 1 = odd
3. [数学归纳法](/Users/helloword/Anmingyu/AlgorithmsUnion/LeetCode/reports/EXPS/数学归纳法.md)

