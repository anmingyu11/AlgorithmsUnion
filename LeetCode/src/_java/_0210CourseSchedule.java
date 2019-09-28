package _java;

import java.util.LinkedList;

import base.Base;

public class _0210CourseSchedule extends Base {

    private abstract static class Solution {
        public abstract int[] findOrder(int numCourses, int[][] prerequisites);
    }

    /**
     *
     * Runtime: 3 ms, faster than 96.36% of Java online submissions for Course Schedule II.
     * Memory Usage: 46.6 MB, less than 42.68% of Java online submissions for Course Schedule II.
     */
    private static class Solution1 extends Solution {

        private static class TopologicalSort {

            int V;
            boolean hasCycle;
            boolean[] marked;
            boolean[] onStack;
            LinkedList<Integer>[] adj;
            // postOrder
            LinkedList<Integer> postOrder;

            public TopologicalSort(int V, int[][] edgePairs) {
                this.V = V;
                this.hasCycle = false;
                this.marked = new boolean[this.V];
                this.onStack = new boolean[this.V];
                this.adj = (LinkedList<Integer>[]) new LinkedList[this.V];
                this.postOrder = new LinkedList<>();
                for (int v = 0; v < V; ++v) {
                    adj[v] = new LinkedList<>();
                }
                for (int[] edge : edgePairs) {
                    adj[edge[1]].add(edge[0]);
                }
            }

            private boolean hasCycle() {
                for (int v = 0; v < V; ++v) {
                    if (hasCycle) {
                        break;
                    }
                    if (!marked[v]) {
                        findCycle(v);
                    }
                }
                return hasCycle;
            }

            private void findCycle(int v) {
                marked[v] = true;
                onStack[v] = true;
                for (int w : adj[v]) {
                    if (hasCycle) {
                        return;
                    }
                    if (onStack[w]) {
                        hasCycle = true;
                        return;
                    }
                    if (!marked[w]) {
                        findCycle(w);
                    }
                }
                onStack[v] = false;
            }

            private LinkedList<Integer> postOrder() {
                marked = new boolean[V];
                for (int v = 0; v < V; ++v) {
                    if (!marked[v]) {
                        dfs(v);
                    }
                }
                return postOrder;
            }

            private void dfs(int v) {
                marked[v] = true;
                for (int w : adj[v]) {
                    if (!marked[w]) {
                        dfs(w);
                    }
                }
                postOrder.addLast(v);
            }

            private LinkedList<Integer> order() {
                if (hasCycle()) {
                    return null;
                }
                return postOrder();
            }

        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] res = new int[]{};
            TopologicalSort top = new TopologicalSort(numCourses, prerequisites);
            LinkedList<Integer> order = top.order();
            if (order == null) {
                return res;
            }
            final int n = order.size();
            res = new int[n];
            for (int i = 0; i < n; ++i) {
                res[i] = order.removeLast();
            }
            return res;
        }

    }

    private static void test(int numCourses, int[][] prerequisites) {
        Solution s = new Solution1();
        printArr(s.findOrder(numCourses, prerequisites));
    }

    public static void main(String[] args) {
        test(2, new int[][]{{1, 0}}); // {0,1}
        test(2, new int[][]{{1, 0}, {0, 1}}); // {} has cycle
        test(1, new int[][]{}); // {0}
        test(3, new int[][]{{1, 0}, {2, 0}}); // {0,2,1}
        test(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}); // [0,1,2,3] or [0,2,1,3]
        test(2, new int[][]{}); // [0,1]
    }

}