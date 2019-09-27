package _java;

import java.util.LinkedList;

import base.Base;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class _0207CourseSchedule extends Base {

    private abstract static class Solution {
        public abstract boolean canFinish(int numCourses, int[][] prerequisites);
    }

    /**
     * Runtime: 3 ms, faster than 90.05% of Java online submissions for Course Schedule.
     * Memory Usage: 44.8 MB, less than 92.31% of Java online submissions for Course Schedule.
     * 显然我没有弄清楚有向图的查环和无向图的查环有什么区别.
     */
    private static class Solution1 extends Solution {

        private static class Graph {
            private int V;
            private int E;
            private boolean hasCycle;
            private boolean[] marked;
            private boolean[] onStack;
            private LinkedList<Integer>[] adj;

            public Graph(int V) {
                this.V = V;
                this.E = 0;
                this.hasCycle = false;
                this.marked = new boolean[V];
                this.onStack = new boolean[V];
                this.adj = (LinkedList<Integer>[]) new LinkedList[V];
                for (int v = 0; v < V; ++v) {
                    adj[v] = new LinkedList<>();
                }
            }

            private void addEdge(int v, int w) {
                adj[v].add(w);
            }

            private boolean hasCycle() {
                for (int v = 0; v < V; ++v) {
                    if (hasCycle) {
                        break;
                    }
                    if (!marked[v]) {
                        dfs(v);
                    }
                }
                return hasCycle;
            }

            private void dfs(int v) {
                marked[v] = true;
                onStack[v] = true;
                for (int w : adj[v]) {
                    if (hasCycle) {
                        break;
                    }
                    if (onStack[w]) {
                        hasCycle = true;
                        break;
                    }
                    if (!marked[w]) {
                        dfs(w);
                    }
                }
                onStack[v] = false;
            }

        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (numCourses < 2) {
                return true;
            }
            if (prerequisites.length < 1) {
                return true;
            }
            Graph g = new Graph(numCourses);
            for (int[] prerequisite : prerequisites) {
                g.addEdge(prerequisite[0], prerequisite[1]);
            }
            return !g.hasCycle();
        }

    }


    private static void test(int numCourses, int[][] prerequisites) {
        Solution s = new Solution1();
        println(s.canFinish(numCourses, prerequisites));
    }

    public static void main(String[] args) {
        test(2, new int[][]{{1, 0}}); //T
        test(2, new int[][]{{1, 0}, {0, 1}});//F
        test(1, new int[][]{});//T
        test(3, new int[][]{{1, 0}, {2, 0}});//T
    }

}
