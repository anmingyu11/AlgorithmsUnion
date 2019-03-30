package _04DivideAndConquer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.Base;
import base.StopwatchCPU;
import base.util.ArraysUtil;

/**
 * 最大子数组问题
 * 对应4.1
 * <p>
 * 也可以用动态规划做.
 * <p>
 * LeetCode上有原题.
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubArray extends Base {

    private static final int NEG_INF = Integer.MIN_VALUE;

    // 伪代码的好处体现出来了
    private abstract static class Solution {

        public abstract int findMaxSubArray(int[] A);

        private int bruteForce(int[] A) {
            final int n = A.length;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; ++i) {
                int sum = 0;
                for (int j = i; j < n; ++j) {
                    sum += A[j];
                    max = Math.max(sum, max);
                }
            }
            return max;
        }

        public <T> void test() {
            final List<int[]> testCases = new LinkedList<>();
            // From ITA
            testCases.add(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
            testCases.add(new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7});
            testCases.add(ArraysUtil.generateFromTo(10, 30));
            testCases.addAll(ArraysUtil.generateSpecial(true));
            testCases.addAll(ArraysUtil.generateRandom(true, 10, 20));

            println("Maximum sub array test started : ");
            StopwatchCPU cpu = new StopwatchCPU();
            for (int[] arr : testCases) {
                int real = bruteForce(arr);
                int your = findMaxSubArray(arr);
                if (real != your) {
                    println("failed in : " + Arrays.toString(arr));
                    println("your : " + your);
                    println("real : " + real);
                    return;
                }
            }
            println("congratulations your solution has passed");
            println("time used : " + cpu.elapsedTime2());
        }


    }

    // 只求最大值, 不求边界
    private static class Solution1 extends Solution {

        @Override
        public int findMaxSubArray(int[] A) {
            return findMaxSubArray(A, 0, A.length - 1);
        }

        private int findMaxSubArray(int[] A, int lo, int hi) {
            int max = NEG_INF;
            if (hi == lo) {
                return A[lo];
            } else {
                int mid = (lo + hi) / 2;
                int leftSum = findMaxSubArray(A, lo, mid);
                int rightSum = findMaxSubArray(A, mid + 1, hi);
                int crossSum = findMaxCrossingSubArray(A, lo, mid, hi);

                int sum = Math.max(crossSum, Math.max(leftSum, rightSum));
                max = Math.max(sum, max);
                return max;
            }
        }

        private int findMaxCrossingSubArray(int[] A, int lo, int mid, int hi) {
            int max = NEG_INF;
            int leftSum = NEG_INF;
            int rightSum = NEG_INF;
            int sum = 0;
            for (int i = mid; i >= lo; --i) {
                sum += A[i];
                if (sum > leftSum) {
                    leftSum = sum;
                    // maxLeft = i;
                }
            }
            sum = 0;
            for (int i = mid + 1; i <= hi; ++i) {
                sum += A[i];
                if (sum > rightSum) {
                    rightSum = sum;
                    // maxRight = j;
                }
            }

            return leftSum + rightSum;
        }

    }

    // 求最大值也存储最大边界
    private static class Solution2 {

        public Ans1 findMaxSubArray(int[] A) {
            return findMaxSubArray(A, 0, A.length - 1);
        }

        private Ans1 findMaxSubArray(int[] A, int lo, int hi) {
            if (hi == lo) {
                return new Ans1(lo, hi, A[lo]);
            } else {
                int mid = (lo + hi) / 2;
                Ans1 ans1 = findMaxSubArray(A, lo, mid);
                Ans1 ans2 = findMaxSubArray(A, mid + 1, hi);
                Ans1 ans3 = findMaxCrossingSubArray(A, lo, mid, hi);

                return Ans1.max(ans1, ans2, ans3);
            }
        }

        private Ans1 findMaxCrossingSubArray(int[] A, int lo, int mid, int hi) {
            int leftMax = mid, rightMax = mid + 1;
            int sum = 0, leftSum = NEG_INF, rightSum = NEG_INF;
            for (int i = mid; i >= lo; --i) {
                sum += A[i];
                if (sum > leftSum) {
                    leftSum = sum;
                    leftMax = i;
                }
            }
            sum = 0;
            for (int i = mid + 1; i <= hi; ++i) {
                sum += A[i];
                if (sum > rightSum) {
                    rightSum = sum;
                    rightMax = i;
                }
            }
            return new Ans1(leftMax, rightMax, leftSum + rightSum);
        }

    }

    // 求最大值,同时输出最大子序列
    private static class Solution3 {

        public Ans2 findMaxSubArray(int[] A) {
            return findMaxSubArray(A, 0, A.length - 1);
        }

        private Ans2 findMaxSubArray(int[] A, int lo, int hi) {
            if (lo == hi) {
                return new Ans2(lo, lo, A[lo], Arrays.copyOfRange(A, lo, lo));
            } else {
                int mid = (lo + hi) / 2;
                Ans2 ans1 = findMaxSubArray(A, lo, mid);
                Ans2 ans2 = findMaxSubArray(A, mid + 1, hi);
                Ans2 ans3 = findMaxCrossingSubArray(A, lo, mid, hi);

                return (Ans2) Ans1.max(ans1, ans2, ans3);
            }
        }

        private Ans2 findMaxCrossingSubArray(int[] A, int lo, int mid, int hi) {
            int left = mid, right = mid + 1;
            int sum = 0, leftSum = NEG_INF, rightSum = NEG_INF;
            for (int i = mid; i >= lo; --i) {
                sum += A[i];
                if (sum > leftSum) {
                    leftSum = sum;
                    left = i;
                }
            }
            sum = 0;
            for (int i = mid + 1; i <= hi; ++i) {
                sum += A[i];
                if (sum > rightSum) {
                    rightSum = sum;
                    right = i;
                }
            }
            return new Ans2(left, right, leftSum + rightSum, Arrays.copyOfRange(A, left, right + 1));
        }
    }

    private static class Ans1 implements Comparable {
        int left;
        int right;
        int max;

        public Ans1(int left, int right, int max) {
            this.left = left;
            this.right = right;
            this.max = max;
        }

        @Override
        public String toString() {
            return max + " " + "[" + left + " , " + right + "]";
        }

        private static Ans1 max(Ans1... s) {
            if (s.length < 1) {
                return null;
            }
            Ans1 max = s[0];
            for (Ans1 a : s) {
                max = a.compareTo(max) > 0 ? a : max;
            }
            return max;
        }

        @Override
        public int compareTo(Object o) {
            Ans1 that = (Ans1) o;
            if (this.max > that.max) {
                return 1;
            } else if (this.max < that.max) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private final static class Ans2 extends Ans1 {
        int[] arr;

        public Ans2(int left, int right, int max, int[] arr) {
            super(left, right, max);
            this.arr = arr;
        }

        @Override
        public String toString() {
            return max + " " + Arrays.toString(arr);
        }

    }

    public static void main(String[] args) {

        Solution s = new Solution1();

        s.test();

    }

}