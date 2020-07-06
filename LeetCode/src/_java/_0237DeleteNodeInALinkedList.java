package _java;

import base.BaseLinkedList;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 *
 * Given linked list -- head = [4,5,1,9], which looks like following:
 *
 * 4 -> 5 -> 1 -> 9
 *
 * Example 1:
 *
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
 * Example 2:
 *
 * Input: head = [4,5,1,9], node = 1
 * Output: [4,5,9]
 * Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
 *
 * Note:
 *
 * The linked list will have at least two elements.
 * All of the nodes' values will be unique.
 * The given node will not be the tail and it will always be a valid node of the linked list.
 * Do not return anything from your function.
 */
public class _0237DeleteNodeInALinkedList extends BaseLinkedList {
    private static abstract class Solution{
        public abstract void deleteNode(ListNode node);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Delete Node in a Linked List.
     * Memory Usage: 41.8 MB, less than 5.02% of Java online submissions for Delete Node in a Linked List.
     */
    private static class Solution1 extends Solution{

        public void deleteNode(ListNode node) {
            //ListNode next = node.next;
            //node.val = next.val;
            //node.next = next.next;
            node = new ListNode(-1);
        }
    }

    public static void main(String[] args) {
        ListNode list1 = generateASingleListNode(new int[]{4,5,1,9});
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);

        Solution1 s1 = new Solution1();
        s1.deleteNode(list1);
        printSingleListNode(list1);
    }
}
