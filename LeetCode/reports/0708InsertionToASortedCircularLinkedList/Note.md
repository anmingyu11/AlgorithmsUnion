# 708 Insertion into a Sorted Circular Linked List

## Decription

Given a node from a Circular Linked List which is sorted in ascending order,
write a function to insert a value insertVal into the list such that it remains a sorted circular list.
The given node can be a reference to any single node in the list,
and may not be necessarily the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value.
 After the insertion, the circular list should remain sorted.

If the list is empty (i.e., given node is null),
you should create a new single circular list and return the reference to that single node. 
Otherwise, you should return the original given node.


Example 1:
<<<<<<< Updated upstream
 
Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
=======
![](https://assets.leetcode.com/uploads/2019/01/19/example_1_before_65p.jpg)
 
Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: 
In the figure above, there is a sorted circular list of three elements. 
You are given a reference to the node with value 3, and we need to insert 2 into the list.
The new node should be inserted between node 1 and node 3.
After the insertion, the list should look like this, and we should still return node 3.

![](https://assets.leetcode.com/uploads/2019/01/19/example_1_after_65p.jpg)
>>>>>>> Stashed changes

Example 2:

Input: head = [], insertVal = 1
Output: [1]
<<<<<<< Updated upstream
Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
=======
Explanation: 
The list is empty (given head is null).
We create a new single circular list and return the reference to that single node.
>>>>>>> Stashed changes

Example 3:

Input: head = [1], insertVal = 0
Output: [1,0]
 
Constraints:

0 <= Number of Nodes <= 5 * 10^4
-10^6 <= Node.val <= 10^6
-10^6 <= insertVal <= 10^6

## Solution

```java
class Solution {
        public Node insert(Node head, int insertVal) {
            if (head == null) {
                head = new Node(insertVal);
                head.next = head;
                return head;
            }
            Node prev = head, cur = head.next;
            do {
                if ((prev.val <= insertVal && insertVal < cur.val) ||
                        (prev.val > cur.val && insertVal <= cur.val) ||
                        (prev.val > cur.val && insertVal >= prev.val) ||
                        (prev.val == cur.val && insertVal == cur.val)
                ) {
                    break;
                }
                prev = prev.next;
                cur = cur.next;
            } while (prev != head);
            Node x = new Node(insertVal);
            prev.next = x;
            x.next = cur;
            return head;
        }
}
```

## Report

Easy to solve but it is so many cases:
>         // [3,4,1] 2
>         // [1] 0
>         // [] 1
>         // [3,5,1] 0
>         // [1,3,5] 6
>         // [3,5,1] 1
>         // [3,3,5] 6
