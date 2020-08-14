# 234 Palindrome Linked LIst

## Description

## Solution

#### Solution1
一栈加一链表

```java
private static class Solution {

    public boolean isPalindrome(ListNode head) {
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }

        p = head;
        while (p != null) {
            if (p.val != stack.pop().val) {
                return false;
            }
            p = p.next;
        }
        return true;
    }

}
```
#### Solution2

链表分成两段比较
```
private static class Solution {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode p = head;
        while (p != null) {
            p = p.next;
        }
        ListNode mid = findMid(head);
        ListNode reverseMid = reverse(mid);

        p = head;
        while (p != null) {
            if (reverseMid.val != p.val) {
                return false;
            }
            reverseMid = reverseMid.next;
            p = p.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode p = head, prev = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        return prev;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        if (fast != null) {// 奇数的话, slow往后挪一位.
            slow = slow.next;
        }
        return slow;
    }

}
```

## Report

如何利用双指针将链表分段。