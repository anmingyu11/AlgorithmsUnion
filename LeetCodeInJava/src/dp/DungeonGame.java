package dp;

import java.util.Arrays;

import base.Base;

public class DungeonGame extends Base {

    // naive
    static class Solution1 {

        // 取一整条线路的最小值,在每条线路的值的最小值结果里取出两者中的大值。将大值
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon.length == 0 || dungeon[0].length == 0) {
                return 0;
            }
            int hp = backtrack(dungeon, 0, 0, 0, Integer.MAX_VALUE);

            return hp > 0 ? 1 : Math.abs(hp - 1);
        }

        private int backtrack(int[][] dungeon, int row, int col, int cur, int min) {
            // 终止
            if (row >= dungeon.length) {
                return Integer.MIN_VALUE;
            }
            if (col >= dungeon[0].length) {
                return Integer.MIN_VALUE;
            }
            // 求当前的总和
            cur += dungeon[row][col];
            // 保存前路线和的最小值
            min = Math.min(min, cur);
            // 收敛
            if (col == dungeon[0].length - 1 && row == dungeon.length - 1) {
                return min;
            }

            int r = backtrack(dungeon, row, col + 1, cur, min);
            int d = backtrack(dungeon, row + 1, col, cur, min);

            return Math.max(r, d);
        }

    }

    // naive mem
    static class Solution2 {

        // 取一整条线路的最小值,在每条线路的值的最小值结果里取出两者中的大值。将大值
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon.length == 0 || dungeon[0].length == 0) {
                return 0;
            }

            int[][] mem = new int[dungeon.length][dungeon[0].length];
            for (int[] m : mem) {
                Arrays.fill(m, Integer.MAX_VALUE);
            }
            int hp = backtrack(dungeon, mem, 0, 0, 0, Integer.MAX_VALUE);

            print2DArr(mem);
            return hp > 0 ? 1 : Math.abs(hp - 1);
        }

        private int backtrack(int[][] dungeon, int[][] mem, int row, int col, int cur, int min) {
            // 终止
            if (row >= dungeon.length) {
                return Integer.MIN_VALUE;
            }
            if (col >= dungeon[0].length) {
                return Integer.MIN_VALUE;
            }
            // 求当前的总和
            cur += dungeon[row][col];
            // 保存前路线和的最小值
            min = Math.min(min, cur);
            // 收敛
            if (col == dungeon[0].length - 1 && row == dungeon.length - 1) {
                return min;
            }

            int r = backtrack(dungeon, mem, row, col + 1, cur, min);
            int d = backtrack(dungeon, mem, row + 1, col, cur, min);

            int max = Math.max(r, d);
            mem[row][col] = max;

            return max;
        }

    }

    static class Solution3 {
        public int calculateMinimumHP(int[][] dungeon) {
            int rowLen = dungeon.length, colLen = dungeon[0].length;
            int[][] mem = new int[rowLen][colLen];

            for (int i = rowLen - 1; i >= 0; --i) {
                for (int j = colLen - 1; j >= 0; --j) {
                    if (i == rowLen - 1 && j == colLen - 1) {
                        mem[i][j] = Math.max(1, 1 - dungeon[i][j]);
                    } else if (i == rowLen - 1) {
                        mem[i][j] = Math.max(1, mem[i][j + 1] - dungeon[i][j]);
                    } else if (j == colLen - 1) {
                        mem[i][j] = Math.max(1, mem[i + 1][j] - dungeon[i][j]);
                    } else {
                        mem[i][j] = Math.max(1, Math.min(mem[i + 1][j], mem[i][j + 1]) - dungeon[i][j]);
                    }
                    print2DArr(mem);
                }
            }

            return mem[0][0];
        }

    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        int[][] dungeon1 = new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        // ans is 7
/*        StopwatchCPU cpu1 = new StopwatchCPU();
        println(new Solution1().calculateMinimumHP(dungeon1));
        println(cpu1.elapsedTime2());
        StopwatchCPU cpu2 = new StopwatchCPU();
        println(new Solution2().calculateMinimumHP(dungeon1));
        println(cpu2.elapsedTime2());*/
        println(new Solution3().calculateMinimumHP(dungeon1));
    }

    private static void test2() {
        int[][] dungeon1 = new int[][]{{3, -20, 30}, {-3, 4, 0}};
        println(new Solution3().calculateMinimumHP(dungeon1));
/*        // ans is 7
        StopwatchCPU cpu1 = new StopwatchCPU();
        println(new Solution1().calculateMinimumHP(dungeon1));
        println(cpu1.elapsedTime2());
        StopwatchCPU cpu2 = new StopwatchCPU();
        println(new Solution2().calculateMinimumHP(dungeon1));
        println(cpu2.elapsedTime2());*/
    }

}
