## WordSearch

遇到了，当在某些case下，递归无法继续行进的情况，比如在这个问题下，
```java
char[][] board1 = new char[][]{{'a'}};
String word1 = "a";
char[][] board2 = new char[][]{{'a'}, {'a'}};
String word2 = "aaa";
```
这两个集合就无法通过，对于回溯法，最好保持简洁的内循环，
将终止条件和收敛条件写在最外面去判断，这样有利于解决递归无法正常行进的问题。
