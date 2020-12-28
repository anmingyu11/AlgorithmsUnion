# 1290 Convert Binary Number in a Linked List to Integer

## Description

Given head which is a reference node to a singly-linked list.
The value of each node in the linked list is either 0 or 1.
The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.

Example 1:

Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10

Example 2:

Input: head = [0]
Output: 0

Example 3:

Input: head = [1]
Output: 1

Example 4:

Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
Output: 18880

Example 5:

Input: head = [0,0]
Output: 0
 
Constraints:

The Linked List is not empty.
Number of nodes will not exceed 30.
Each node's value is either 0 or 1.

## Solution

#### Solution1

```java
class Solution1 {
    private int bits = 0;
    
    public int getDecimalValue(ListNode head) {
        return aux(0,head);
    }
    
    private int aux(int bit, ListNode x) {
        if(x.next == null) {
            bits = bit;
            return x.val;
        }
        int res = aux(bit + 1, x.next);
        
        return res + (x.val << (bits - bit));
    }
}
```

#### Solution2

```java
class Solution {
    public int getDecimalValue(ListNode head) {
        int sum = 0;
        for(ListNode p = head; p !=null; p=p.next){
            sum = 2 * sum + p.val;
        }
        return sum;
    }
}
```

#### Solution3

```java
class Solution {
    public int getDecimalValue(ListNode head) {
        int sum = 0;
        for (ListNode p = head; p != null; p = p.next) {
            sum = (sum << 1) + p.val;
        }
        return sum;
    }
}
```

## Reports

这其实就是2进制转10进制的最快方法。
其实不管是二进制还是十进制，在计算机里都是二进制存储的。
所以产生了这样的转换方式。

