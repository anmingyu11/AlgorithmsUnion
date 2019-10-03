package _java;

import base.Base;

/**
 * In a N x N grid composed of 1 x 1 squares,
 * each 1 x 1 square consists of a /, \, or blank space.
 * These characters divide the square into contiguous regions.
 * <p>
 * (Note that backslash characters are escaped, so a \ is represented as "\\".)
 * <p>
 * Return the number of regions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * " /",
 * "/ "
 * ]
 * Output: 2
 * Explanation: The 2x2 grid is as follows:
 * <p>
 * Example 2:
 * <p>
 * Input:
 * [
 * " /",
 * "  "
 * ]
 * Output: 1
 * Explanation: The 2x2 grid is as follows:
 * <p>
 * Example 3:
 * <p>
 * Input:
 * [
 * "\\/",
 * "/\\"
 * ]
 * Output: 4
 * Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
 * The 2x2 grid is as follows:
 * <p>
 * Example 4:
 * <p>
 * Input:
 * [
 * "/\\",
 * "\\/"
 * ]
 * Output: 5
 * Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
 * The 2x2 grid is as follows:
 * <p>
 * Example 5:
 * <p>
 * Input:
 * [
 * "//",
 * "/ "
 * ]
 * Output: 3
 * Explanation: The 2x2 grid is as follows:
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] is either '/', '\', or ' '.
 */
public class _0959RegionsCutBySlahes extends Base {

    private abstract static class Solution {

        public abstract int regionsBySlashes(String[] grid);

    }

    /**
     * Runtime: 16 ms, faster than 9.65% of Java online submissions for Regions Cut By Slashes.
     * Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Regions Cut By Slashes.
     * 扩张原图 create by me.
     */
    private static class Solution1 extends Solution {

        private static class UF {

            private int[] parent;
            private int[] rank;
            private int count;

            public UF(int count) {
                this.count = count;
                rank = new int[count];
                parent = new int[count];
                for (int i = 0; i < count; ++i) {
                    parent[i] = i;
                }
            }

            private int find(int v) {
                while (parent[v] != v) {
                    parent[v] = parent[parent[v]];
                    v = parent[v];
                }
                return parent[v];
            }

            private void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }
                if (rank[rootP] > rank[rootQ]) {
                    parent[rootP] = parent[rootQ];
                } else if (rank[rootP] < rank[rootQ]) {
                    parent[rootQ] = parent[rootP];
                } else {
                    ++rank[rootP];
                    parent[rootP] = parent[rootQ];
                }
                --count;
            }

        }

        public int regionsBySlashes(String[] grid) {
            if (grid.length < 1) {
                return 0;
            }
            int m = grid.length, n = grid[0].length();
            char[][] newGrid = new char[3 * m][3 * n];
            char char1 = '1';
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    char gridCH = grid[i - 1].charAt(j - 1);
                    int r = 3 * (i - 1), c = 3 * (j - 1);
                    if (gridCH == '/') {
                        newGrid[r + 2][c] = char1;
                        newGrid[r][c + 2] = char1;
                        newGrid[r + 1][c + 1] = char1;
                    } else if (gridCH == '\\') {
                        newGrid[r][c] = char1;
                        newGrid[r + 1][c + 1] = char1;
                        newGrid[r + 2][c + 2] = char1;
                    }
                }
            }
            UF uf = new UF(9 * m * n);
            m *= 3;
            n *= 3;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (newGrid[i][j] == 0) {
                        int cur = i * m + j;
                        if (i + 1 < m && newGrid[i + 1][j] == 0) {
                            uf.union(cur, cur + m);
                        }
                        if (i - 1 >= 0 && newGrid[i - 1][j] == 0) {
                            uf.union(cur, cur - m);
                        }
                        if (j + 1 < n && newGrid[i][j + 1] == 0) {
                            uf.union(cur, cur + 1);
                        }
                        if (j - 1 >= 0 && newGrid[i][j - 1] == 0) {
                            uf.union(cur, cur - 1);
                        }
                    } else {
                        uf.count = uf.count - 1;
                    }
                }
            }
            return uf.count;
        }

    }

    /**
     * Runtime: 4 ms, faster than 92.78% of Java online submissions for Regions Cut By Slashes.
     * Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Regions Cut By Slashes.
     */
    private static class Solution2 extends Solution {

        private int E;
        private int count;
        private int[] id;

        public int regionsBySlashes(String[] grid) {
            if (grid.length < 1) {
                return 0;
            }
            int n = grid.length;
            E = n;
            count = 4 * n * n; // count = 4 * (n << 1); 这个可千万不能随便用
            id = new int[count];
            for (int i = 0; i < count; ++i) {
                id[i] = i;
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i > 0) {
                        // connect up
                        union(section(i - 1, j, 2), section(i, j, 0));
                    }
                    if (j > 0) {
                        // connect left
                        union(section(i, j - 1, 3), section(i, j, 1));
                    }
                    char ch = grid[i].charAt(j);
                    if (ch != '\\') {
                        // connect 0,1;2,3
                        union(section(i, j, 0), section(i, j, 1));
                        union(section(i, j, 2), section(i, j, 3));
                    }
                    if (ch != '/') {
                        // connect 1,2;0,3
                        union(section(i, j, 0), section(i, j, 3));
                        union(section(i, j, 1), section(i, j, 2));
                    }
                }
            }

            return count;
        }

        private int find(int p) {
            // compress
            while (id[p] != p) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        private void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                id[rootP] = rootQ;
                --count;
            }
        }

        private int section(int i, int j, int sec) {
            return 4 * (i * E + j) + sec;
        }

    }

    public static void main(String[] args) {
        String[] a1 = {
                " /",
                "/ "
        };
        String[] a2 = {
                " /",
                "  "
        };
        String[] a3 = {
                "\\/",
                "/\\"
        };
        String[] a4 = {
                "/\\",
                "\\/"
        };
        String[] a5 = {
                "//",
                "/ "
        };

        Solution s = new Solution2();

        println("--------");
        println(s.regionsBySlashes(a1)); // 2
        println("--------");
        println(s.regionsBySlashes(a2)); // 1
        println("--------");
        println(s.regionsBySlashes(a3)); // 4
        println("--------");
        println(s.regionsBySlashes(a4)); // 5
        println("--------");
        println(s.regionsBySlashes(a5)); // 3
        println("--------");


    }
}
