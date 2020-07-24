# 202 Happy Number

## Description

Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

## Solution

#### Solution1

```java
class Solution1 {

    @Override
    public boolean isHappy(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int cycle = 0;
        while (true) {
            int nextN = 0;
            for (int N = n; N != 0; N /= 10) {
                int r = N % 10;
                nextN += r * r;
            }
            if (nextN == 1) {
                return true;
            }
            map.put(n, nextN);
            if (map.containsKey(nextN)) {
                cycle = nextN;
                break;
            }
            n = nextN;
        }
        return false;

    }
}
````

#### Solution2

```java
class Solution2 {
    private int getNext(int n) {
        int next = 0;
        for (int v = n; v != 0; v /= 10) {
            int r = v % 10;
            next += r * r;
        }
        return next;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        int v = n;
        for (; v != 1 && set.add(v); v = getNext(v)) ;
        return v == 1;
    }
}
```

#### Solution3

```java
class Solution3 {

    private int next(int v) {
        int next = 0;
        for (; v != 0; v /= 10) {
            int r = v % 10;
            next += r * r;
        }
        return next;
    }

    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = next(slow);
            fast = next(next(fast));
        } while (slow != 1 && slow != fast);
        return slow == 1;
    }
}
```

## Report

1. 首先对题的理解有问题，做题之前一定要将题意理解透，不管是leetcode还是其他事情，与人沟通也好，都需要明确你要面对的是什么。
2. 邻接表构建图，map 也一样可以构建图，只是每个顶点只可能有一条边。

