package _java;

import java.util.HashMap;
import java.util.Map;

import base.Base;

/**
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 */
public class _0138CopyListwithRandomPointer extends Base {

    private static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    ", random=" + random.val +
                    '}';
        }
    }

    private abstract static class Solution {
        public abstract Node copyRandomList(Node head);
    }


    // Runtime: 1 ms, faster than 84.35% of Java online submissions for Copy List with Random Pointer.
    // Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Copy List with Random Pointer.
    // 我的写法
    private static class Solution1 extends Solution {

        @Override
        public Node copyRandomList(Node head) {
            Map<Node, Integer> map = new HashMap<>();
            Map<Integer, Node> cloneMap = new HashMap<>();

            Node dummy = new Node();
            Node pD = dummy;
            Node p = head;
            int n = 0;
            while (p != null) {
                // new Node
                Node newNode = new Node();
                newNode.val = p.val;
                pD.next = newNode;
                pD = pD.next;
                // map
                map.put(p, n);
                cloneMap.put(n, newNode);
                ++n;
                p = p.next;
            }

            pD = dummy.next;
            p = head;
            while (p != null) {
                Integer index = map.get(p.random);
                if (index != null) {
                    pD.random = cloneMap.get(index);
                } else {
                    pD.random = null;
                }
                p = p.next;
                pD = pD.next;
            }

            return dummy.next;
        }
    }

    // Runtime: 1 ms, faster than 84.35% of Java online submissions for Copy List with Random Pointer.
    // Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Copy List with Random Pointer.
    // 图遍历
    private static class Solution2 extends Solution {

        Map<Node, Node> map = new HashMap<>();

        @Override
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node node = map.get(head);

            if (node == null) {
                node = new Node();
                node.val = head.val;
            } else {
                return node;
            }


            node.random = copyRandomList(head.random);
            node.next = copyRandomList(head.next);

            map.put(head, node);

            return node;
        }

    }

    // Runtime: 1 ms, faster than 84.35% of Java online submissions for Copy List with Random Pointer.
    // Memory Usage: 35.5 MB, less than 100.00% of Java online submissions for Copy List with Random Pointer.
    // Solution3
    private static class Solution3 extends Solution {

        Map<Node, Node> map = new HashMap<>();

        @Override
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node oldNode = head;
            Node newNode = getCloneNode(head);
            while (oldNode != null) {
                newNode.next = getCloneNode(oldNode.next);
                newNode.random = getCloneNode(oldNode.random);

                oldNode = oldNode.next;
                newNode = newNode.next;
            }
            return map.get(head);
        }

        private Node getCloneNode(Node oldNode) {
            if (oldNode == null) {
                return null;
            }
            Node newNode = map.get(oldNode);
            if (newNode != null) {
                return newNode;
            } else {
                newNode = new Node(oldNode.val, null, null);
                map.put(oldNode, newNode);
                return newNode;
            }
        }

    }

    public static void main(String[] args) {
        Node[] l1 = new Node[2];
        l1[0] = new Node();
        l1[1] = new Node();
        l1[0].val = 1;
        l1[0].next = l1[1];
        l1[0].random = l1[1];
        l1[1].val = 2;
        l1[1].next = null;
        l1[1].random = l1[1];

        Solution s = new Solution3();

        println(l1[0]);
        println(s.copyRandomList(l1[0]));
    }

}