package _2Sort;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import base.Base;

import static base.Base.generateArrRandom;
import static base.Base.print;
import static base.Base.println;
import static base.BaseSort.exch;
import static base.BaseSort.less;

public class MaxPQ<Key extends Comparable> implements Iterable<Key> {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Comparable[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Comparable[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            sink(k);
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

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
        Key[] newPQ = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; ++i) {
            newPQ[i] = pq[i];
        }
        pq = newPQ;
    }


    /**
     * Adds a new key to this priority queue.
     *
     * @param x the new key to add to this priority queue
     */
    public void insert(Key x) {

        // double size of array if necessary
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
    }

    /**
     * Removes and returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");

        Key max = pq[1];
        pq[1] = pq[n--];
        pq[n + 1] = null;
        sink(1);
        if (n > 0 && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }

        return max;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && less(pq, k / 2, k)) {
            exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int child = 2 * k;
            if (child < n && less(pq, child, child + 1)) {
                ++child;
            }
            if (!less(pq, k, child)) {
                break;
            }
            exch(pq, k, child);
            k = child;
        }
    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in descending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in descending order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private MaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new MaxPQ<Key>(size());
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
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = generateArrRandom(10, 0, 10);
        println(Arrays.toString(arr));
        MaxPQ<Integer> maxPQ = new MaxPQ<Integer>(arr);
        for (int num : maxPQ) {
            print(num + " : ");
        }
        Base.println("");
    }
}
