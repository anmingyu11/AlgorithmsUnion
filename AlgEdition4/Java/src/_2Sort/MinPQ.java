package _2Sort;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import base.Base;

import static base.Base.generateArrRandom;
import static base.BaseSort.exch;
import static base.BaseSort.greater;

public class MinPQ<Key extends Comparable> implements Iterable<Key> {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MinPQ(int initCapacity) {
        pq = (Key[]) new Comparable[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MinPQ() {
        this(1);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * <p>
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MinPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Comparable[n + 1];
        for (int i = 0; i < n; ++i) {
            pq[i + 1] = keys[i];
        }
        for (int k = n / 2; k >= 1; --k) {
            sink(k);
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
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    // helper function to double the size of the heap array

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; ++i) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param x the key to add to this priority queue
     */
    public void insert(Key x) {
        if (pq.length - 1 == n) {
            resize(2 * pq.length);
        }

        pq[++n] = x;
        swim(n);
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");

        Key min = pq[1];
        pq[1] = pq[n];
        pq[n--] = null;
        sink(1);
        if ((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }

        return min;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {
        while (k / 2 >= 1
                && greater(pq, k / 2, k)) {
            exch(pq, k / 2, k);
            k = k / 2;
        }

    }

    private void sink(int k) {
        while (2 * k < n) {
            int child = 2 * k;
            if (child < n && greater(pq, child, child + 1)) {
                ++child;
            }
            if (!greater(pq, k, child)) {
                break;
            }
            exch(pq, k, child);
            k = child;
        }
    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in ascending order.
     * <p>
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        // create a new pq
        private MinPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new MinPQ<Key>(size());
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

    /**
     * Unit tests the {@code MinPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Integer[] arr = generateArrRandom(10, 0, 10);
        Base.println(Arrays.toString(arr));
        MinPQ<Integer> minPQ = new MinPQ<Integer>(arr);
        for (int num : minPQ) {
            Base.print(num + " : ");
        }
        Base.println("");
    }

}