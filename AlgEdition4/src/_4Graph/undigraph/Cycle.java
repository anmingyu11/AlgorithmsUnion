package _4Graph.undigraph;

/******************************************************************************
 *  Compilation:  javac Cycle.java
 *  Execution:    java  Cycle filename.txt
 *  Dependencies: Graph.java Stack.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  Identifies a cycle.
 *  Runs in O(E + V) time.
 *
 *  % java Cycle tinyG.txt
 *  3 4 5 3
 *
 *  % java Cycle mediumG.txt
 *  15 0 225 15
 *
 *  % java Cycle largeG.txt
 *  996673 762 840164 4619 785187 194717 996673
 *
 ******************************************************************************/

import _1Fundamentals.Stack.Stack;
import base.stdlib.In;
import base.stdlib.StdOut;

/**
 * The {@code Cycle} class represents a data type for
 * determining whether an undirected graph has a simple cycle.
 * The <em>hasCycle</em> operation determines whether the graph has
 * a cycle and, if so, the <em>cycle</em> operation returns one.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the <em>hasCycle</em> operation takes constant time;
 * the <em>cycle</em> operation takes time proportional
 * to the length of the cycle.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * 检测环:
 * 给定的图是无环图吗
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    /**
     * Determines whether the undirected graph {@code G} has a cycle and,
     * if so, finds such a cycle.
     *
     * @param G the undirected graph
     */
    public Cycle(Graph G) {
        if (hasSelfLoop(G)) {
            return;
        }
        if (hasParallelEdges(G)) {
            return;
        }
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); ++v) {
            if (!marked[v]) {
                dfs(G, v, v);
            }
        }
    }

    // does this graph have a self loop?
    // side effect: initialize cycle to be self loop
    // 自环
    private boolean hasSelfLoop(Graph G) {
        for (int v = 0; v < G.V(); ++v) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }

    // does this graph have two parallel edges?
    // side effect: initialize cycle to be two parallel edges
    // 平行边
    // 如果有平行边
    // 那么说明比如v-w,w-v是平行边
    // 在建立这个图的时候,这两条边被添加了两次,也就是adj(v)中会有两个w
    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); ++v) { // check for parallel edges incident to v
            for (int w : G.adj(v)) {
                if (marked[w]) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked[w] = true;
            }

            for (int w : G.adj(v)) {// check for parallel edges incident to v
                marked[w] = false;
            }
        }
        return false;
    }

    /**
     * Returns true if the graph {@code G} has a cycle.
     *
     * @return {@code true} if the graph has a cycle; {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a cycle in the graph {@code G}.
     *
     * @return a cycle if the graph {@code G} has a cycle,
     * and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    // 检查环,忽略平行边
    // w!=u这个段,说明了已经有环存在,
    // 因为无环图不可能存在dfs(w) 在dfs(v)调用之前被调用
    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) { // short circuit if cycle already found
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);
            } else if (w != u) {// 检查环,忽略平行边
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) { // v从哪里来
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    /**
     * Unit tests the {@code Cycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("Graph is acyclic");
        }
    }

}