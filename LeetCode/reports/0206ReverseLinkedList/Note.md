# 206 Reverse Linked List


## Solutions

### 1. 迭代

```java
// 迭代
private class Solution1 {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
```

### 2. 递归

```java
// 优雅的递归
// 整个递归流程其实与迭代相同
// 递归唯一返回的值就是收敛条件中返回的值即头节点
private class Solution2 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
```

1. 迭代方法没什么可说的。
2. 递归方法，递归方法与以前的常见的递归有所不同，这里的递归仅仅是将最后一个节点取了出来并返回，当然还有遍历的作用。判断条件中第一个condition是用于处理头节点本身是空的情况，不知道哪个二逼给的这么一个testcase.
3. 如何巧妙的使用递归做遍历？