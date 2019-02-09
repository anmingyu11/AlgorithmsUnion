package _java;

import java.util.Arrays;

import base.Base;

public class _0215KthLargestElementinanArray extends Base {

    private abstract static class Solution {
        public abstract int findKthLargest(int[] nums, int k);
    }

    // 排序
    private static class Solution1 extends Solution {
        @Override
        public int findKthLargest(int[] nums, int k) {
            Arrays.sort(nums);
            return nums[nums.length - k];
        }
    }

    // 插入 真的蠢。。 不写这个了 跟插入排序没区别。
    private static class Solution2 extends Solution {

        @Override
        public int findKthLargest(int[] nums, int k) {
            int[] arr = new int[k];

            Arrays.fill(arr, Integer.MIN_VALUE);
            int arrK = 0;
            for (int i = 0; i < k; ++i) {
                if (nums[i] >= arr[0]) {
                    int n = k - 1;
                    while (n >= k - arrK) {
                        arr[n--] = arr[n];
                    }
                    arr[0] = nums[i];
                    ++arrK;
                } else {
                    arr[arrK++] = nums[i];
                }
            }
            for (int i = k; i < nums.length; ++i) {
                if (nums[i] >= arr[0]) {
                    int n = k - 1;
                    while (n >= 1) {
                        arr[n--] = arr[n];
                    }
                    arr[0] = nums[i];
                }
            }

            return arr[k - 1];
        }

    }

    // 堆,直接在原数组里动 7ms左右 beats 77.58%
    private static class Solution3 extends Solution {

        @Override
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;

            for (int i = n / 2; i >= 0; --i) {
                sink(nums, i, n);
            }
            --n;
            for (int i = 0; i < k - 1; ++i) {
                swap(nums, 0, n--);
                sink(nums, 0, n);
            }

            return nums[0];
        }

        private void sink(int[] nums, int k, int len) {
            while (2 * k + 1 < len) {
                int j = 2 * k + 1;
                if (j + 1 < len && nums[j] < nums[j + 1]) {
                    ++j;
                }
                if (nums[j] < nums[k]) {
                    break;
                }
                swap(nums, j, k);
                k = j;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    // 迭代 9ms左右，70.96%
    private static class Solution4 extends Solution {

        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;

            for (int i = n / 2; i >= 0; --i) {
                int t = i;
                while (2 * t + 1 < n) {
                    int j = 2 * t + 1;
                    if (j + 1 < n && nums[j] < nums[j + 1]) {
                        ++j;
                    }
                    if (nums[j] < nums[t]) {
                        break;
                    }
                    int tmp = nums[j];
                    nums[j] = nums[t];
                    nums[t] = tmp;
                    t = j;
                }
            }
            --n;
            for (int i = 0; i < k - 1; ++i) {
                int tmp = nums[0];
                nums[0] = nums[n];
                nums[n--] = tmp;
                int o = 0;
                while (2 * o + 1 < n) {
                    int j = 2 * o + 1;
                    if (j + 1 < n && nums[j] < nums[j + 1]) {
                        ++j;
                    }
                    if (nums[j] < nums[o]) {
                        break;
                    }
                    int tmp2 = nums[j];
                    nums[j] = nums[o];
                    nums[o] = tmp2;
                    o = j;
                }
            }

            return nums[0];
        }

        private void sink(int[] nums, int t, int n) {
            while (2 * t + 1 < n) {
                int j = 2 * t + 1;
                if (j + 1 < n && nums[j] < nums[j + 1]) {
                    ++j;
                }
                if (nums[j] < nums[t]) {
                    break;
                }
                int tmp = nums[j];
                nums[j] = nums[t];
                nums[t] = tmp;
                t = j;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    // Partition. 63 ms 16.51%
    private static class Solution5 extends Solution {

        private void sort(int[] nums, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            int p = partition1(nums, lo, hi);
            sort(nums, lo, p - 1);
            sort(nums, p + 1, hi);
        }

        public int findKthLargest(int[] nums, int k) {
            //int i = find(nums, 0, nums.length - 1, nums.length - k);
            //return nums[i];
            sort(nums, 0, nums.length - 1);
            printArr(nums);
            return 0;
        }

        private int find(int[] nums, int lo, int hi, int k) {
            if (lo >= hi) {
                return hi;
            }
            int p = partition3(nums, lo, hi);
            if (p > k) {
                return find(nums, lo, p - 1, k);
            } else if (p < k) {
                return find(nums, p + 1, hi, k);
            } else {
                return p;
            }
        }

        private int partition1(int[] arr, int lo, int hi) {
            int p = arr[lo];// 枢轴记录
            int i = lo, j = hi;
            while (i < j) {
                while (i < j && arr[j] >= p) {
                    --j;
                }
                arr[i] = arr[j];// 交换比枢轴小的记录到左端
                while (i < j && arr[i] <= p) {
                    ++i;
                }
                arr[j] = arr[i];// 交换比枢轴大的记录到右端
            }
            // 扫描完成，枢轴到位
            arr[i] = p;
            // 返回的是枢轴的位置
            return i;

        }

        private int partition2(int[] a, int lo, int hi) {
            int i = lo;
            int j = hi + 1;
            int v = a[lo];
            while (true) {

                while (v > a[++i]) {
                    if (i == hi) {
                        break;
                    }
                }

                while (v < a[--j]) {
                    if (j == lo) {
                        break;
                    }
                }

                if (i >= j) {
                    break;
                }

                swap(a, i, j);
            }

            swap(a, lo, j);

            return j;
        }

        private int partition3(int[] nums, int lo, int hi) {
            int v = nums[lo];
            //i==lo 因为要剔除一个两个元素的情况，如果跳过第一个就没了。
            int i = lo, j = hi;
            while (i < j) {
                while (nums[i] <= v) {
                    ++i;
                    if (i == hi) {
                        break;
                    }
                }
                while (nums[j] >= v) {
                    --j;
                    //危险
                    if (j < lo) {
                        break;
                    }
                }

                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }

            swap(nums, j, lo);

            return j;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int[] arr2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        int[] arr3 = new int[]{99, 99};
        int k3 = 1;
        int[] arr4 = new int[]{1};
        int k4 = 1;
        int[] arr5 = new int[]{7, 6, 5, 4, 3, 2, 1};
        int k5 = 5;
        int[] arr6 = new int[]{-1, 2, 0};
        int k6 = 2;

        Solution s = new Solution2();

        //println(s.findKthLargest(arr1, k1)); // 5
        //println(s.findKthLargest(arr2, k2)); // 4
        //println(s.findKthLargest(arr3, k3)); // 99
        //println(s.findKthLargest(arr4, k4)); // 1
        //println(s.findKthLargest(arr5, k5)); // 3
        println(s.findKthLargest(arr6, k6)); // 0
    }
}
