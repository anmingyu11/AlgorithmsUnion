# 186 Reverse Words in a String 2

## Description

Given an input string , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?

## Solutions

#### Solution1

```java
class Solution {

    public void reverseWords(char[] s) {
        final int n = s.length;
        for (int i = 0; i < n; ++i) {
            if (s[i] == ' ') {
                continue;
            }
            int j;
            for (j = i; j < n && s[j] != ' '; ++j) ;
            reverse(s, i, j-1);
            i=j;
        }
        reverse(s, 0, n - 1);
    }

    private void reverse(char[] s, int lo, int hi) {
        while (lo < hi) {
            char tmp = s[hi];
            s[hi] = s[lo];
            s[lo] = tmp;
            ++lo;
            --hi;
        }
    }

}
```

## Reports

reverse all and reverse every words.
