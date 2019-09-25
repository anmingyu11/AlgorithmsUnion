package base;

import java.util.List;

public class BaseGraph extends Base {

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", neighbors=" + neighbors +
                    '}';
        }
    }

}
