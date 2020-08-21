# Intersection of two linked lists

## Descriptions

Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

![](https://assets.leetcode.com/uploads/2018/12/13/160_statement.png)

begin to intersect at node c1.

Example 1:

![](https://assets.leetcode.com/uploads/2020/06/29/160_example_1_1.png)

Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8

Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). 
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. 
There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

Example 2:

![](https://assets.leetcode.com/uploads/2020/06/29/160_example_2.png)

Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Reference of the node with value = 2
Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). 
From the head of A, it reads as [1,9,1,2,4]. 
From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; 
There are 1 node before the intersected node in B.

Example 3:

![](https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png)

Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: null
Input Explanation: From the head of A, it reads as [2,6,4]. 
From the head of B, it reads as [1,5]. 
Since the two lists do not intersect, intersectVal must be 0, 
while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.

Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Each value on each linked list is in the range [1, 10^9].
Your code should preferably run in O(n) time and use only O(1) memory.

## Solutions

#### Solution1
```java
class Solution{
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode pA = headA, pB = headB;
        while (pA != null) {
            set.add(pA);
            pA = pA.next;
        }
        while (pB != null) {
            if (set.contains(pB)) {
                return pB;
            }
            pB = pB.next;
        }
        return null;
    }
}
```

#### Solution2
```java
class Solution {
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA != null ? pA.next : headB;
            pB = pB != null ? pB.next : headA;
        }
        return pA;
    }
}
```

## Reports

第二种方案证明：

首先要明白这里的intersection,是指链表层面的intersection,相当于两条道路有交汇点，并不是两个集合有交集的意思。

两个链表一定是一长一短，或者同样长。

设两个链表为 l1 , l2,
设长度为 len1 , len2
设 len1 <= len2 (len2 <= len1 同理)
可得 len1 <= len2
设两个链表指针分别为 p1 , p2

设 p2 走到 l2 末尾
则 p1 已经在p1走过了 len1 并且在 l2 走过了 len2 - len1
p2 走过了 len2
此时将 p2 转到 l1的头部
则 p2 和 p1 处于对齐状态
假如两者相交 , 则一定会遇到结合点 , 否则会同时走到末尾的 null 结点

