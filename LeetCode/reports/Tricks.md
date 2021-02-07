## 解决不会数数的问题

| range | start | end  | length |
| :---: | ----- | ---- | ------ |
| [0,n) | 0     | n-1  | n      |
| [0,n] | 0     | n    | n+1    |
| (0,n) | 1     | n-1  | n-1    |
| (0,n] | 1     | n    | n      |
| [a,b) | a     | b-1  | b-a    |
| [a,b] | a     | b    | b-a+1  |
| (a,b) | a+1   | b-1  | b-a-1  |
| (a,b] | a+1   | b    | b-a    |

## 奇数与偶数之间的关系

奇数 + 1 = 偶数 
偶数 + 1 = 奇数
奇数 + 奇数 = 偶数
奇数 + 偶数 = 奇数
偶数 + 偶数 = 偶数
奇数 × 奇数 = 奇数
奇数 × 偶数 = 偶数
偶数 × 偶数 = 偶数

## 直方图减法

```java
class Solution {
    private int solve(int[] piles, int[] sums) {
        final int n = piles.length;
        sums = new int[n];
        sums[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            sums[i] = sums[i + 1] + piles[i];
        }
    }

    private int solve2(){
        int[] sum =  Arrays.copyOf(piles, piles.length);
        for (int i = sum.length - 2; i >= 0; i--) {
            sum[i] += sum[i + 1];
        }
    }

}
```

```java
class Solution {
    private int solve(int[] piles, int[] sums) {
        final int n = piles.length;
        sums = new int[n];
        sums[0] = piles[0];
        for (int i = 1; i < n; ++i) {
            sums[i] += sums[i - 1] + piles[i];
        }
    }
}
```

```java

class Solution {
    private int solve(int[] piles, int[] sums) {
        int[] dp = new int[n + 1];
        dp[n-1] = stoneValue[n-1];
        for(int i = n - 2; i >= 0; --i) {
            dp[i] = dp[i + 1] + stoneValue[i];
        }
    }
}
// dp[i] - dp[j+1] is the sums between [i, j]

```

## 找 square number
```java
class Solution{
    void solve(){
        for(k = 0; k*k <= i; ++k){
            // TODO : k
        }
    }
}
```


