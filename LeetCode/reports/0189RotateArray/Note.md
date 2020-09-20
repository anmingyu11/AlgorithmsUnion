

## Solution

#### Solution1

```java
class Solution1 {
    
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] A, int lo, int hi) {
        while (lo < hi) {
            int tmp = A[lo];
            A[lo] = A[hi];
            A[hi] = tmp;
            ++lo;
            --hi;
        }
    }
}
```
#### Solution2

```java
class Solution2 {

    public void rotate(int[] nums, int k) {
        final int n = nums.length;
        if (n < 2) {
            return;
        }
        k %= n;
        int[] arr = new int[n];
        for (int i = n - 1, j = k - 1; i > n - 1 - k; --i, --j) {
            arr[j] = nums[i];
        }
        for (int i = 0, j = k; i < n - k; ++i, ++j) {
            arr[j] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, n);
    }

}
```
#### Solution3
Approach 3: Using Cyclic Replacements

We can directly place every number of the array at its required correct position.
But if we do that, we will destroy the original element.
Thus, we need to store the number being replaced in a $temp$ variable.
Then, we can place the replaced number $temp$ at its correct position and so on, $n$ times, where $n$ is the length of array.
We have chosen $n$ to be the number of replacements since we have to shift all the elements of the array(which is $n$).
But, there could be a problem with this method, if $n % k = 0$ where $k = k % n$ 
(since a value of $k$ larger than $n$ eventually leads to a $k$ equivalent to $k % n$). 
In this case, while picking up numbers to be placed at the correct position, we will eventually reach the number from which we originally started. 
Thus, in such a case, when we hit the original number's index again, we start the same process with the number following it.

Now let's look at the proof of how the above method works.
Suppose, we have nn as the number of elements in the array and kk is the number of shifts required.
Further, assume $n % k = 0$. 
Now, when we start placing the elements at their correct position, 
in the first cycle all the numbers with their index $i$ satisfying $i%k=0$ get placed at their required position.
This happens because when we jump k steps every time, we will only hit the numbers k steps apart.
We start with index $i = 0$, having $i % k = 0$.
Thus, we hit all the numbers satisfying the above condition in the first cycle.
When we reach back the original index, we have placed $\frac{n}{k}$ elements at their correct position, 
since we hit only that many elements in the first cycle.
Now, we increment the index for replacing the numbers.
This time, we place other $\frac{n}{k}$ elements at their correct position, different from the ones placed correctly in the first cycle, 
because this time we hit all the numbers satisfy the condition $i % k = 1$.
When we hit the starting number again, we increment the index and repeat the same process from $i = 1$ for all the indices satisfying $i % k == 1$
This happens till we reach the number with the index $i%k=0$ again, which occurs for $i=k$.
We will reach such a number after a total of $k$ cycles. 
Now, the total count of numbers exclusive numbers placed at their correct position will be $k \times \frac{n}{k} = n$.
Thus, all the numbers will be placed at their correct position.

Look at the following example to clarify the process:

nums: [1, 2, 3, 4, 5, 6]
k: 2

![](https://leetcode.com/media/original_images/189_Rotate_Array.png)

## Reports

1. 3次反转能达到的效果很不错。
2. 插入排序的方式是最直接的。
3. 用辅助数组也很容易想，但是代码可以写的更好。
1. 直接一步到位的想法很难想:



