# 876 Middle of the linked list

## Description

Given a non-empty, singly linked list with head node head, return a middle node of linked list.

If there are two middle nodes, return the second middle node.

Example 1:

Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.

Example 2:

Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.

Note:

The number of nodes in the given list will be between 1 and 100.

## Solution

#### Solution1
```java
class Solution1 {

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next == null) {
            return slow;
        } else {
            return slow.next;
        }
    }

}
```

#### Solution2
```java
class Solution2 {

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
```

## Reports

仔细体会这两种方法的差别

1. `fast != null && fast.next != null`
2. `fast.next !=null && fast.next.next != null`

区别在于当链表长度为偶数时，fast 多走一步和少走一步的区别
slow 同样，slow如果多走了一部，就会在切分节点的后节点， 少走一步就会在切分节点的前节点

节点总数为偶数，设切分节点为 l1, l2
1. slow在l1的尾端
2. slow在l2的头部

如果为奇数，不存在多走和少走的问题， 因为有一个共同条件fast.next!=null不满足