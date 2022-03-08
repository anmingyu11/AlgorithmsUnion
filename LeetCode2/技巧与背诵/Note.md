## 技巧



1. 乘数 `num1` 位数为 M*M*，被乘数 `num2` 位数为 N*N*， `num1 x num2` 结果 `res` 最大总位数为 **M+N**。
2. `num1[i] x num2[j]` 的结果为 `tmp`(位数为两位，"0x","xy"的形式)，其第一位位于 `res[i+j]`，第二位位于 `res[i+j+1]`。