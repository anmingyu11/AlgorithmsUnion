package _java;

import java.util.LinkedList;

import base.Base;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 * <p>
 * These child lists may have one or more children of their own,
 * and so on, to produce a multilevel data structure, as shown in the example below.
 * <p>
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
 * You are given the head of the first level of the list.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
 * Explanation:
 * <p>
 * The multilevel linked list in the input is as follows:
 * <p>
 * https://assets.leetcode.com/uploads/2018/10/12/multilevellinkedlist.png
 * <p>
 * After flattening the multilevel linked list it becomes:
 * <p>
 * https://assets.leetcode.com/uploads/2018/10/12/multilevellinkedlistflattened.png
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2,null,3]
 * Output: [1,3,2]
 * Explanation:
 * <p>
 * The input multilevel linked list is as follows:
 * <p>
 * 1---2---NULL
 * |
 * 3---NULL
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * How multilevel linked list is represented in test case:
 * <p>
 * We use the multilevel linked list from Example 1 above:
 * <p>
 * 1---2---3---4---5---6--NULL
 * |
 * 7---8---9---10--NULL
 * |
 * 11--12--NULL
 * The serialization of each level is as follows:
 * <p>
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * To serialize all levels together we will add nulls in each level to signify
 * no node connects to the upper node of the previous level.
 * <p>
 * The serialization becomes:
 * <p>
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * Merging the serialization of each level and removing trailing nulls we obtain:
 * <p>
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * <p>
 * Constraints:
 * <p>
 * Number of Nodes will not exceed 1000.
 * 1 <= Node.val <= 10^5
 */
public class _0430FlattenAMultilevelDoublyLinkedList extends Base {

    private static abstract class Solution {
        class Node {
            public int val;
            public Node prev;
            public Node next;
            public Node child;
        }

        public abstract Node flatten(Node head);
    }

    private static class Solution1 extends Solution {

        @Override
        public Node flatten(Node head) {
            LinkedList<Node> res = new LinkedList<>();
            aux(head, res);
            Node prev = null;
            for (Node x : res) {
                x.prev = prev;
                x.child = null;
                if (prev != null) {
                    prev.next = x;
                }
                prev = x;
            }
            return head;
        }

        private void aux(Node x, LinkedList<Node> res) {
            while (x != null) {
                res.add(x);
                if (x.child != null) {
                    aux(x.child, res);
                }
                x = x.next;
            }
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public Node flatten(Node head) {
            if (head == null || head.child == null && head.next == null) {
                return head;
            }
            Node fake = new Node();
            aux(fake, head);
            head.prev = null;
            return fake.next;
        }

        private Node aux(Node fake, Node x) {
            if (x == null) {
                return fake;
            }
            fake.next = x;
            x.prev = fake;
            Node next = x.next;
            Node childTail = aux(fake.next, x.child);
            x.child = null;
            return aux(childTail, next);
        }

    }

    public static void main(String[] args) {

    }
}
