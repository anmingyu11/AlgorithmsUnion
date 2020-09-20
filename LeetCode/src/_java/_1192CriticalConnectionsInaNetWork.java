package _java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import base.Base;

/**
 * There are n servers numbered from 0 to n-1 connected by
 * undirected server-to-server connections forming a network
 * where connections[i] = [a, b] represents a connection between servers a and b.
 * Any server can reach any other server directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that,
 * if removed, will make some server unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 */
public class _1192CriticalConnectionsInaNetWork extends Base {

    private static abstract class Solution {
        public abstract List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections);
    }

    /**
     * Brute Force TLE
     */
    private static class Solution1 extends Solution {

        // adjacency list
        private Map<Integer, List<Integer>> adjacencyG;
        private int n;

        @Override
        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            this.n = n;
            List<List<Integer>> res = new LinkedList<>();
            // build graph
            adjacencyG = new HashMap<>();
            for (List<Integer> e : connections) {
                putEdge(e);
            }
            // dfs
            for (List<Integer> e : connections) {
                removeEdge(e);
                // dfs
                Set<Integer> visited = new HashSet<>();
                dfs(e.get(0), visited);
                if (visited.size() < n) {
                    res.add(e);
                }
                putEdge(e);
            }
            return res;
        }

        private void dfs(int v, Set<Integer> visited) {
            if (!visited.add(v) || visited.size() == this.n) {
                // visited
                return;
            }
            List<Integer> adjL = adjacencyG.get(v);
            for (int u : adjL) {
                dfs(u, visited);
            }
        }

        private void removeEdge(List<Integer> e) {
            int a = e.get(0), b = e.get(1);
            // node a
            adjacencyG.get(a).remove(Integer.valueOf(b));
            adjacencyG.get(b).remove(Integer.valueOf(a));
        }

        private void putEdge(List<Integer> e) {
            int a = e.get(0), b = e.get(1);
            // node a
            List<Integer> aList = adjacencyG.get(a);
            if (aList == null) {
                aList = new LinkedList<>();
            }
            aList.add(b);
            adjacencyG.put(a, aList);
            // node b
            List<Integer> bList = adjacencyG.get(b);
            if (bList == null) {
                bList = new LinkedList<>();
            }
            bList.add(a);
            adjacencyG.put(b, bList);
        }

    }

    private static class Solution2 extends Solution {

        private List<Integer>[] adjGraph;
        private Set<List<Integer>> result;

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            // result
            result = new HashSet<>(connections);
            // build Graph
            adjGraph = new List[n];
            for (List<Integer> e : connections) {
                int v = e.get(0), w = e.get(1);
                if (adjGraph[v] == null) {
                    adjGraph[v] = new ArrayList<>();
                }
                if (adjGraph[w] == null) {
                    adjGraph[w] = new ArrayList<>();
                }
                adjGraph[v].add(w);
                adjGraph[w].add(v);
            }
            // rank to store the cycle in graph
            int[] rank = new int[n];
            // reason fill -2 is .
            // rank is 0 if your depth is 1, then visit all vertex is cycle
            // rank is -1 if depth is start with 0, then also meet the above situation
            Arrays.fill(rank, -2);
            // find cycle and delete them
            dfs(0, 0, rank);
            return new ArrayList<>(result);
        }

        private int dfs(int v, int depth, int[] rank) {
            if (rank[v] >= 0) {
                // most important condition.
                // if add a rank[v] < depth then will be search more time
                return rank[v];
            }
            rank[v] = depth;
            int resDepth = depth;
            for (int w : adjGraph[v]) {
                if (rank[w] == rank[v] - 1) {
                    continue;
                }
                int backDepth = dfs(w, depth + 1, rank);
                resDepth = Math.min(resDepth, backDepth);
                if (backDepth <= depth) {
                    result.remove(Arrays.asList(v, w));
                    result.remove(Arrays.asList(w, v));
                }
            }
            return resDepth;
        }

    }

    private static class Solution3 extends Solution{
        @Override
        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            return null;
        }
    }

    private static void putEdge(List<List<Integer>> connections, int v, int u) {
        LinkedList<Integer> edge = new LinkedList<>();
        edge.add(v);
        edge.add(u);
        connections.add(edge);
    }

    public static void main(String[] args) {
        Solution s = new Solution2();
        // graph1
        println("-----------------------");
        int n1 = 4;
        List<List<Integer>> connections1 = new LinkedList<>();
        putEdge(connections1, 0, 1);
        putEdge(connections1, 1, 2);
        putEdge(connections1, 2, 0);
        putEdge(connections1, 1, 3);
        List<List<Integer>> res1 = s.criticalConnections(n1, connections1);
        for (List<Integer> r : res1) {
            for (int i = 0; i < r.size(); ++i) {
                int v = r.get(i);
                if (i < r.size() - 1) {
                    print(v + "->");
                } else {
                    print(v);
                }
            }
            println("");
        }
        // graph2
        println("-----------------------");
        int n2 = 6;
        List<List<Integer>> connections2 = new LinkedList<>();
        putEdge(connections2, 0, 1);
        putEdge(connections2, 1, 2);
        putEdge(connections2, 2, 0);
        putEdge(connections2, 1, 3);
        putEdge(connections2, 3, 4);
        putEdge(connections2, 4, 5);
        putEdge(connections2, 5, 3);
        List<List<Integer>> res2 = s.criticalConnections(n2, connections2);
        for (List<Integer> r : res2) {
            for (int i = 0; i < r.size(); ++i) {
                int v = r.get(i);
                if (i < r.size() - 1) {
                    print(v + "->");
                } else {
                    print(v);
                }
            }
            println("");
        }
        // graph 3
        println("-----------------------");
        int n3 = 5;
        List<List<Integer>> connections3 = new LinkedList<>();
        putEdge(connections3, 1, 0);
        putEdge(connections3, 2, 0);
        putEdge(connections3, 3, 2);
        putEdge(connections3, 4, 2);
        putEdge(connections3, 4, 3);
        putEdge(connections3, 3, 0);
        putEdge(connections3, 4, 0);
        List<List<Integer>> res3 = s.criticalConnections(n3, connections3);
        for (List<Integer> r : res3) {
            for (int i = 0; i < r.size(); ++i) {
                int v = r.get(i);
                if (i < r.size() - 1) {
                    print(v + "->");
                } else {
                    print(v);
                }
            }
            println("");
        }
    }
}
