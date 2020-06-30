# 2 Add Two Numbers

如何把链表的代码写的既优雅又高效？

```java
public ListNode addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
    ListNode<Integer> p = l1, q = l2, dummyHead = new ListNode<>(-1), cur = dummyHead;

    int carry = 0;
    while (p != null || q != null) {
      int val1 = p != null ? p.val : 0;
      int val2 = q != null ? q.val : 0;
      int sum = carry + val1 + val2;
      carry = sum / 10;
      sum %= 10;
      cur.next = new ListNode<>(sum);
      cur = cur.next;
      if (p != null) {
        p = p.next;
      }
      if (q != null) {
        q = q.next;
      }
    }
    if (carry != 0) {
      cur.next = new ListNode<>(1);
    }

    return dummyHead.next;
}
```

## 技巧

carry 代表进位

三元运算符减小复杂的指针判断

对于指针是要详细而慢慢的琢磨，理解游标和对象之间的差异性。

