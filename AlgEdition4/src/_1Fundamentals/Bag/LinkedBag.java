package _1Fundamentals.Bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

import base.stdlib.StdIn;
import base.stdlib.StdOut;

/**
 * The {@code LinkedBag} class represents a bag (or multiset) of
 * generic items. It supports insertion and iterating over the
 * items in arbitrary order.
 * <p>
 * This implementation uses a singly linked list with a non-static nested class Node.
 * See {@link Bag} for a version that uses a static nested class.
 * The <em>add</em>, <em>isEmpty</em>, and <em>size</em> operations
 * take constant time. Iteration takes time proportional to the number of items.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LinkedBag<Item> implements Iterable<Item> {
    private Node first;    // beginning of bag
    private int n;         // number of elements in bag

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Initializes an empty bag.
     */
    public LinkedBag() {
        first = null;
        n = 0;
    }

    /**
     * Is this bag empty?
     *
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this bag.
     *
     * @param item the item to add to this bag
     */
    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        ++n;
    }


    /**
     * Returns an iterator that iterates over the items in the bag.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator over a linked list
    private class ListIterator implements Iterator<Item> {
        private Node current;

        // creates a new iterator
        public ListIterator() {
            current = first;
        }

        // is there a next item in the iterator?
        public boolean hasNext() {
            return current != null;
        }

        // this method is optional in Iterator interface
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // returns the next item in the iterator (and advances the iterator)
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit tests the {@code LinkedBag} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        LinkedBag<String> bag = new LinkedBag<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }

}