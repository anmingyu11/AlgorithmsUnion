# Add Two Numbers 2

## Description

```
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
```

## Solutions

#### Solution1

```java
private static class Solution1 {

    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode<Integer> dummy = new ListNode(-1), p1 = l1, p2 = l2, p = dummy;
        int carry = 0;
        while (p1 != null || p2 != null) {
            int v = carry;
            if (p1 != null) {
                v += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                v += p2.val;
                p2 = p2.next;
            }
            carry = v / 10;
            v = v % 10;
            p.next = new ListNode<>(v);
            p = p.next;
        }
        if (carry != 0) {
            p.next = new ListNode<>(carry);
        }
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode prev = null, next = null, cur = node;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
```

#### Solution2
```java
private static class Solution2 {

    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<Integer> s1 = new LinkedList<>(), s2 = new LinkedList<>();
        ListNode<Integer> p1 = l1, p2 = l2, dummy = new ListNode<>(-1), p = dummy;
        int carry = 0;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                s1.add(p1.val);
                p1 = p1.next;
            }
            if (p2 != null) {
                s2.add(p2.val);
                p2 = p2.next;
            }
        }
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int v = carry;
            if (!s1.isEmpty()) {
                v += s1.removeLast();
            }
            if (!s2.isEmpty()) {
                v += s2.removeLast();
            }
            carry = v / 10;
            v %= 10;
            p.next = new ListNode<>(v);
            p = p.next;
        }
        if (carry != 0) {
            p.next = new ListNode<>(carry);
        }
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode prev = null, next = null, cur = node;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
```

#### Solution3
```java
private static class Solution3 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode<Integer> p1 = l1, p2 = l2, dummy = new ListNode<>(-1);
        int len1 = 0, len2 = 0;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                len1 += 1;
                p1 = p1.next;
            }
            if (p2 != null) {
                len2 += 1;
                p2 = p2.next;
            }
        }
        int offset = len1 - len2;
        if (offset < 0) {
            p1 = l2;
            p2 = l1;
            offset = -offset;
        } else {
            p1 = l1;
            p2 = l2;
        }
        dummy.next = addTwoNumbersAux(offset, p1, p2, dummy.next);
        if (dummy.next.val >=10){
            dummy.next.val %=10;
            dummy.val = 1;
            return dummy;
        }
        return dummy.next;
    }

    private ListNode<Integer> addTwoNumbersAux(int off, ListNode<Integer> p1, ListNode<Integer> p2, ListNode<Integer> cur) {
        if (p1 == null && p2 == null) {
            return null;
        }
        ListNode<Integer> next;
        int v = 0;
        if (off == 0) {
            v = p1.val + p2.val;
            next = addTwoNumbersAux(0, p1.next, p2.next, cur);
        } else {
            v = p1.val;
            next = addTwoNumbersAux(off - 1, p1.next, p2, cur);
        }
        if (next != null) {
            v += next.val / 10;
            next.val = next.val % 10;
        }
        cur = new ListNode<>(v);
        cur.next = next;
        return cur;
    }

}
```

#### Reports

1. 使用反转链表去做
2. 使用栈
3. 递归实现.
