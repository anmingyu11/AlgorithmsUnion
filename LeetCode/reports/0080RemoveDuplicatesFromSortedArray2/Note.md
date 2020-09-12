# 80 Remove Duplicates from sorted array 2

## Description

Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}

## Solution

#### Solution1

```java
class Solution {

    int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        int i = 0, j = 0, last = nums[i] - 1, counter = 0;
        for (; i < nums.length; ++i) {
            if (nums[i] != last) {
                nums[j++] = nums[i];
                last = nums[i];
                counter = 1;
            } else if (nums[i] == last && counter < 2) {
                nums[j++] = nums[i];
                ++counter;
            }
        }
        return j;
    }

}
```

#### Solution2

```java
class Solution {

    int removeDuplicates(int[] nums) {
        int i = 0;
        for (int e : nums) {
            if (i < 2 || e > nums[i - 2]) {
                nums[i++] = e;
            }
        }
        return i;
    }
}
```

## Reports

Solution2 是Pochmann的解法

利用了 Sorted Array的特性，关键是还极其巧妙的处理的初始化的问题，一个或逻辑操作如神来之笔。天才可能就是这样的吧。
如果正常思维第一点利用 Sorted Array应该是不难想到的，但是怎样处理一开始的两个节点？
一看就会，但事实上奇妙无穷。