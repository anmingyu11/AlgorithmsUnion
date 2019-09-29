package _java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import base.BaseGraph;

/**
 * Given a reference of a node in a connected undirected graph,
 * return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * <p>
 * Example:
 * Input:
 * <p>
 * {
 * "$id":"1", "neighbors":[{
 * "$id":"2", "neighbors":[{
 * "$ref":"1"
 * },{
 * "$id":"3", "neighbors":[{
 * "$ref":"2"
 * },{
 * "$id":"4", "neighbors":[{
 * "$ref":"3"
 * },{
 * "$ref":"1"
 * }],"val":4
 * }],"val":3
 * }],"val":2
 * },{
 * "$ref":"4"
 * }],"val":1
 * }
 * <p>
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 * <p>
 * Note:
 * <p>
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor,
 * then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 */
public class _0133CloneGraph extends BaseGraph {

    private abstract static class Solution {
        public abstract Node cloneGraph(Node node);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Clone Graph.
     * Memory Usage: 34 MB, less than 96.47% of Java online submissions for Clone Graph.
     * Mine
     */
    private static class Solution1 extends Solution {

        private HashMap<Node, Node> map;

        public Node cloneGraph(Node node) {
            map = new HashMap<>();
            return dfs(node);
        }

        private Node dfs(Node node) {
            Node newNode = map.get(node);
            if (newNode != null) {
                return newNode;
            }
            List<Node> neighbors = new LinkedList<>();
            newNode = new Node(node.val, neighbors);
            map.put(node, newNode);
            for (Node neighbor : node.neighbors) {
                neighbors.add(dfs(neighbor));
            }
            return newNode;
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public Node cloneGraph(Node node) {
            return null;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();

    }
}
