package _3Searching.HashTable;

/******************************************************************************
 *  Compilation:  javac SeparateChainingHashST.java
 *  Execution:    java SeparateChainingHashST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/34hash/tinyST.txt
 *
 *  A symbol table implemented with a separate-chaining hash table.
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
 * The {@code SeparateChainingHashST} class represents a symbol table of generic
 * key-value pairs.
 * It supports the usual <em>put</em>, <em>get</m>, <em>contains</em>,
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
 * This implementation uses a separate chaining hash table. It requires that
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
 * {@link LinearProbingHashST},
 * <p>
 * SeparateChainingHashST类表示通用键值对的符号表。
 * 它支持通常的put，get，contains，delete，size和is-empty方法。
 * 它还提供了一种迭代所有键的keys()方法。
 * 符号表实现关联数组抽象：当将值与已存在于符号表中的键相关联时，约定是将旧值替换为新值。
 * 与java.util.Map不同，此类使用值不能为null的约定
 * - 将与键关联的值设置为null等效于从符号表中删除键。
 * <p>
 * 此实现使用链地址法的哈希表。
 * 它要求键类型覆盖equals（）和hashCode（）方法。
 * 每次put，contains或remove操作的预期时间是constant的，受均匀散列假设的影响。 size和is-empty 需要恒定的时间。
 * construct需要恒定的时间。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables

    /**
     * Initializes an empty symbol table.
     * <p>
     * 初始化一个空符号表。
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with {@code m} chains.
     * <p>
     * 初始化拥有m个链的符号表.
     *
     * @param m the initial number of chains
     */
    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for (int i = 0; i < m; ++i) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * <p>
     * 返回此符号表中键-值对的数量。
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

    /**
     * Returns the value associated with the specified key in this symbol table.
     * <p>
     * 返回与此符号表中指定键关联的值。
     *
     * @param key the key
     * @return the value associated with {@code key} in the symbol table;
     * {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        int i = hash(key);
        return st[i].get(key);
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
        if (n >= 10 * m) { // double table size if average length of list >= 10
            resize(2 * m);
        }
        int i = hash(key);
        if (!st[i].contains(key)) {
            ++n;
        }
        st[i].put(key, val);
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
        int i = hash(key);
        if (st[i].contains(key)) {
            --n;
        }
        st[i].delete(key);
        if (m > INIT_CAPACITY && n <= 2 * m) { // halve table size if average length of list <= 2
            resize(m / 2);
        }
    }

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }

    /**
     * Unit tests the {@code SeparateChainingHashST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        In in = new In(SearchTestResources.Local.tinyST);
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
        // print keys
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

}