package _java;

import base.Base;

public class _0134GasStation extends Base {

    private abstract static class Solution {
        public abstract int canCompleteCircuit(int[] gas, int[] cost);
    }

    // 0ms 100% 贪心 肥胖 丑陋
    private static class Solution1 extends Solution {

        @Override
        public int canCompleteCircuit(int[] gas, int[] cost) {
            final int len = gas.length;
            if (len == 1) {
                return gas[0] - cost[0] >= 0 ? 0 : -1;
            }
            int start = 0;
            for (int i = 0; i < len; ) {
                if (gas[i] < cost[i]) {
                    // 初始结点一定是cost < gas的,如果不是,那么跳过.
                    ++i;
                    continue;
                } else {
                    // 判断下i结点行不行,
                    int remain = gas[i] - cost[i];
                    int j = (i + 1) % len;
                    while (j != i) {
                        remain += gas[j] - cost[j];
                        if (remain >= 0) {
                            // 能继续往下, 继续这个循环
                            j = (j + 1) % len;
                            // 如果已经回到i了,终止循环返回起点
                            if (j == i) {
                                return i;
                            }
                            continue;
                        } else {
                            // 到j不能继续往下, 将i置成j
                            if (j <= i) {
                                return -1;
                            }
                            i = j;
                            break;
                        }
                    }
                }
            }
            return -1;
        }

    }

    // 简洁版本,而且应该比我的少几个常数项
    private static class Solution2 extends Solution {

        @Override
        public int canCompleteCircuit(int[] gas, int[] cost) {
            final int len = gas.length;
            int total = 0;
            int start = 0;
            int remain = 0;
            for (int i = 0; i < len; ++i) {
                remain += gas[i] - cost[i];
                if (remain < 0) {
                    total += remain;
                    remain = 0;
                    start = i + 1;
                }
            }
            total += remain;
            return total < 0 ? -1 : start;
        }
    }

    public static void main(String[] args) {
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        int[] gas3 = {1};
        int[] cost3 = {0};
        int[] gas4 = {1, 2, 3, 100};
        int[] cost4 = {10, 20, 30, 0};
        int[] gas5 = {0};
        int[] cost5 = {1};

        Solution s = new Solution2();

        println(s.canCompleteCircuit(gas1, cost1));// 3
        println(s.canCompleteCircuit(gas2, cost2));// -1
        println(s.canCompleteCircuit(gas3, cost3));// 0
        println(s.canCompleteCircuit(gas4, cost4));// 3
        println(s.canCompleteCircuit(gas5, cost5));// -1
    }

}
