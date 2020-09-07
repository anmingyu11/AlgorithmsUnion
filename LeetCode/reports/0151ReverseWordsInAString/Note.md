# 151 Reverse Words In A String

## Description

Given an input string, reverse the string word by word.

Example 1:

Input: "the sky is blue"
Output: "blue is sky the"

Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces.
However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
 
Follow up:

For C programmers, try to solve it in-place in O(1) extra space.

## Solution

#### Solution1
build-in methods

```java
class Solution {

    public String reverseWords(String s) {
        s = s.trim();
        String[] splits = s.split("\\s+");
        List<String> res = Arrays.asList(splits);
        Collections.reverse(res);
        return String.join(" ", res);
    }

}
```
#### Solution2


```java
class Solution {

    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            int j;
            for (j = i; j < s.length() && s.charAt(j) != ' '; ++j) ;
            res.insert(0, s.substring(i, j));
            res.insert(0, ' ');
            i = j;
        }
        if (res.length() > 0) {
            res.deleteCharAt(0);
        }
        return res.toString();
    }

}
```

#### Solution3
reverse every words

```java
class Solution {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int lo, hi;
        for (lo = 0, hi = s.length() - 1; lo <= hi; ++lo) {
            char ch = s.charAt(lo);
            if (ch != ' ') {
                break;
            }
        }
        for (int i = lo; i <= hi; ++i) {
            char ch = s.charAt(i);
            if (ch == ' ' && (i + 1 < s.length() && s.charAt(i + 1) == ' ' || i + 1 == s.length())) {
                continue;
            } else {
                sb.append(ch);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = lo; i <= hi; ++i) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            int j;
            for (j = i; j <= hi && s.charAt(j) != ' '; ++j) ;
            if (res.length() > 0) {
                res.append(' ');
            }
            res.append(reverse(s.substring(i, j)));
            i = j;
        }
        return reverse(res.toString());
    }

    private String reverse(String s) {
        int n = s.length(), lo = 0, hi = n - 1;
        char[] chs = new char[n];
        while (lo <= hi) {
            chs[lo] = s.charAt(hi);
            chs[hi] = s.charAt(lo);
            ++lo;
            --hi;
        }
        return new String(chs);
    }
}
```

#### Solution4
build-in join

```java
class Solution {

    public String reverseWords(String s) {
        LinkedList<String> dq = new LinkedList<>();

        int left = 0, right = s.length() - 1;
        for (; left <= right && s.charAt(left) == ' '; ++left) ;
        for (; left <= right && s.charAt(right) == ' '; --right) ;

        for (; left <= right; ++left) {
            char ch = s.charAt(left);
            if (ch == ' ') {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (; left <= right && (ch = s.charAt(left)) != ' '; ++left) sb.append(ch);
            dq.addFirst(sb.toString());
        }

        return String.join(" ", dq);
    }

}
```

#### Solution5

clean and straight forward

```java
class Solution {

    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            int j;
            for (j = i; j >= 0 && s.charAt(j) != ' '; --j) ;
            if (res.length() > 0) {
                res.append(' ');
            }
            res.append(s.substring(j + 1, i + 1));
            i = j;
        }
        return res.toString();
    }

}
```

## Reports

Overview

Different interviewers would probably expect different approaches for this problem.

The holy war question is to use or not use built-in methods.

As you may notice, most of design problems on Leetcode are voted down because of two main reasons:

- There was no approach with built-in methods / data structures in the article.
- One of the approaches in the article did contain built-in methods / data structures.
- Seems like the community has no common opinion yet, and in practice that means an unpredictable interview experience for some sort of problems.

Here we consider three different solutions of linear time and space complexity:

- Use built-in split and reverse. Benefits: in-place in Python (in-place, but linear space complexity!) and the simplest one to write.
- The most straightforward one. Trim the whitespaces, reverse the whole string and then reverse each word. Benefits: could be done in-place for the
 languages with mutable strings.
- Two passes approach with a deque. Move along the string, word by word, and push each new word in front of the deque. Convert the deque back into
 string. Benefits: two passes.