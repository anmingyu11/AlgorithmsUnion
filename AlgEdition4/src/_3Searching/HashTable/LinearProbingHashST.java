package _3Searching.HashTable;

/******************************************************************************
 *  Compilation:  javac LinearProbingHashST.java
 *  Execution:    java LinearProbingHashST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/34hash/tinyST.txt
 *
 *  Symbol-table implementation with linear-probing hash table.
 *
 ******************************************************************************/

import _1Fundamentals.Queue.Queue;
import _3Searching.Applications.ST;
import _3Searching.BST.BST;
import _3Searching.BST.RedBlackBST;
import _3Searching.ElementarySymbolTables.BinarySearchST;
import _3Searching.ElementarySymbolTables.SequentialSearchST;
import _3Searching.SearchTestResources;
import base.stdlib.In;
import base.stdlib.StdOut;

/**
 * The {@code LinearProbingHashST} class represents a symbol table of generic
 * key-value pairs.
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 * It also provides a <em>keys</em> method for iterating over all of the keys.
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that
 * values cannot be {@code null}—setting the
 * value associated with a key to {@code null} is equivalent to deleting the key
 * from the symbol table.
 * <p>
 * This implementation uses a linear probing hash table. It requires that
 * the key type overrides the {@code equals()} and {@code hashCode()} methods.
 * The expected time per <em>put</em>, <em>contains</em>, or <em>remove</em>
 * operation is constant, subject to the uniform hashing assumption.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/34hash">Section 3.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * For other implementations, see {@link ST}, {@link BinarySearchST},
 * {@link SequentialSearchST}, {@link BST}, {@link RedBlackBST}, and
 * {@link SeparateChainingHashST},
 * <p>
 * LinearProbingHashST类表示通用键值对的符号表。
 * 它支持通用的put，get，contains，delete，size和is-empty方法。
 * 它还提供了一种迭代所有key的keys方法。
 * 符号表实现关联数组抽象：当将值与已存在于符号表中的键相关联时，约定是将旧值替换为新值。
 * 与java.util.Map不同，此类使用值不能为null的约定
 * - 将与键关联的值设置为null等效于从符号表中删除键。
 * 此哈希表的实现使用线性探测法。
 * 它要求键类型覆盖equals（）和hashCode（）方法。
 * 每次put，contains或remove操作的预期时间是constant的，受均匀散列假设的影响。
 * size和is-empty需要constant的时间。
 * construct需要constant的时间。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values

    /**
     * Initializes an empty symbol table.
     * <p>
     * 初始化一个空的符号表
     */
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     * <p>
     * 使用指定的初始容量初始化空符号表。
     *
     * @param capacity the initial capacity
     */
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * <p>
     * 返回此符号表中键 - 值对的数量。
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if this symbol table is empty.
     * <p>
     * 如果此符号表为空，则返回true。
     *
     * @return {@code true} if this symbol table is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     * <p>
     * 如果此符号表包含指定的键，则返回true。
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key};
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; ++i) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        this.keys = temp.keys;
        this.vals = temp.vals;
        this.m = temp.m;
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
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        if (n >= m / 2) { // double table size if 50% full
            resize(2 * m);
        }
        int i = 0;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        ++n;
    }

    /**
     * Returns the value associated with the specified key.
     * <p>
     * 返回与指定键关联的值。
     *
     * @param key the key
     * @return the value associated with {@code key};
     * {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
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
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }
        int i = hash(key); // find position i of key
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        // delete key and associated value
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % m;
        while (keys[i] != null) { // rehash all keys in same cluster
            // delete keys[i] an vals[i] and reInsert
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        --n;
        if (n > 0 && n <= m / 8) {// halves size of array if it's 12.5% full or less
            resize(m / 2);
        }
        assert check();
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * <p>
     * 将此符号表中的所有键作为Iterable返回。
     * 要迭代名为st的符号表中的所有键，请使用foreach表示法：for（Key key：st.keys（））。
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; ++i) {
            if (keys[i] != null) {
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    // integrity check - don't check after each put() because
    // integrity not maintained during a delete()
    private boolean check() {
        // check that hash table is at most 50% full
        if (m < 2 * n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }
        // check that each key in table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) {
                continue;
            } else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }

    /**
     * Unit tests the {@code LinearProbingHashST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        In in = new In(SearchTestResources.Local.tinyST);
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
        assert st.check();
        // print keys
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}