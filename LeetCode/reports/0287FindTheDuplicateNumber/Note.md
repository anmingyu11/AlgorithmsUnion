# 287 Find The Duplicate Number

## Description

Given an array nums containing
n + 1 integers where each integer
is between 1 and n (inclusive),
prove that at least one duplicate number must exist.
Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

## Solution

### Solution1

```java
class Solution {

    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int v : nums) {
            if (!set.add(v)) {
                return v;
            }
        }
        return -1;
    }

}
```

### Solution2

```java
class Solution{
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0;
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);

            int slow2 = 0;
            do {
                slow = nums[slow];
                slow2 = nums[slow2];
            } while (slow != slow2);

            return slow;
    }
}
```
## Thought

- 查重问题最简单的就是用HashSet.
- 这是一个链表查环问题，很容易想到如果有重复的一定会有环。可以分两步证明，重复的在前面和重复的在后面。
- 不光要知道有环，还要知道环的入口。