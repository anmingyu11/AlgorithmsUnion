package _java;

import java.util.HashMap;
import java.util.HashSet;

import base.Base;

/**
 * On a 2D plane, we place stones at some integer coordinate points.
 * Each coordinate point may have at most one stone.
 * <p>
 * Now, a move consists of removing a stone that shares a column or row
 * with another stone on the grid.
 * <p>
 * What is the largest possible number of moves we can make?
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 * <p>
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 * <p>
 * Input: stones = [[0,0]]
 * Output: 0
 * <p>
 * Note:
 * <p>
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 */
public class _0947MostStonesRemovedWithSameRoworColumn_UNFMULT extends Base {

    private abstract static class Solution {
        public abstract int removeStones(int[][] stones);
    }

    /**
     * Runtime: 7 ms, faster than 85.81% of Java online submissions for Most Stones Removed with Same Row or Column.
     * Memory Usage: 44.4 MB, less than 39.29% of Java online submissions for Most Stones Removed with Same Row or Column.
     * <p>
     * mine
     */
    private static class Solution1 extends Solution {

        private static class UF {

            private class Node {
                private int r;
                private int c;
                private Node root;

                private Node(int r, int c) {
                    this.r = r;
                    this.c = c;
                }

                @Override
                public int hashCode() {
                    return (r + 1) * (c + 1);
                }

            }

            private HashMap<Integer, Node> row;
            private HashMap<Integer, Node> col;
            private final int total;
            private int connected;

            public UF(int N) {
                total = N;
                connected = N;
                row = new HashMap<>();
                col = new HashMap<>();
            }

            private Node findR(int r) {
                Node root = row.get(r);
                if (root == null) {
                    return null;
                }
                while (root.root != root) { // 路径压缩
                    root.root = root.root.root;
                    root = root.root;
                }
                return root;
            }

            private Node findC(int c) {
                Node root = col.get(c);
                if (root == null) {
                    return null;
                }
                while (root.root != root) {
                    root.root = root.root.root;
                    root = root.root;
                }
                return root;
            }

            private void union(int r, int c) {
                Node rootR = findR(r);
                Node rootC = findC(c);
                Node node = new Node(r, c);
                node.root = node;
                row.put(r, node);
                col.put(c, node);
                if (rootR == null && rootC == null) {
                    return;
                } else if (rootR == null) {
                    rootC.root = node;
                } else if (rootC == null) {
                    rootR.root = node;
                } else if (rootR == rootC) {
                    rootR.root = node;
                } else {
                    rootR.root = node;
                    rootC.root = node;
                    --connected;
                }
                --connected;
            }

        }

        public int removeStones(int[][] stones) {
            final int n = stones.length;
            if (n < 1) {
                return 0;
            }
            UF uf = new UF(n);
            for (int i = 0; i < n; ++i) {
                uf.union(stones[i][0], stones[i][1]);
            }
            return uf.total - uf.connected;
        }

    }

    /**
     * Runtime: 6 ms, faster than 95.60% of Java online submissions for Most Stones Removed with Same Row or Column.
     * Memory Usage: 43.7 MB, less than 50.00% of Java online submissions for Most Stones Removed with Same Row or Column.
     * <p>
     * Encoding Nodes
     */
    private static class Solution2 extends Solution {

        private static class UF {

            private int[] parent;
            private int count;

            public UF(int N) {
                parent = new int[N];
                count = N;
                for (int i = 0; i < N; ++i) {
                    parent[i] = i;
                }
            }

            private int find(int p) {
                while (p != parent[p]) {
                    parent[p] = parent[parent[p]];
                    p = parent[p];
                }
                return p;
            }

            private void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                parent[rootQ] = rootP;
            }

        }

        public int removeStones(int[][] stones) {
            final int n = stones.length;
            if (n < 1) {
                return 0;
            }
            UF dsu = new UF(20000);
            for (int[] stone : stones) {
                // col connect to row
                dsu.union(stone[0], 10000 + stone[1]);
            }
            HashSet<Integer> set = new HashSet<>();
            for (int[] stone : stones) {
                // row connect together
                set.add(dsu.find(stone[0]));
            }
            return n - set.size();
        }

    }

    private static class Solution3 extends Solution {

        HashMap<Integer, Integer> nodes;

        public int removeStones(int[][] stones) {
            final int n = stones.length;
            nodes = new HashMap<>();

            return 0;
        }

        private void union(int p, int q) {
        }

        private int find(int p) {
        }

    }

    public static void main(String[] args) {
        int[][] stones1 =
                {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        int[][] stones2 =
                {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
        int[][] stones3 = {{0, 0}};
        int[][] stones4 = {{0, 1}, {1, 0}, {1, 1}};

        Solution s = new Solution3();

        println(s.removeStones(stones1)); // 5
        println(s.removeStones(stones2)); // 3
        println(s.removeStones(stones3)); // 0
        println(s.removeStones(stones4)); // 2

    }

}