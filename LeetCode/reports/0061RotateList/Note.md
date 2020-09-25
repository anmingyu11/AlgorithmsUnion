# 61. Rotate List

## Description
```

Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
```

## Solution

#### Solution1
```java
class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // Get the len and the last node.
        int n = 1;
        ListNode p, last, prev;
        for (p = head; p.next != null; p = p.next, ++n) ;
        last = p;
        k %= n;
        if (k == 0) {
            return head;
        }
        k = n-k;
        for (p = head, prev = dummy; k > 0; --k, prev = p, p = p.next) ;
        prev.next = null;
        dummy.next = p;
        last.next = head;
        return dummy.next;
    }
}
```

## Reports.

有点不会数数
1. i = 0 i < n ::: n
2. i = 1 i < n ::: n-1
3. i = 0 i <=n ::: n+1
4. i = 1 i <=n ::: n
