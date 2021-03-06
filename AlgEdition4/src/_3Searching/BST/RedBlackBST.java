package _3Searching.BST;

/******************************************************************************
 *  Compilation:  javac RedBlackBST.java
 *  Execution:    java RedBlackBST < input.txt
 *  Dependencies: StdIn.java StdOut.java  
 *  Data files:   https://algs4.cs.princeton.edu/33balanced/tinyST.txt  
 *
 *  A symbol table implemented using a left-leaning red-black BST.
 *  This is the 2-3 version.
 *
 *  Note: commented out assertions because DrJava now enables assertions
 *        by default.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java RedBlackBST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 ******************************************************************************/

import java.util.NoSuchElementException;

import _1Fundamentals.Queue.Queue;
import _3Searching.Applications.ST;
import _3Searching.ElementarySymbolTables.BinarySearchST;
import _3Searching.ElementarySymbolTables.SequentialSearchST;
import _3Searching.HashTable.LinearProbingHashST;
import _3Searching.HashTable.SeparateChainingHashST;
import _3Searching.SearchTestResources;
import base.stdlib.In;
import base.stdlib.StdOut;
import base.util.ArraysUtil;
import base.util.ShuffleUtil;

/**
 * The {@code BST} class represents an ordered symbol table of generic
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
 * This implementation uses a left-leaning red-black BST. It requires that
 * the key type implements the {@code Comparable} interface and calls the
 * {@code compareTo()} and method to compare two keys. It does not call either
 * {@code equals()} or {@code hashCode()}.
 * The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>,
 * <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> operations each take
 * logarithmic time in the worst case, if the tree becomes unbalanced.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/33balanced">Section 3.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * For other implementations of the same API, see {@link ST}, {@link BinarySearchST},
 * {@link SequentialSearchST}, {@link BST},
 * {@link SeparateChainingHashST}, {@link LinearProbingHashST}
 * <p>
 * BST类表示通用键值对的有序符号表。
 * 它支持通常的put，get，contains，delete，size和is-empty方法。
 * 它还提供了有序的方法来查找minimum, maximum, floor, 和 ceiling。
 * 它还提供了一种迭代所有键的键方法。
 * 符号表实现关联数组抽象：当将值与已存在于符号表中的键相关联时，约定是将旧值替换为新值。
 * 与java.util.Map不同，此类使用值不能为null的约定
 * - 将与键关联的值设置为null等效于从符号表中删除键。
 * <p>
 * 此实现使用左倾红黑树。
 * 它要求key实现Comparable接口并调用compareTo（）和方法来比较两个key。
 * 它不会调用equals（）或hashCode（）。
 * 如果树变得不平衡，则在最坏的情况下，
 * put，contains，remove，minimum，maximum，ceiling和floor操作都采用对数时间。
 * size和is-empty需要constant的时间。构造需要constant的时间。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     * <p>
     * 初始化一个空符号表。
     */
    public RedBlackBST() {
    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) {
            return BLACK;
        } else {
            return x.color;
        }
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * <p>
     * 返回此符号表中键 - 值对的数量。
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Is this symbol table empty?
     * <p>
     * 这个符号表是空的吗？
     *
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    /**
     * Returns the value associated with the given key.
     * <p>
     * 返回与给定键关联的值。
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    /**
     * Does this symbol table contain the given key?
     * <p>
     * 此符号表是否包含给定的键？
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/

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
        root = put(root, key, val);
        root.color = BLACK;
        assert check();
    }

    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        // 此处与balanced是不同的请注意.
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/

    /**
     * Removes the smallest key and associated value from the symbol table.
     * <p>
     * 从符号表中删除最小的键和关联值。
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }
        if (!isRed(root.left) && !isRed(root.right)) { // isRed(root.right) necessary?
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
        assert check();
    }

    // delete the key-value pair with the minimum key rooted at h

    /**
     * 设h是当前结点.
     * 如果我们删除的是2-结点,则树会失去平衡,
     * 那么我们需要在向下递归查找最小值的过程中,将树配平,即不允许h的子结点存在2-结点,从而保证删除成功.
     * 重点: 保证每次递归的结点h,h都是红的.
     * <p>
     * 如要删除,则有两种情况:
     * 1.要删除的结点是3-结点
     * 2.要删除的结点是2-结点
     * <p>
     * 第一种情况:
     * 直接删除即可
     * 第二种:
     * 我们需要在递归的过程中保证下一个遍历的结点h.left是3-结点,即我们可以通过配平保证向下递归的过程中,每一个被遍历的子节点h.left都是红的.
     * 首先,当前被遍历的h结点必定是红的
     * 那么当h.left不存在,直接删除当前结点,如果存在,且h.left是2-结点
     * 有两种情况:
     * 第一种,存在兄弟结点是3-结点,那么从兄弟结点中转移一个结点过来,使当前结点成为3-结点
     * 第二种,如果兄弟结点也是2-结点,那么将父结点和两个子节点配成一个3-结点.继续向下遍历.
     *
     * @param h
     * @return
     */
    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
        assert check();
    }

    /**
     * 原理跟deleteMin类似
     * 但有所区别,因为我们的树是左倾的,所以不可能会出现右结点是红色的,
     * 所以,我们会先对当前结点(设为h)进行一次检查,如果它的左结点是红色的,直接右旋将左子节点转到当前节点的右面,将h.right配红
     *
     * @param h
     * @return
     */
    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
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
        if (!contains(key)) { // very useful
            return;
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
        assert check();
    }

    /**
     * 删除操作所涉及的情况过多了,所以我们要用另一个方式去做:
     * <p>
     * 如同删除最大值和最小值一样,在递归向下的过程中,依然去将向下过程中经过的结点配平,
     * <p>
     * 当在左子树递归的时候,设当前结点为h
     * 用deleteMin()的配平方式不断向下递归
     * 当在右子树递归的时候,设当前结点为h
     * 用deleteMax()的配平方式不断向下递归
     * <p>
     * <p>
     * 如果h就是要查找的结点
     * 1.如果h有左子节点并且左子节点是红,右旋,并继续向右子树遍历.
     * 2.h一定是红的,如果h没有左子节点或者左子结点是黑色结点,且没有右子结点,那么直接以删除最大结点的方式删除,返回0.
     * 3.如果h的左子结点是黑,且右子结点是2-结点(h.right==black && h.right.left == black),那么进行配平,从左子节点借一个结点,
     * 如果左子结点也是2-结点,那么将h,h.left,h.right配成一个3-结点, 将当前节点用她的右子树的最小结点进行覆盖,并删除她的右子树的最小结点.
     * <p>
     * 最后调整树
     *
     * @param h
     * @param key
     * @return
     */
    private Node delete(Node h, Key key) {
        assert get(h, key) != null;
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h); // 向右配平
            }
            if (key.compareTo(h.key) == 0 && (h.right == null)) {
                return null; // 要删除的h一定是红的.
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h); // 向右配平
            }
            if (key.compareTo(h.key) == 0) {//删除,与二叉树的删除是一个原理.
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/

    /**
     * 右旋操作
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 左旋操作.
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children

    /**
     * 设当前结点为h,
     * 转换颜色, h一定是两个子结点颜色的反色,即
     * 满足,h!=null && h.left!=null && h.right !=null
     * 且满足两者条件其一,
     * 1. h是黑色结点,两个子节点都是红色结点. 即这个结点是由h结点的父结点连接的4-结点,翻转颜色让她和她的父节点合并成一个结点,将她的两个子节点的颜色变黑
     * 2. h是红结点,两个子节点都是黑结点. 即这个结点是一个3-结点,将这个结点下沉,和她的两个子节点合并成一个4-结点,变成了h是一个由h的父节点连接的4-结点
     *
     * @param h
     */
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) && isRed(h.left) && isRed(h.right))
                || (isRed(h) && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.

    /**
     * 设当前结点为h
     * 必须满足的条件是h!=null且h是红色结点,
     * <p>
     * 翻转颜色
     * 这样可以将父节点下沉一个结点给左子节点,即将h结点和她的两个子节点合并,成为一个由其父结点连接的4-结点
     * <p>
     * 但如果她的右子节点是3-结点,那么我们是直接从右子节点借一个结点到左子节点,
     * 翻转颜色过后,通过左旋她的右子节点,再左旋h结点,翻转颜色,这样左子节点就变成了3-结点
     *
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h) {
        assert (h != null);
        assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * 设当前结点为h
     * 必须满足的条件是h!=null且h是红色结点,
     * <p>
     * 翻转颜色,这个不必多说.
     * <p>
     * 如果h结点的左子结点是一个3-结点,将她的左子节点借一个结点到右子节点
     * 先右旋h结点,然后翻转颜色即可做到.
     *
     * @param h
     * @return
     */
    private Node moveRedRight(Node h) {
        assert (h != null);
        assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant

    /**
     * 第一次判断,如果h.right是红的,左旋.左面是不是红色的其实对结果并没有影响,因为就算左面是红的,后面的两次操作也会调整过去,
     * 但是如果加上!isRed(h.left) 会增加效率,因为如果左面也是红的的话,可以不进行rotateLeft,直接flipColor,这样少了一次左旋操作.如果左面是黑的,直接旋转即可
     * 第二次判断,如果h.left是红色的,而且h.left.left也是红的,则需要右旋,这样会制造3-结点,然后转换颜色将父节点送上去.
     * 第三次判断,如果左右都是红的,出现了3-结点,转换颜色将父节点送上去.
     *
     * @param h
     * @return
     */
    private Node balance(Node h) {
        assert (h != null);
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


    /***************************************************************************
     *  Utility functions.
     ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        assert x != null;
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(root).key;
    }

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        assert x != null;
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * <p>
     * 返回符号表中小于或等于key的最大键。
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node x = floor(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * <p>
     * 返回符号表中大于或等于key的最小键。
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table.
     * <p>
     * 返回排名为k的符号表中的key 这是符号表st中的第（k + 1）小键。
     *
     * @param k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *                                  <em>n</em>–1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        return select(root, k).key;
    }

    // the key of rank k in the subtree rooted at x
    private Node select(Node x, int k) {
        assert x != null;
        assert k >= 0 && k < size(x);
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     * <p>
     * 返回符号表中小于key的键的数量.
     *
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, root);
    }

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * <p>
     * 将符号表中的所有键作为Iterable返回。
     * 要迭代名为st的符号表中的所有键，请使用foreach表示法：for（Key key ： st.keys（））。
     *
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     * <p>
     * 返回给定范围内符号表中的所有键，作为Iterable。
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in the sybol table between {@code lo}
     * (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *                                  is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    /**
     * Returns the number of keys in the symbol table in the given range.
     * <p>
     * 返回给定范围内符号表中的键数。
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return the number of keys in the sybol table between {@code lo}
     * (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *                                  is {@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
        }
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) {
            StdOut.println("Not in symmetric order");
        }
        if (!isSizeConsistent()) {
            StdOut.println("Subtree counts not consistent");
        }
        if (!isRankConsistent()) {
            StdOut.println("Ranks not consistent");
        }
        if (!is23()) {
            StdOut.println("Not a 2-3 tree");
        }
        if (!isBalanced()) {
            StdOut.println("Not balanced");
        }
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) {
            return true;
        }
        if (x.size != size(x.left) + size(x.right) + 1) {
            return false;
        }
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (key.compareTo(select(rank(key))) != 0) {
                return false;
            }
        }
        return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }
        if (isRed(x.right)) {
            return false;
        }
        if (x != root && isRed(x) && isRed(x.left)) {
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) {
                black++;
            }
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) {
            return black == 0;
        }
        if (!isRed(x)) {
            black--;
        }
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    /**
     * Unit tests the {@code RedBlackBST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int[] a = ArraysUtil.generateFromTo(1, 100);
        ShuffleUtil.shuffle(a);
        final int n = a.length;
        final int nChars = chars.length;

        RedBlackBST<Integer, Character> st = new RedBlackBST<>();
        for (int i = 0; i < n; ++i) {
            st.put(i, chars[i % nChars]);
        }
        assert st.check();
//        for (Integer s : st.keys()) {
//            StdOut.println(s + " " + st.get(s));
//        }
//        StdOut.println();
        StdOut.println("size : " + st.size());
        StdOut.println("height : " + st.height());
        StdOut.println(Math.log(st.size()));// log_e
    }

    private static void test1() {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        In in = new In(SearchTestResources.Local.tinyST);
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
        assert st.check();
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
    }
}