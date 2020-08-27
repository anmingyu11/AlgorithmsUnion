# 1171. Remove Zero Sum Consecutive Nodes from Linked List

## Descriptions

```
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum
to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.

(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:

Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.
Example 2:

Input: head = [1,2,3,-3,4]
Output: [1,2,4]
Example 3:

Input: head = [1,2,3,-3,-2]
Output: [1]
 
Constraints:

The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.
```

## Solutions

#### Solution1

```java
class Solution {

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode<Integer> dummy = new ListNode<>(-1), p;
        dummy.next = head;
        boolean removed;
        do {
            int sum = 0;
            int i = 0;
            removed = false;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(sum, -1);
            for (p = dummy.next; p != null; p = p.next, ++i) {
                sum += p.val;
                if (map.containsKey(sum)) {
                    int lo = map.get(sum);
                    int hi = i + 1;
                    dummy.next = remove(dummy.next, lo, hi);
                    removed = true;
                    break;
                } else {
                    map.put(sum, i);
                }
            }
        } while (removed);
        return dummy.next;
    }

    private ListNode remove(ListNode head, int lo, int hi) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p, slow = dummy, fast = null;
        int i;
        for (i = -1, p = dummy; i <= hi && p != null; p = p.next, ++i) {
            if (i == lo) {
                slow = p;
            }
            if (i == hi) {
                fast = p;
            }
        }
        slow.next = fast;
        return dummy.next;
    }

}
```

#### Solution2
```java
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(0), p;
            dummy.next = head;
            int sum = 0;
            Map<Integer, ListNode> map = new HashMap<>();
            for (p = dummy; p != null; p = p.next) {
                sum += p.val;
                map.put(sum, p);
            }
            sum =0;
            for (p = dummy; p != null; p = p.next) {
                sum += p.val;
                p.next = map.get(sum).next;
            }
            return dummy.next;

    }
}
```


## Reports

1. 快慢指针。
2. 第二种办法太过精妙，map可以用来表示图，利用map作为查找的下一个节点，这样即使每一个都get一下也对最终大局无关。