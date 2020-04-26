# 1314 Matrix Block Sum

## 题目描述

Given a `m * n` matrix `mat` and an integer `K`, return a matrix `answer` where each `answer[i][j]` is the sum of all elements `mat[r][c]` for `i - K <= r <= i + K, j - K <= c <= j + K`, and `(r, c)` is a valid position in the matrix.

**Example 1:**

```
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
```

**Example 2:**

```
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]] 
```

**Constraints:**

- `m == mat.length`
- `n == mat[i].length`
- `1 <= m, n, K <= 100`
- `1 <= mat[i][j] <= 100`

## Solutions

### 1. 暴力法

```java
 private static class Solution1 extends Solution {

        private int[][] mat;
        private int K, m, n;

        public int[][] matrixBlockSum(int[][] mat, int K) {
            this.mat = mat;
            this.K = K;
            this.m = mat.length;
            if (m <= 0) {
                return new int[0][];
            }
            this.n = mat[0].length;
            int[][] res = new int[m][n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    res[i][j] = calculateCell(i, j);
                }
            }
            return res;
        }

        private int calculateCell(int r, int c) {
            int sum = 0;
            for (int i = Math.max(r - K, 0); i <= Math.min(r + K, m - 1); ++i) {
                for (int j = Math.max(c - K, 0); j <= Math.min(c + K, n - 1); ++j) {
                    sum += mat[i][j];
                }
            }
            return sum;
        }

    }
```

时间复杂度$O(m*n*k*k)$

简单易懂

### 2. 二维dp

```java
    private static class Solution2 extends Solution {

        private int K, m, n;

        public int[][] matrixBlockSum(int[][] mat, int K) {
            this.K = K;
            this.m = mat.length;
            if (m <= 0) {
                return new int[0][];
            }
            this.n = mat[0].length;
            int[][] res = new int[m][n];
            int[][] dp = new int[m][n];

            // cal sums
            for (int i = 0; i < m; ++i) {
                dp[i][0] = mat[i][0];
                for (int j = 1; j < n; ++j) {
                    dp[i][j] = dp[i][j - 1] + mat[i][j];
                }
            }
            // res
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    res[i][j] = calculateCell(dp, i, j);
                }
            }
            return res;
        }

        private int calculateCell(int[][] dp, int r, int c) {
            int sum = 0;
            for (int i = Math.max(r - K, 0); i <= Math.min(r + K, m - 1); ++i) {
                int leftJ = Math.max(c - K, 0), rightJ = Math.min(c + K, n - 1);
                sum += dp[i][rightJ] - (leftJ > 0 ? dp[i][leftJ - 1] : 0);
            }
            return sum;
        }

    }
```

思路是利用二维数组`dp[m][n]`来存储原矩阵每一行的累加和

再从行的维度通过`dp[i][right] - dp[i][left]`的方式计算每一行的对应列区间的和。来计算

时间复杂度$O(m*n + m*n*k)$ = $O(k*m*n)$

### 3. 一维dp

```java
    private static class Solution3 extends Solution {

        public int[][] matrixBlockSum(int[][] mat, int K) {
            // 如何将dp转化为1维，进而使效率提升至 O(n^2)级别.
            int m = mat.length;
            if (m <= 0) {
                return new int[0][];
            }
            int n = mat[0].length;
            // 1. 先算[0,n)的和.
            // 2. 再从0开始计算区间和，并分配到所有需要计算结果的res.
            int[] dp = new int[n + 1];
            int[][] res = new int[m][n];

            for (int i = 0; i < m; ++i) {
                // 1. calculate sum.
                dp[0] = 0;
                for (int j = 1; j <= n; ++j) {
                    dp[j] = dp[j - 1] + mat[i][j - 1];
                }
                // 2. 发放阶段
                for (int c = 0; c < n; ++c) {
                    int subSum = dp[Math.min(c + K, n - 1) + 1] - dp[Math.max(c - K, 0)];
                    for (int r = Math.max(i - K, 0); r <= Math.min(i + K, m - 1); ++r) {
                        res[r][c] += subSum;
                    }
                }
            }
            return res;
        }

    }
```

将2维dp转化为了1维dp，每算完一行，就将这一行的数据利用并分发出去，将两个双循环变为一个嵌套循环。
$$
O(m*(n + n*2*k)) =
O(m*n + 2*m*n*k) =
O(m*n*k)
$$

### 4. 一维dp的改进

```java
private static class Solution4 extends Solution {

        public int[][] matrixBlockSum(int[][] mat, int K) {
            // 如何将dp转化为1维，进而使效率提升至 O(n^2)级别.
            int m = mat.length;
            if (m <= 0) {
                return new int[0][];
            }
            int n = mat[0].length;
            // 1. 先算[0,n)的和.
            // 2. 再从0开始计算区间和，并分配到所有需要计算结果的res.
            int[] dp = new int[n + 1];
            int[][] res = new int[m][n];

            for (int i = 0; i < m; ++i) {
                // 1. cal & distribute.
                dp[0] = 0;
                for (int j = 1; j <= n; ++j) {
                    dp[j] = dp[j - 1] + mat[i][j - 1];
                    if (j > K) {
                        int subSum = dp[j] - dp[Math.max(0, j - 2 * K - 1)];
                        for (int r = Math.max(i - K, 0); r <= Math.min(i + K, m - 1); ++r) {
                            res[r][j - K - 1] += subSum;
                        }
                    }
                }
                // 2. distribute the remains.
                // remain the most right is n - K - 1
                for (int c = Math.max(0, n - K); c < n; ++c) {
                    int subSum = dp[Math.min(c + K, n)] - dp[Math.max(0, c - K)];
                    for (int r = Math.max(i - K, 0); r <= Math.min(i + K, m - 1); ++r) {
                        res[r][c] += subSum;
                    }
                }
            }
            return res;
        }
}
```

本质上并没有改变，还是$O(m*n*k)$

### 5. 效率最高，代码最优雅的方案，二维dp

```java
private static class Solution5 extends Solution {

        public int[][] matrixBlockSum(int[][] mat, int K) {
            int m = mat.length;
            if (m <= 0) {
                return new int[0][];
            }
            int n = mat[0].length;
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + mat[i - 1][j - 1];
                }
            }
            int[][] res = new int[m][n];
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    int top = Math.max(i - K - 1, 0), bottom = Math.min(i + K, m), left = Math.max(j - K - 1, 0), right = Math.min(j + K, n);
                    res[i - 1][j - 1] =
                            dp[bottom][right] - dp[top][right] - dp[bottom][left] + dp[top][left];
                }
            }
            return res;
        }
}
```

计算二维数组区间和的重要技巧，

![](/Users/helloword/Anmingyu/AlgorithmsUnion/LeetCode/reports/1314MatrixBlockSum/rangeSum.png)

时间复杂度
$$
O(m*n)
$$

