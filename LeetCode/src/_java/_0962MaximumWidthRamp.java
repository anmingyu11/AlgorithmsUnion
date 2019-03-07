package _java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import base.Base;
import base.interfaces.ISort;

public class _0962MaximumWidthRamp extends Base {
    // Given an array A of integers,
    // a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].
    // The width of such a ramp is j - i.
    private abstract static class Solution {
        public abstract int maxWidthRamp(int[] A);
    }

    // beats 35%
    private static class Solution1 extends Solution {

        @Override
        public int maxWidthRamp(int[] A) {
            final int n = A.length;
            int max = 0;
            for (int j = n - 1; j > 0; --j) {
                for (int i = j - 1 - max; i >= 0; --i) {
                    if (A[i] <= A[j]) {
                        max = Math.max(j - i, max);
                    }
                }
            }
            return max;
        }
    }

    private static class Solution2 extends Solution {

        @Override
        public int maxWidthRamp(int[] A) {
            final int n = A.length;
            int max = 0;
            for (int j = n - 1; j > 0; --j) {
                if (max > j) {
                    return max;
                }
                for (int i = j - 1 - max; i >= 0; --i) {
                    if (A[i] <= A[j]) {
                        max = Math.max(j - i, max);
                    }
                }
            }
            return max;
        }
    }

    // Runtime: 41 ms, faster than 68.93% of Java online submissions for Maximum Width Ramp.
    // Memory Usage: 50.5 MB, less than 91.43% of Java online submissions for Maximum Width Ramp.
    private static class Solution3 extends Solution {

        private static class HeapSort {

            private int[] a, b;
            private int n;

            // 堆排序
            private void sort(int[] a, int[] b) {
                n = a.length;
                this.a = a;
                this.b = b;
                for (int k = n / 2; k >= 0; --k) {
                    sink(k, n);
                }
                printArr(b);
                int sz = n;
                while (sz > 0) {
                    swap(0, --sz);
                    sink(0, sz);
                }
            }

            private void sink(int k, int N) {
                while (2 * k + 1 < N) {
                    int child = 2 * k + 1;
                    if (child + 1 < N && a[child + 1] > a[child]) {
                        ++child;
                    }
                    if (a[child] < a[k]) {
                        break;
                    }
                    swap(child, k);
                    k = child;
                }
            }

            private void swap(int i, int j) {
                int t1 = a[i];
                int t2 = b[i];
                a[i] = a[j];
                b[i] = b[j];
                a[j] = t1;
                b[j] = t2;
            }
        }


        public int maxWidthRamp(int[] A) {
            int N = A.length;
            Integer[] B = new Integer[N];
            for (int i = 0; i < N; ++i) {
                B[i] = i;
            }
            Arrays.sort(B, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (A[o1] < A[o2]) {
                        return -1;
                    } else if (A[o1] > A[o2]) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            int ans = 0;
            int m = N;
            for (int i : B) {
                ans = Math.max(ans, i - m);
                m = Math.min(m, i);
            }

            return ans;
        }

        // 堆排序本身的一个flaw,无法做到这一点.
        private static class Sort implements ISort {

            int[] a;

            //堆排序
            public void sort(int[] a) {
                final int n = a.length;
                this.a = a;
                for (int k = n / 2; k >= 0; --k) {
                    sink(k, n);
                }
                int sz = n;
                while (sz > 0) {
                    swap(0, --sz);
                    sink(0, sz);
                }
            }

            private void sink(int k, int N) {
                while (2 * k + 1 < N) {
                    int child = 2 * k + 1;
                    if (child + 1 < N && a[child + 1] > a[child]) {
                        ++child;
                    }
                    if (a[child] < a[k]) {
                        break;
                    }
                    swap(child, k);
                    k = child;
                }
            }

            private void swap(int i, int j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
    }

    // Runtime: 16 ms, faster than 89.31% of Java online submissions for Maximum Width Ramp.
    // Memory Usage: 51.3 MB, less than 79.05% of Java online submissions for Maximum Width Ramp.
    private static class Solution4 extends Solution {

        // 二分查找中带等号和不带等号的区别.hi = mid - 1和hi = mid的区别;
        private int binarySearch(int[] A, List<int[]> candidates, int target) {
            int lo = 0, hi = candidates.size();
            while (lo < hi) {
                int mi = (lo + hi) / 2;
                if (candidates.get(mi)[0] < target) {
                    lo = mi + 1;
                } else {
                    hi = mi;
                }
            }
            return lo;
        }

        public int maxWidthRamp(int[] A) {
            int N = A.length;
            int ans = 0;
            List<int[]> candidates = new ArrayList<>();
            // 先添加最后一个元素.
            candidates.add(new int[]{A[N - 1], N - 1});
            // 从倒数第二个向前进行迭代
            for (int i = N - 2; i >= 0; --i) {
                // 在candidates里进行二分搜索,搜索目标为A[i]
                int target = A[i];
                int bi = binarySearch(A, candidates, target);
                if (bi < candidates.size()) {
                    // 二分搜索到的结果bi, 搜索的结果索引在candidates以内, 说明target满足A[i] <= A[j]
                    // 取出比A[i]大的第一个A[j],我这里不明白,难道不应该检查[bi..candidates,size()]所有元素吗?
                    int j = candidates.get(bi)[1];
                    // 计算j-i是否是最大值.
                    ans = Math.max(ans, j - i);
                } else {
                    // 如果大于等于最大,加入末尾,加到末尾的时候,加入的是A[i],i这个二元组,而且是加到最末尾
                    // 这就意味着,每次二分搜索之后的bi位置,>bi的第一个索引一定是距离bi最远的,因为这个是最早添加进来的
                    // candidates里,A[i]是元素主序,i是元素对应数组的索引,candidates满足这个条件:
                    // 1. 对于 0 <= i < candidates.size(),有candidates[i][0] < candidates[i+1][0](边界就不处理了)
                    // 2. 同样, 有candidates[i][1] > candidates[i+1][1]
                    candidates.add(new int[]{A[i], i});
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {6, 0, 8, 2, 1, 5};
        int[] a2 = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        int[] a3 = {1, 2, 1};

        Solution s = new Solution4();

        println(s.maxWidthRamp(a1)); // 4
        println(s.maxWidthRamp(a2)); // 7
        println(s.maxWidthRamp(a3)); // 2

    }

}