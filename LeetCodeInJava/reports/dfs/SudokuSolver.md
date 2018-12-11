## SudokuSolver

一轮循环来判断一个数是否可填在当前位置。
```java
private boolean isValid(char[][] board, int x, int y, char ch) {
    int startX = 3 * (x / 3), startY = 3 * (y / 3);
    for (int i = 0; i < 9; ++i) {
        if (i != x && board[i][y] == ch) {
            return false;
        }
        if (i != y && board[x][i] == ch) {
            return false;
        }
        if (board[startX + i / 3][startY + i % 3] == ch) {
            return false;
        }
    }

    return true;
}
```
类似于八皇后问题