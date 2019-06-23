package _3Searching.Applications;

/******************************************************************************
 *  Compilation:  javac ST.java
 *  Execution:    java ST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/tinyST.txt
 *
 *  Sorted symbol table implementation using a java.util.TreeMap.
 *  Does not allow duplicates.
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import base.stdlib.StdIn;
import base.stdlib.StdOut;

/**
 * The {@code ST} class represents an ordered symbol table of generic
 * key-value pairs.
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 * It also provides ordered methods for finding the <em>minimum</em>,
 * <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 * It also provides a <em>keys</em> method for iterating over all of the keys.
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that
 * values cannot be {@code null}—setting the
 * value associated with a key to {@code null} is equivalent to deleting the key
 * from the symbol table.
 * <p>
 * This implementation uses a balanced binary search tree. It requires that
 * the key type implements the {@code Comparable} interface and calls the
 * {@code compareTo()} and method to compare two keys. It does not call either
 * {@code equals()} or {@code hashCode()}.
 * The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>,
 * <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> operations each take
 * logarithmic time in the worst case.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * ST类表示通用键值对的有序符号表。
 * 它支持通常的put，get，contains，delete，size和is-empty方法。
 * 它还提供了有序的方法来查找minimum, maximum, floor, and ceiling。
 * 它还提供了一种迭代所有键的键方法。
 * 符号表实现关联数组抽象：当将值与已存在于符号表中的键相关联时，约定是将旧值替换为新值。
 * 与java.util.Map不同，此类使用值不能为null的约定 - 将与键关联的值设置为null等效于从符号表中删除键。
 * <p>
 * 此实现使用平衡二叉搜索树。它要求key类型实现Comparable接口并调用compareTo（）和方法来比较两个key。
 * 它不会调用equals（）或hashCode（）。
 * 在最坏的情况下，put，contains，remove，minimum，maximum，ceiling和floor操作都采用对数时间。
 * size()和is-empty()操作需要constant的时间。construct需要constant的时间。
 *
 * @param <Key>   the generic type of keys in this symbol table
 * @param <Value> the generic type of values in this symbol table
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    /**
     * Initializes an empty symbol table.
     * <p>
     * 初始化一个空符号表。
     */
    public ST() {
        st = new TreeMap<>();
    }


    /**
     * Returns the value associated with the given key in this symbol table.
     * <p>
     * 返回与此符号表中给定键关联的值。
     *
     * @param key the key
     * @return the value associated with the given key if the key is in this symbol table;
     * {@code null} if the key is not in this symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null key");
        }
        return st.get(key);
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     * <p>
     * 将指定的键值对插入符号表，如果符号表已包含指定的键，则使用新值覆盖旧值。
     * 如果指定的值为null，则从此符号表中删除指定的键（及其关联值）。
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (val == null) {
            st.remove(key);
        } else {
            st.put(key, val);
        }
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     * <p>
     * 从此符号表中移除指定的键及其关联值（如果键位于此符号表中）。
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls delete() with null key");
        }
        st.remove(key);
    }

    /**
     * Returns true if this symbol table contain the given key.
     * <p>
     * 如果此符号表包含给定键，则返回true。
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls contains() with null key");
        }
        return st.containsKey(key);
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * <p>
     * 返回此符号表中键 - 值对的数量。
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return st.size();
    }

    /**
     * Returns true if this symbol table is empty.
     * <p>
     * 如果此符号表为空，则返回true。
     *
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all keys in this symbol table.
     * <p>
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * <p>
     * 返回此符号表中的所有键。
     * 要迭代名为st的符号表中的所有键，请使用foreach表示法：for（Key key：st.keys（））。
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        return st.keySet();
    }

    /**
     * Returns all of the keys in this symbol table.
     * To iterate over all of the keys in a symbol table named {@code st}, use the
     * foreach notation: {@code for (Key key : st)}.
     * <p>
     * This method is provided for backward compatibility with the version from
     * <em>Introduction to Programming in Java: An Interdisciplinary Approach.</em>
     * <p>
     * 返回此符号表中的所有键。 要迭代名为st的符号表中的所有键，请使用foreach表示法：for（Key key：st）。
     * 提供此方法是为了向后兼容
     *
     * @return an iterator to all of the keys in this symbol table
     * @deprecated Replaced by {@link #keys()}.
     */
    @Deprecated
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    /**
     * Returns the smallest key in this symbol table.
     * <p>
     * 返回此符号表中的最小键。
     *
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return st.firstKey();
    }

    /**
     * Returns the largest key in this symbol table.
     * <p>
     * 返回此符号表中的最大键。
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return st.lastKey();
    }

    /**
     * Returns the smallest key in this symbol table greater than or equal to {@code key}.
     * <p>
     * 返回此符号表中大于或等于key的最小键。
     *
     * @param key the key
     * @return the smallest key in this symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        Key k = st.ceilingKey(key);
        if (k == null) {
            throw new NoSuchElementException("all keys are less than " + key);
        }
        return k;
    }

    /**
     * Returns the largest key in this symbol table less than or equal to {@code key}.
     * <p>
     * 返回此符号表中小于或等于key的最大键。
     *
     * @param key the key
     * @return the largest key in this symbol table less than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        Key k = st.floorKey(key);
        if (k == null) {
            throw new NoSuchElementException("all keys are greater than " + key);
        }
        return k;
    }

    /**
     * Unit tests the {@code ST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}

