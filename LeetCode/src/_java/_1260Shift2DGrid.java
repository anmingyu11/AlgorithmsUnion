package _java;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _1260Shift2DGrid extends Base {
    private static abstract class Solution {
        public abstract List<List<Integer>> shiftGrid(int[][] grid, int k);
    }

    private static class Solution1 extends Solution {
        @Override
        public List<List<Integer>> shiftGrid(int[][] grid, int k) {
            List<List<Integer>> res = new LinkedList<>();
            final int m = grid.length;
            if (m < 1) {
                return res;
            }
            int n = grid[0].length;
            println("m : "+m + " n : "+n);
            int[][] gridCopy = new int[m][n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int index = (i * n + j + k) % (m * n);
                    int shiftI = (index / n) % m;
                    int shiftJ = index % n;
                    //println("I : " + i + " J : "+j);
                    //println("shitfI : " + shiftI + " shiftJ : "+shiftJ);
                    gridCopy[shiftI][shiftJ] = grid[i][j];
                }
            }
            for (int i = 0; i < m; ++i) {
                List<Integer> line = new LinkedList<>();
                res.add(line);
                for (int j = 0; j < n; ++j) {
                    line.add(gridCopy[i][j]);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] grid2 = {{3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}};
        int k2 = 4;
        int[][] grid3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k3 = 9;
        int[][] grid4 = {{1}, {2}, {3}, {4}, {7}, {6}, {5}};
        int k4 = 23;
        Solution s = new Solution1();
        println(s.shiftGrid(grid2, k2));
        println(s.shiftGrid(grid3, k3));
        println(s.shiftGrid(grid4, k4));
    }
}
