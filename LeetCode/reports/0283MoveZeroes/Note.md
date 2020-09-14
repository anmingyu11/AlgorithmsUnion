# 283 Move Zeros

## Description

Given an array nums,
write a function to move all 0's to the end of it while maintaining 
the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

## Solution

```java
class Solution {

    void moveZeroes(int[] nums) {
        int j = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (; j < nums.length; ++j) {
            nums[j] = 0;
        }
    }
}
```

## Reports

Easy 但是没有第一时间想到最好的办法。