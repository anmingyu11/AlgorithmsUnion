package _java;

import java.util.HashMap;

import base.Base;

/**
 * 2019年 02月 11日 星期一 21:20:28 CST
 * Google面试出现了在近6个月出现了221次
 */
public class _0904FruitIntoBaskets extends Base {

    private abstract static class Solution {
        public abstract int totalFruit(int[] tree);
    }

    // BruteForce
    // 竟然能AC
    // Runtime: 1981 ms, faster than 1.41% of Java online submissions for Fruit Into Baskets.
    // Memory Usage: 41.5 MB, less than 13.21% of Java online submissions for Fruit Into Baskets.
    private static class Solution1 extends Solution {

        @Override
        public int totalFruit(int[] tree) {
            final int n = tree.length;
            int max = 0;
            int basket1 = 0;
            int basket2 = 0;
            boolean basket2Type = false;
            int sum = 0;
            for (int i = 0; i < n; ++i) {
                basket1 = tree[i];
                ++sum;
                for (int j = i + 1; j < n; ++j) {
                    if (!basket2Type) {
                        if (tree[j] != basket1) {
                            basket2 = tree[j];
                            basket2Type = true;
                        }
                        ++sum;
                    } else {
                        if (tree[j] == basket1 || tree[j] == basket2) {
                            ++sum;
                        } else {
                            break;
                        }
                    }
                }
                max = Math.max(max, sum);
                // Reset
                basket1 = 0;
                basket2 = 0;
                basket2Type = false;
                sum = 0;
            }
            return max;
        }
    }

    // 分阶段求解 伪One Pass
    // Runtime: 27 ms, faster than 64.08% of Java online submissions for Fruit Into Baskets.
    // Memory Usage: 40.8 MB, less than 17.88% of Java online submissions for Fruit Into Baskets.
    private static class Solution2 extends Solution {

        @Override
        public int totalFruit(int[] tree) {
            final int n = tree.length;
            int b1 = 0;
            int b2 = -1;
            int max = 0;
            int sum = 0;

            for (int i = 0; i < n; ++i) {
                if (b2 < 0) {
                    if (tree[i] == tree[b1]) {
                        ++sum;
                    } else {
                        b2 = i;
                        ++sum;
                    }
                } else {
                    if (tree[i] == tree[b1]) {
                        ++sum;
                    } else if (tree[i] == tree[b2]) {
                        ++sum;
                    } else {
                        // 找到最长序列 选最大
                        max = Math.max(sum, max);
                        sum = 0;
                        // Reset
                        b1 = b2;
                        i = b1 - 1;
                        b2 = -1;
                    }
                }
            }
            max = Math.max(sum, max);

            return max;
        }
    }

    // 再改进一下 ,很像KMP
    // Runtime: 11 ms, faster than 90.47% of Java online submissions for Fruit Into Baskets.
    // Memory Usage: 51.4 MB, less than 0.95% of Java online submissions for Fruit Into Baskets.
    private static class Solution3 extends Solution {

        @Override
        public int totalFruit(int[] tree) {
            final int n = tree.length;
            int b1 = 0;
            int b2 = -1;
            int max = 0;
            int sum = 0;

            for (int i = 0; i < n; ++i) {
                if (b2 < 0) {
                    if (tree[i] == tree[b1]) {
                        ++sum;
                    } else {
                        b2 = i;
                        ++sum;
                    }
                } else {
                    if (tree[i] == tree[b1]) {
                        ++sum;
                    } else if (tree[i] == tree[b2]) {
                        //多加的一行,用来判断b2最后的第一次出现的位置
                        if (tree[i - 1] != tree[b2]) {
                            b2 = i;
                        }
                        ++sum;
                    } else {
                        // 找到最长序列 选最大
                        max = Math.max(sum, max);
                        sum = 0;
                        // Reset
                        b1 = b2;
                        i = b1 - 1;
                        b2 = -1;
                    }
                }
            }
            max = Math.max(sum, max);

            return max;
        }
    }

    // 难以企及 ,这个太精妙了,不光效率高,而且还很优雅易读
    // Runtime: 9 ms, faster than 99.64% of Java online submissions for Fruit Into Baskets.
    // Memory Usage: 51 MB, less than 100.00% of Java online submissions for Fruit Into Baskets.
    private static class Solution4 extends Solution {

        public int totalFruit(int[] tree) {
            int n = tree.length;
            if (n < 3) {
                return n;
            }

            int b1 = 0;
            int b2 = b1;
            int max = 0;

            for (int i = 1; i < n; i++) {
                if (tree[i] == tree[b1] || tree[i] == tree[b2]) {
                    continue;
                }

                // 如果都不符合,定位b1,并将b1后退到第一个出现b1的位置.
                max = Math.max(max, i - b1);
                b1 = i - 1;

                // 将b1后退至当前b1位置第一个出现的位置
                while (b1 > 0 && tree[b1 - 1] == tree[b1]) {
                    --b1;
                }

                // b2定位为当前位置
                b2 = i;
            }

            max = Math.max(max, n - b1);

            return max;
        }

    }

    private static class Solution5 extends Solution {

        public int totalFruit(int[] tree) {
            int ans = 0, i = 0;
            Counter count = new Counter();
            for (int j = 0; j < tree.length; ++j) {
                count.add(tree[j], 1);
                while (count.size() >= 3) {
                    count.add(tree[i], -1);
                    if (count.get(tree[i]) == 0) {
                        count.remove(tree[i]);
                    }
                    i++;
                }

                ans = Math.max(ans, j - i + 1);
            }

            return ans;
        }

        private class Counter extends HashMap<Integer, Integer> {
            public int get(int k) {
                return containsKey(k) ? super.get(k) : 0;
            }

            public void add(int k, int v) {
                put(k, get(k) + v);
            }
        }
    }

    public static void main(String[] args) {
        int[] tree1 = {1, 2, 1};
        int[] tree2 = {0, 1, 2, 2};
        int[] tree3 = {1, 2, 3, 2, 2};
        int[] tree4 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        int[] tree5 = {0};
        int[] tree6 = {0, 1, 6, 6, 4, 4, 6};

        Solution s = new Solution5();

        println(s.totalFruit(tree1)); // 3
        println(s.totalFruit(tree2)); // 3
        println(s.totalFruit(tree3)); // 4
        println(s.totalFruit(tree4)); // 5
        println(s.totalFruit(tree5)); // 1
        println(s.totalFruit(tree6)); // 5

    }
}
