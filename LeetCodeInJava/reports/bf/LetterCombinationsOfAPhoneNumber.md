## LetterCombinationsOfAPhoneNumber

当遇到这类问题，比如t9输入，可以用数组来代替map,能够大大提高效率


```Java
Map<Integer, char[]> map = new HashMap<>(10);

public Solution1() {
    map.put(2, new char[]{'a', 'b', 'c'});
    map.put(3, new char[]{'d', 'e', 'f'});
    map.put(4, new char[]{'g', 'h', 'i'});
    map.put(5, new char[]{'j', 'k', 'l'});
    map.put(6, new char[]{'m', 'n', 'o'});
    map.put(7, new char[]{'p', 'q', 'r', 's'});
    map.put(8, new char[]{'t', 'u', 'v'});
    map.put(9, new char[]{'w', 'x', 'y', 'z'});
}
```

替换为：

```Java
String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
```
