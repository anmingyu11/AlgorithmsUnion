## 1406 Stone Game 3

## Description

Alice and Bob continue their games with piles of stones.
There are several stones arranged in a row,
and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first.
On each player's turn,
that player can take 1, 2 or 3 stones from the first remaining stones in the row.

The score of each player is the sum of values of the stones taken.
The score of each player is 0 initially.

The objective of the game is to end with the highest score,
and the winner is the player with the highest score and there could be a tie.
The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win,
"Bob" if Bob will win or "Tie" if they end the game with the same score.

Example 1:
Input: values = [1,2,3,7]
Output: "Bob"
Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.

Example 2:
Input: values = [1,2,3,-9]
Output: "Alice"
Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. The next move Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. The next move Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.

Example 3:
Input: values = [1,2,3,6]
Output: "Tie"
Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.

Example 4:
Input: values = [1,2,3,-1,-2,-3,7]
Output: "Alice"

Example 5:
Input: values = [-1,-2,-3]
Output: "Tie"
 

Constraints:

1 <= values.length <= 50000
-1000 <= values[i] <= 1000

## Solution

#### Solution1

```java
class Solution1 {
    int[] sums;
    Integer[] dp;
    int n;

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        sums = new int[n];
        dp = new Integer[n];
        sums[n - 1] = stoneValue[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            sums[i] += stoneValue[i] + sums[i + 1];
        }
        int alice = auxiliary(0);
        int bob = sums[0] - alice;
        if (alice > bob) {
            return "Alice";
        } else if (bob > alice) {
            return "Bob";
        } else {
            return "Tie";
        }

    }

    private int auxiliary(int idx) {
        if (idx >= n) {
            return 0;
        }
        if (dp[idx] != null) {
            return dp[idx];
        }
        int min = 0x7fffffff;
        for (int k = 1; k <= 3; ++k) {
            min = Math.min(min, auxiliary(idx + k));
        }
        dp[idx] = sums[idx] - min;
        return dp[idx];
    }
}
```

#### Solution2

```java

class Solution2 {

    public String stoneGameIII(int[] stoneValue) {
        final int n = stoneValue.length;
        int[] sums = new int[n];
        int[] dp = new int[n]; //  integer is slower than int.

        sums[n - 1] = stoneValue[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            sums[i] = sums[i + 1] + stoneValue[i];
        }

        // converge
        dp[n - 1] = sums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            int max = 0x80000000;
            for (int k = 1; k <= 3; ++k) {
                int res = 0;
                if (i + k >= n) {
                    res = sums[i];
                } else {
                    res = sums[i] - dp[i + k];
                }
                max = Math.max(max, res);
            }
            dp[i] = max;
        }

        int alice = dp[0];
        int bob = sums[0] - dp[0];

        if (alice > bob) {
            return "Alice";
        } else if (alice < bob) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

}
```

#### Solution3

```java
class Solution3 {
    public String stoneGameIII(int[] stoneValue) {
        final int n = stoneValue.length;
        int[] dp = new int[n];

        // converge
        int sums = stoneValue[n - 1];
        dp[n - 1] = sums;
        for (int i = n - 2; i >= 0; --i) {
            int max = 0x80000000;
            sums += stoneValue[i];
            for (int k = 1; k <= 3; ++k) {
                int res = 0;
                if (i + k >= n) {
                    res = sums;
                } else {
                    res = sums - dp[i + k];
                }
                max = Math.max(max, res);
            }
            dp[i] = max;
        }

        int alice = dp[0];
        int bob = sums - dp[0];

        if (alice > bob) {
            return "Alice";
        } else if (alice < bob) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
```

#### Solution4

```java
class Solution4 {

    public String stoneGameIII(int[] stoneValue) {
        final int n = stoneValue.length;
        int best = 0x70000000, sums = 0;
        int dp0 = 0, dp1 = 0, dp2 = 0;

        for (int i = n - 1; i >= 0; --i) {
            sums += stoneValue[i];
            int minDp = Math.min(dp0, Math.min(dp1, dp2));
            best = sums - minDp;
            dp2 = dp1;
            dp1 = dp0;
            dp0 = best;
        }

        int alice = best;
        int bob = sums - best;

        if (alice > bob) {
            return "Alice";
        } else if (alice < bob) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
```

## Report

没有1140难， 1140还是一道 medium 的问题。

问题是一个 min max 问题。极大极小问题。

其思路是和1140一样的，但是可以不断优化。
