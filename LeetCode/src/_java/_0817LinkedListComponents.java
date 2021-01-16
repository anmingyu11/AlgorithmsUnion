package _java;

import java.util.HashSet;
import java.util.Set;

import base.BaseLinkedList;

/**
 * We are given head, the head node of a linked list containing unique integer values.
 * <p>
 * We are also given the list G, a subset of the values in the linked list.
 * <p>
 * Return the number of connected components in G,
 * where two values are connected if they appear consecutively in the linked list.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
 * Example 2:
 * <p>
 * Input:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, 3 and 4 are connected,
 * so [0, 1] and [3, 4] are the two connected components.
 * Note:
 * <p>
 * If N is the length of the linked list given by head, 1 <= N <= 10000.
 * The value of each node in the linked list will be in the range [0, N - 1].
 * 1 <= G.length <= 10000.
 * G is a subset of all values in the linked list.
 */
public class _0817LinkedListComponents extends BaseLinkedList {

    private static abstract class Solution {
        public abstract int numComponents(ListNode head, int[] G);
    }

    private static class Solution1 extends Solution {

        public int numComponents(ListNode head, int[] G) {
            int n = 0;
            Set<Integer> set = new HashSet<>(G.length);

            for (int v : G) {
                set.add(v);
            }

            for (ListNode<Integer> p = head; p != null; p = p.next) {
                if (set.contains(p.val)) {
                    ++n;
                }
                while (p != null && set.contains(p.val)) {
                    set.remove(p.val);
                    p = p.next;
                }
                if (p == null) {
                    break;
                }
            }
            return n;
        }

    }

    private static class Solution2 extends Solution {

        public int numComponents(ListNode head, int[] G) {
            int n = 0;
            Set<Integer> set = new HashSet<>(G.length);

            for (int v : G) {
                set.add(v);
            }

            for (ListNode<Integer> p = head; p != null; p = p.next) {
                if (set.contains(p.val)) {
                    ++n;
                }
                while (p != null && set.contains(p.val)) {
                    set.remove(p.val);
                    p = p.next;
                }
                if (p == null) {
                    break;
                }
            }
            return n;
        }

    }

    /**
     * Runtime: 5 ms, faster than 91.17% of Java online submissions for Linked List Components.
     * Memory Usage: 39.8 MB, less than 68.93% of Java online submissions for Linked List Components.
     * 不需要remove
     */
    private static class Solution3 extends Solution {

        public int numComponents(ListNode head, int[] G) {
            int n = 0;
            Set<Integer> set = new HashSet<>(G.length);

            for (int v : G) {
                set.add(v);
            }

            for (ListNode<Integer> p = head; p != null; p = p.next) {
                if (set.contains(p.val)) {
                    ++n;
                }
                while (p != null && set.contains(p.val)) {
                    p = p.next;
                }
                if (p == null) {
                    break;
                }
            }

            return n;
        }

    }

    /**
     *
     */
    private static class Solution4 extends Solution {

        public int numComponents(ListNode head, int[] G) {
            int N = 10001;
            int components = 0;
            int[] search = new int[N];
            for (int v : G) {
                search[v] = 1;
            }

            for (ListNode<Integer> p = head; p != null; p = p.next){
                if (search[p.val] > 0) {
                    ++components;
                }
                while(p!=null && search[p.val] > 0) {
                    p = p.next;
                }
                if (p == null){
                    break;
                }
            }
            return components;
        }


    }

    public static void main(String[] args) {

    }
}
