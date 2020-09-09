# 203 Remove Linekd List Elements

## Description

Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5

## Solution

#### Solution1
```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1), prev = dummy, p = head;
        dummy.next = head;

        while (p != null) {
            if (p.val == val) {
                prev.next = p.next;
            } else {
                prev = p;
            }
            p = p.next;
        }
        return dummy.next;
    }
}
```


## Reports

Easy.
