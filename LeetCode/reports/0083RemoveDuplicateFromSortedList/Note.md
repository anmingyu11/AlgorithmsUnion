# 83 Remove Duplicate From Sorted List

## Description

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3

## Solution

#### Solution1

```java
class Solution {

    public ListNode deleteDuplicates(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(head.val - 1);
        dummy.next = head;
        ListNode p = head, prev = dummy;
        while (p != null) {
            ListNode next = p.next;
            if (p.val == prev.val) {
                prev.next = p.next;
            } else {
                prev = p;
            }
            p = next;
        }
        return dummy.next;
    }

}
```

#### Solution2

```java
class Solution {

    public ListNode deleteDuplicates(ListNode<Integer> head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.next.val == p.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
```

## Reports

Easy and there is a elegant code to learn