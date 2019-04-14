package _java;

import java.util.HashSet;
import java.util.Set;

import base.BaseLinkedList;
import base.util.ListNodeUtil;

/**
 * Given a linked list, return the node where the cycle begins.
 * <p>
 * If there is no cycle, return null.
 * <p>
 * To represent a cycle in the given linked list,
 * we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
 * If pos is -1, then there is no cycle in the linked list.
 * <p>
 * Note: Do not modify the linked list.
 */
public class _0142LinkedListCycle2 extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode detectCycle(ListNode head);
    }

    /**
     * 直觉
     * <p>
     * 如果我们跟踪我们已经在Set中看到的节点，我们可以遍历列表并返回第一个重复节点。
     * <p>
     * 算法
     * <p>
     * 首先，我们分配一个Set来存储ListNode引用。
     * <p>
     * 然后，我们遍历列表，检查被访问者是否包含当前节点。如果已经看到节点，那么它必然是循环的入口。
     * <p>
     * 如果任何其他节点是循环的入口，那么我们将已经返回该节点。否则，永远不会满足if条件，并且我们的函数将返回null。
     * <p>
     * 该算法必须终止于具有有限数量节点的任何列表，因为输入列表的域可以分为两类：循环列表和非循环列表。
     * <p>
     * 非循环列表类似于以空方式终止的节点链，而循环列表可以被认为是非循环列表，其中最终的空值被对某个先前节点的引用所替代。
     * <p>
     * 如果while循环终止，我们返回null，因为我们遍历了整个列表而没有遇到重复的引用。在这种情况下，列表是非循环的。
     * 对于循环列表，while循环永远不会终止，但在某些时候if条件将被满足并导致函数返回。
     * <p>
     * Runtime: 5 ms, faster than 22.80% of Java online submissions for Linked List Cycle II.
     * Memory Usage: 34.6 MB, less than 61.00% of Java online submissions for Linked List Cycle II.
     */
    private static class Solution1 extends Solution {

        @Override
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> set = new HashSet<>();
            ListNode p = head;
            while (p != null) {
                if (!set.add(p)) {
                    return p;
                }
                p = p.next;
            }
            return null;
        }

    }

    /**
     * 弗洛伊德判圈法
     * <p>
     * 第一次相遇时slow走过的距离：a+b，fast走过的距离：a+b+c+b。
     * <p>
     * 因为fast的速度是slow的两倍，所以fast走的距离是slow的两倍，有 2(a+b) = a+b+c+b，可以得到a=c（这个结论很重要！）。
     * <p>
     * 我们发现L=b+c=a+b，也就是说，从一开始到二者第一次相遇，循环的次数就等于环的长度。
     * <p>
     * 2. 我们已经得到了结论a=c，那么让两个指针分别从X和Z开始走，每次走一步，那么正好会在Y相遇！也就是环的第一个节点。
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle II.
     * Memory Usage: 35.1 MB, less than 11.00% of Java online submissions for Linked List Cycle II.
     */
    private static class Solution2 extends Solution {

        @Override
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }
            ListNode slow = head, fast = head;

            while (true) {
                if (fast == null || fast.next == null) {
                    return null;
                }
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    break;
                }
            }

            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        }

    }


    public static void main(String[] args) {
        ListNode l = generateASingleListNode(3, 2, 0, -4);

        ListNodeUtil.getLast(l).next = l.next;

        Solution s = new Solution2();

        ListNode cycleNode = s.detectCycle(l);
        println(cycleNode.val);
    }

}
