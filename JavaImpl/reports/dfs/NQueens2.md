## NQueens2

#### 神奇的小技巧
用column标记此行之前的哪些column已经放置了queen.
棋盘坐标(row, col)对应column的第col位(LSB --> MSB, 下同).

用diag标记此位置之前的哪些主对角线已经放置了queen.
棋盘坐标(row, col)对应diag的第(n - 1 + row - col)位.
主对角线是否被占据可为y-x 存入到set中

用antiDiag标记此位置之前的哪些副对角线已经放置了queen.
棋盘坐标(row, col)对应antiDiag的第(row + col)位.
副对角线可以为x+y 存入到第二个Set中


