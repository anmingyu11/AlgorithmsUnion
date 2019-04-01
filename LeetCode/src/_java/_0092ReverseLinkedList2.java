package _java;

import base.BaseLinkedList;
import base.util.ArraysUtil;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 */
public class _0092ReverseLinkedList2 extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode<Integer> reverseBetween(ListNode<Integer> head, int m, int n);
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List II.
    // Memory Usage: 35.7 MB, less than 92.53% of Java online submissions for Reverse Linked List II.
    private static class Solution1 extends Solution {


        // Object level variables since we need the changes
        // to persist across recursive calls and Java is pass by value.
        private boolean stop;
        private ListNode<Integer> left;

        public void recurseAndReverse(ListNode<Integer> right, int m, int n) {

            // base case. Don't proceed any further
            if (n == 1) {
                return;
            }

            // Keep moving the right pointer one step forward until (n == 1)
            right = right.next;

            // Keep moving left pointer to the right until we reach the proper node
            // from where the reversal is to start.
            if (m > 1) {
                this.left = this.left.next;
            }

            // Recurse with m and n reduced.
            this.recurseAndReverse(right, m - 1, n - 1);

            // In case both the pointers cross each other or become equal, we
            // stop i.e. don't swap data any further. We are done reversing at this
            // point.
            if (this.left == right || right.next == this.left) {
                this.stop = true;
            }

            // Until the boolean stop is false, swap data between the two pointers
            if (!this.stop) {
                int t = this.left.val;
                this.left.val = right.val;
                right.val = t;

                // Move left one step to the right.
                // The right pointer moves one step back via backtracking.
                this.left = this.left.next;
            }
        }

        public ListNode<Integer> reverseBetween(ListNode<Integer> head, int m, int n) {
            this.left = head;
            this.stop = false;
            this.recurseAndReverse(head, m, n);
            return head;
        }

    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = generateASingleListNode(ArraysUtil.generateFromTo(1, 5));
        int m1 = 2, n1 = 4;

        Solution s = new Solution1();

        //printSingleListNode(s.reverseBetween(l1, m1, n1));
        for (int i = 1; i <= 5; ++i) {
            for (int j = i; j <= 5; ++j) {
                print(" reverse: i : " + i + " j : " + j + " ---- ");
                printSingleListNode(s.reverseBetween(l1, i, j));
            }
        }
    }

}