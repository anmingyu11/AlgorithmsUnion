## EditDistance

小技巧，如果有一个长度为0则返回不为0的那个

```Java
int m = word1.length();
int n = word2.length();

if (m * n == 0) {
    return m + n;
}
```


