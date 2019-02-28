package _2Sort.PriorityQueue;

/******************************************************************************
 *  Compilation:  javac IndexMinPQ.java
 *  Execution:    java IndexMinPQ
 *  Dependencies: StdOut.java
 *
 *  Minimum-oriented indexed PQ implementation using a binary heap.
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code IndexMinPQ} class represents an indexed priority queue of generic keys.
 * It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 * operations, along with <em>delete</em> and <em>change-the-key</em>
 * methods. In order to let the client refer to keys on the priority queue,
 * an integer between {@code 0} and {@code maxN - 1}
 * is associated with each key—the client uses this integer to specify
 * which key to delete or change.
 * It also supports methods for peeking at the minimum key,
 * testing if the priority queue is empty, and iterating through
 * the keys.
 * <p>
 * This implementation uses a binary heap along with an array to associate
 * keys with integers in the given range.
 * The <em>insert</em>, <em>delete-the-minimum</em>, <em>delete</em>,
 * <em>change-key</em>, <em>decrease-key</em>, and <em>increase-key</em>
 * operations take logarithmic time.
 * The <em>is-empty</em>, <em>size</em>, <em>min-index</em>, <em>min-key</em>,
 * <em>contains</em>, and <em>key-of</em> operations take constant time.
 * Construction takes time proportional to the specified capacity.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * 索引优先队列.
 * <p>
 * 这个数据结构的实现看了很久,一直一脸懵逼,现在我来说下让我懵逼的地方:
 * 首先,索引队列的操作是,插入一个索引为i的值key,不断建立堆,排序以key的顺序进行排序.
 * <p>
 * 1. pq数组用来干什么: 设pq[i]对应的值是keyi,那么对应索引i的key值就是keys[keyi],即keys[pq[i]]
 * pq[i]对应的是在堆中位置为i的元素对应的索引位置,keys[pq[i]].
 * 2. qp数组用来干什么: pq数组是用来对pq进行索引,比如我要查找i索引对应的值key,qp[i]=index,对应的key[pq[index]]即是索引为i对应的key值,即key[pq[qp[i]]],
 * qp[i]对应的是索引i的元素在堆中对应的位置,pq[qp[i]],keys[pq[qp[i]]],可以直接取出索引i对应的value.
 * 3. keys数组用来干什么: 单纯的存储数据,排名第一的值对应的值是 key[pq[1]],当你要找一个对应索引为i的key时,key[pq[qp[i]]],对应的就是key[i];
 * <p>
 * 说一下pq[qp[i]] = qp[pq[i]] = i
 * 索引为i的元素在堆中的位置是qp[i],pq[qp[i]] 对应的元素就是i,pq[qp[i]] = i , keys[pq[qp[i]]] = keyi
 * pq[i]对应的是在堆中位置是i的元素j,qp[pq[i]] = i,这两个等式最好分着来写,毕竟pq[i]对应的i和qp[i]对应的i的意义不一样,
 * 如果这两个等式连在一起会产生歧义.
 *
 * @param <Key> the generic type of key on this priority queue
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    /**
     * Initializes an empty indexed priority queue with indices between {@code 0}
     * and {@code maxN - 1}.
     *
     * @param maxN the keys on this priority queue are index from {@code 0}
     *             {@code maxN - 1}
     * @throws IllegalArgumentException if {@code maxN < 0}
     */
    public IndexMinPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException();
        }
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];                   // make this of length maxN??
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Is {@code i} an index on this priority queue?
     *
     * @param i an index
     * @return {@code true} if {@code i} is an index on this priority queue;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     */
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException();
        }
        return qp[i] != -1;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Associates key with index {@code i}.
     * <p>
     * 插入索引为i,值为key的元素到优先队列
     * <p>
     * 1.加大长度
     * 2.如同正常的优先队列,索引i对应的元素先被插入堆底
     * qp[i] = n;
     * 3.堆底元素的索引是i
     * pq[n] = i
     * 4.索引为i的元素是
     * keys[i] = key;
     * 5.上浮操作调整堆
     *
     * @param i   an index
     * @param key the key to associate with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if there already is an item associated
     *                                  with index {@code i}
     */
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException();
        }
        if (contains(i)) {
            throw new IllegalArgumentException("index is already in the priority queue");
        }
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int minIndex() {
        if (n == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key minKey() {
        if (n == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     * 1.先取出最小元素的位置pq[1]
     * 2.交换与堆尾的位置并调整堆,
     * 3.删除qp[min] =-1,keys[min] = null,pq[n+1]=null
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMin() {
        if (n == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        pq[n + 1] = -1;        // not needed
        return min;
    }

    /**
     * Returns the key associated with index {@code i}.
     *
     * @param i the index of the key to return
     * @return the key associated with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public Key keyOf(int i) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        } else {
            return keys[i];
        }
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     * <p>
     * 改变索引为i的key.
     * 改变完了之后,调整堆,因为索引为i的元素可能会在堆的任何位置,所以对索引为i的元素进行上浮和下沉操作.
     *
     * @param i   the index of the key to change
     * @param key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param i   the index of the key to change
     * @param key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @deprecated Replaced by {@code changeKey(int, Key)}.
     */
    @Deprecated
    public void change(int i, Key key) {
        changeKey(i, key);
    }

    /**
     * Decrease the key associated with index {@code i} to the specified value.
     * <p>
     * 将索引关联的key变小,只需上浮调整堆即可.
     *
     * @param i   the index of the key to decrease
     * @param key decrease the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key >= keyOf(i)}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public void decreaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (keys[i].compareTo(key) <= 0) {
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        }
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * Increase the key associated with index {@code i} to the specified value.
     * <p>
     * 将索引关联的key,变大只需下沉调整堆
     *
     * @param i   the index of the key to increase
     * @param key increase the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key <= keyOf(i)}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public void increaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (keys[i].compareTo(key) >= 0) {
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
        }
        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * Remove the key associated with index {@code i}.
     * <p>
     * 删除操作,将索引i对应的元素与堆底交换
     * 索引i可能在堆中任何的位置,需要下沉且上浮保证堆有序.
     *
     * @param i the index of the key to remove
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public void delete(int i) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }


    /***************************************************************************
     * General helper functions.
     ***************************************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    /**
     * 交换
     * pq表示的其实就是堆,首先要对堆进行交换
     * 然后对qp进行替换,qp[i]指索引为i的元素对应在堆中的位置pq[i]指的是在堆中位置为i的元素,
     * 所以将堆中位置为i的元素pq[i]的指定在堆中的位置变换为i qp[pq[i]] = i;
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    /***************************************************************************
     * Heap helper functions.
     ***************************************************************************/
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }


    /***************************************************************************
     * Iterators.
     ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }


    /**
     * Unit tests the {@code IndexMinPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
   /*     // insert a bunch of strings
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }*/
        testInteger();
    }

    // Help me understand
    private static void testInteger() {
        int[] arr = {3, 4, 6, 5, 1, 2, 7, 0, 9, 8};
        final int n = arr.length;
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(n);
        for (int i = 0; i < n; ++i) {
            pq.insert(i, arr[i]);
        }
    }
}