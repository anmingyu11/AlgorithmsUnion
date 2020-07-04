# 21 Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4

## Solutions

```java
ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
    ListNode fake = new ListNode(Integer.MIN_VALUE);
    ListNode p = fake;
    while (l1 != null || l2 != null) {
        if (l1 == null) {
            p.next = l2;
            p = p.next;
            l2 = l2.next;
            continue;
        }
        if (l2 == null) {
            p.next = l1;
            p = p.next;
            l1 = l1.next;
            continue;
        }
        if (l1.val < l2.val) {
            p.next = l1;
            l1 = l1.next;
        } else {
            p.next = l2;
            l2 = l2.next;
        }
        p = p.next;
    }
    return fake.next;
}
```

## 技巧

1. 注意审题，两个列表的拼接，非两个链表组成新的链表
2. 使用continue会加快程序运行速度，在循环内部有其他的判断的前提下。