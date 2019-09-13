package _java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import base.Base;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 * <p>
 * Note:
 * <p>
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class _0718MaximumLengthofRepeatedSubarray extends Base {

    private abstract static class Solution {
        public abstract int findLength(int[] A, int[] B);
    }

    /**
     * BruteForce
     * Runtime: 126 ms, faster than 7.62% of Java online submissions for Maximum Length of Repeated Subarray.
     * Memory Usage: 37.5 MB, less than 100.00% of Java online submissions for Maximum Length of Repeated Subarray.
     */
    private static class Solution1 extends Solution {

        private int[] A, B;
        private int AL, BL;
        private int max = 0;

        public int findLength(int[] A, int[] B) {
            this.A = A;
            this.B = B;
            this.AL = A.length;
            this.BL = B.length;
            max = 0;
            for (int ai = 0; ai < AL; ++ai) {
                for (int bi = 0; bi < BL; ++bi) {
                    max = Math.max(max, auxFindLength(ai, bi));
                }
            }
            return max;
        }

        private int auxFindLength(int ai, int bi) {
            // 小于Longest.
            if (AL - ai < max || BL - bi < max) {
                return 0;
            }
            int cur = 0;
            while (ai < AL && bi < BL && A[ai] == B[bi]) {
                ++cur;
                ++ai;
                ++bi;
            }
            return cur;
        }

    }

    /**
     * BruteForce
     * TLE
     */
    private static class Solution2 extends Solution {

        @Override
        public int findLength(int[] A, int[] B) {
            int ans = 0;
            Map<Integer, LinkedList<Integer>> map = new HashMap<>();
            for (int i = 0; i < B.length; ++i) {
                LinkedList<Integer> bucket = map.get(B[i]);
                if (bucket == null) {
                    bucket = new LinkedList<>();
                }
                bucket.add(i);
                map.put(B[i], bucket);
            }

            for (int i = 0; i < A.length; ++i) {
                LinkedList<Integer> bucket = map.get(A[i]);
                if (bucket == null) {
                    continue;
                }
                for (int j : bucket) {
                    int k = 0;
                    while (i + k < A.length && j + k < B.length
                            && A[i + k] == B[j + k]) {
                        ++k;
                    }
                    ans = Math.max(k, ans);
                }
            }

            return ans;
        }
    }

    private static class Solution3 extends Solution {

        @Override
        public int findLength(int[] A, int[] B) {
            final int AN = A.length;
            final int BN = B.length;
            int max = 0;
            int[][] dp = new int[AN + 1][BN + 1];
            // dp[i,j] 代表 A[i],B[j] 对应的最长子序列的值.
            for (int i = AN - 1; i >= 0; --i) {
                for (int j = BN - 1; j >= 0; --j) {
                    if (A[i] == B[j]) {
                        // dp[i,j] 如果相等,最小为1,如果dp[i][j]相等,而且dp[i+1][j+1]也相等,就可以利用这个子问题.
                        dp[i][j] = dp[i + 1][j + 1] + 1;
                    }
                    max = Math.max(dp[i][j], max);
                }
            }
            printArr(dp);
            return max;
        }

    }

    // 前向 dp

    /**
     * Runtime: 44 ms, faster than 48.22% of Java online submissions for Maximum Length of Repeated Subarray.
     * Memory Usage: 51.5 MB, less than 80.00% of Java online submissions for Maximum Length of Repeated Subarray.
     */
    private static class Solution4 extends Solution {

        @Override
        public int findLength(int[] A, int[] B) {
            final int AN = A.length;
            final int BN = B.length;
            int max = 0;
            int[][] dp = new int[AN + 1][BN + 1];
            for (int i = 1; i <= AN; ++i) {
                for (int j = 1; j <= BN; ++j) {
                    if (A[i - 1] == B[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            printArr(dp);
            return max;
        }
    }

    /**
     * Runtime: 860 ms, faster than 5.03% of Java online submissions for Maximum Length of Repeated Subarray.
     * Memory Usage: 50.4 MB, less than 80.00% of Java online submissions for Maximum Length of Repeated Subarray.
     */
    private static class Solution5 extends Solution {

        private int[] A;
        private int[] B;

        public int findLength(int[] A, int[] B) {
            this.A = A;
            this.B = B;
            final int AN = A.length;
            final int BN = B.length;
            int lo = 0, hi = Math.min(AN, BN) + 1;
            // 增加lo,减小hi,定位mid
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (check(mid)) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo - 1;
        }

        private boolean check(int len) {
            Set<String> seen = new HashSet<>();
            for (int i = 0; i + len <= A.length; ++i) {
                seen.add(Arrays.toString(Arrays.copyOfRange(A, i, i + len)));
            }
            for (int i = 0; i + len <= B.length; ++i) {
                if (seen.contains(Arrays.toString(Arrays.copyOfRange(B, i, i + len)))) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * Runtime: 542 ms, faster than 6.27% of Java online submissions for Maximum Length of Repeated Subarray.
     * Memory Usage: 37.9 MB, less than 100.00% of Java online submissions for Maximum Length of Repeated Subarray.
     **/
    private static class Solution6 extends Solution {

        private int[] A;
        private int[] B;

        public int findLength(int[] A, int[] B) {
            this.A = A;
            this.B = B;
            int lo = 0, hi = Math.min(A.length, B.length) + 1;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (check(mid)) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo - 1;
        }

        private boolean check(int len) {
            for (int i = 0; i + len <= A.length; ++i) {
                RK rk = new RK(Arrays.copyOfRange(A, i, i + len));
                if (rk.search(B) >= 0) {
                    return true;
                }
            }
            return false;
        }

        private static class RK {

            static final int R = 128;
            static final int R_LSHIFT = 7;
            static final long Q = 880971759L;

            int[] pat;
            long patH;
            int M;
            long RM;

            public RK(int[] pat) {
                this.pat = pat;
                RM = 1; // RM = R^(m-1)%Q
                M = pat.length;
                for (int i = 1; i < M; ++i) {
                    RM = (RM << R_LSHIFT) % Q;
                }
                patH = hash(pat, M);
            }

            public int search(int[] match) {
                int N = match.length;
                if (N < M) {
                    return -N;
                }
                long matchH = hash(match, M);
                if (matchH == patH
                    // &&check()
                ) {
                    return 0;
                }
                for (int i = M; i < N; ++i) {
                    matchH = (matchH + Q - RM * match[i - M] % Q) % Q;
                    matchH = ((matchH << R_LSHIFT) + match[i]) % Q;
                    int offset = i - M + 1;
                    if (patH == matchH
                        // &&check()
                    ) {
                        return offset;
                    }
                }
                return -N;
            }

            private long hash(int[] key, int m) {
                long hash = 0;
                for (int i = 0; i < m; ++i) {
                    hash = ((hash << R_LSHIFT) + key[i]) % Q;
                }
                return hash;
            }

            private boolean check(int[] dump, int i) {
                return true;
            }

        }

    }

    /**
     * Runtime: 12 ms, faster than 98.57% of Java online submissions for Maximum Length of Repeated Subarray.
     * Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Maximum Length of Repeated Subarray.
     */
    private static class Solution7 extends Solution {

        public int findLength(int[] A, int[] B) {
            int max = 0;
            int m = A.length, n = B.length;
            int[] dp = new int[n + 1];
            for (int i = 1; i <= m; ++i) {
                for (int j = n; j >= 1; --j) {
                    if (A[i - 1] == B[j - 1]) {
                        dp[j] = 1 + dp[j - 1];
                        max = Math.max(max, dp[j]);
                    } else {
                        dp[j] = 0;
                    }
                }
            }
            return max;
        }

    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};

        int[] A2 = {0, 0, 0, 0, 0};
        int[] B2 = {0, 0, 0, 0, 0};

        int[] A3 = {1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0
                , 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0};
        int[] B3 = {1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1
                , 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0};

        Solution s = new Solution7();
        println(s.findLength(A, B));// 3
        println("---------------");
        println(s.findLength(A2, B2));// 5
        println("---------------");
        println(s.findLength(A3, B3));// 11
    }
}