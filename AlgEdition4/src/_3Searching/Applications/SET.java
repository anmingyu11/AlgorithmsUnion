package _3Searching.Applications;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import base.stdlib.StdOut;

/******************************************************************************
 *  Compilation:  javac SET.java
 *  Execution:    java SET
 *  Dependencies: StdOut.java
 *
 *  Set implementation using Java's TreeSet library.
 *  Does not allow duplicates.
 *
 *  % java SET
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 ******************************************************************************/

/**
 * The {@code SET} class represents an ordered set of comparable keys.
 * It supports the usual <em>add</em>, <em>contains</em>, and <em>delete</em>
 * methods. It also provides ordered methods for finding the <em>minimum</em>,
 * <em>maximum</em>, <em>floor</em>, and <em>ceiling</em> and set methods
 * for <em>union</em>, <em>intersection</em>, and <em>equality</em>.
 * <p>
 * Even though this implementation include the method {@code equals()}, it
 * does not support the method {@code hashCode()} because sets are mutable.
 * <p>
 * This implementation uses a balanced binary search tree. It requires that
 * the key type implements the {@code Comparable} interface and calls the
 * {@code compareTo()} and method to compare two keys. It does not call either
 * {@code equals()} or {@code hashCode()}.
 * The <em>add</em>, <em>contains</em>, <em>delete</em>, <em>minimum</em>,
 * <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> methods take
 * logarithmic time in the worst case.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 * <i>Algorithms in Java, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * SET类表示一组有序的可比键。 它支持通常的add，contains和delete方法。
 * 它还提供了有序的方法，用于查找最小值，最大值，最大值和最大值，并设置union，intersection和equality的方法。
 * 尽管此实现包含方法equals（），但它不支持方法hashCode（），因为集合是可变的。
 * 此实现使用平衡二叉搜索树。 它要求key类型实现Comparable接口并调用compareTo（）和方法来比较两个密钥。
 * 它不会调用equals（）或hashCode（）。
 * <p>
 * 在最坏的情况下，add，contains，delete，minimum，maximum，ceiling和floor方法占用对数时间。
 * size和is-empty操作需要constant的时间。 construct需要constant的时间。
 *
 * @param <Key> the generic type of a key in this set
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;

    /**
     * Initializes an empty set.
     * <p>
     * 初始化一个空集。
     */
    public SET() {
        set = new TreeSet<>();
    }

    /**
     * Initializes a new set that is an independent copy of the specified set.
     * <p>
     * 初始化一个新集合，该集合是指定集合的独立副本。
     *
     * @param x the set to copy
     */
    public SET(SET<Key> x) {
        set = new TreeSet<>(x.set);
    }

    /**
     * Adds the key to this set (if it is not already present).
     * <p>
     * 将key添加到此集合（如果它尚不存在）。
     *
     * @param key the key to add
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void add(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("called add() with a null key");
        }
        set.add(key);
    }

    /**
     * Returns true if this set contains the given key.
     * <p>
     * 如果此set包含给定键，则返回true。
     *
     * @param key the key
     * @return {@code true} if this set contains {@code key};
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("called contains() with a null key");
        }
        return set.contains(key);
    }

    /**
     * Removes the specified key from this set (if the set contains the specified key).
     * <p>
     * 从该集合中删除指定的key（如果该集合包含指定的key）。
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("called delete() with a null key");
        }
        set.remove(key);
    }

    /**
     * Returns the number of keys in this set.
     * <p>
     * 返回此集合中的key。
     *
     * @return the number of keys in this set
     */
    public int size() {
        return set.size();
    }

    /**
     * Returns true if this set is empty.
     * <p>
     * 如果此set为空，则返回true。
     *
     * @return {@code true} if this set is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all of the keys in this set, as an iterator.
     * To iterate over all of the keys in a set named {@code set}, use the
     * foreach notation: {@code for (Key key : set)}.
     * <p>
     * 返回此set中的所有键，作为迭代器。
     * 要迭代一组命名集中的所有键，请使用foreach表示法：for（Key key：set）。
     *
     * @return an iterator to all of the keys in this set
     */
    public Iterator<Key> iterator() {
        return set.iterator();
    }

    /**
     * Returns the largest key in this set.
     * <p>
     * 返回此集合中的最大键。
     *
     * @return the largest key in this set
     * @throws NoSuchElementException if this set is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("called max() with empty set");
        }
        return set.last();
    }

    /**
     * Returns the smallest key in this set.
     * <p>
     * 返回此集合中的最小键。
     *
     * @return the smallest key in this set
     * @throws NoSuchElementException if this set is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty set");
        }
        return set.first();
    }

    /**
     * Returns the smallest key in this set greater than or equal to {@code key}.
     * <p>
     * 返回此set中大于或等于key的最小键。
     *
     * @param key the key
     * @return the smallest key in this set greater than or equal to {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     * @throws NoSuchElementException   if there is no such key
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("called ceiling() with a null key");
        }
        Key k = set.ceiling(key);
        if (k == null) {
            throw new NoSuchElementException("all keys are less than " + key);
        }
        return k;
    }

    /**
     * Returns the largest key in this set less than or equal to {@code key}.
     * <p>
     * 返回此set中小于或等于key的最大键。
     *
     * @param key the key
     * @return the largest key in this set table less than or equal to {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     * @throws NoSuchElementException   if there is no such key
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("called floor() with a null key");
        }
        Key k = set.floor(key);
        if (k == null) {
            throw new NoSuchElementException("all keys are greater than " + key);
        }
        return k;
    }

    /**
     * Returns the union of this set and that set.
     * <p>
     * 返回此set和该set的并集。
     *
     * @param that the other set
     * @return the union of this set and that set
     * @throws IllegalArgumentException if {@code that} is {@code null}
     */
    public SET<Key> union(SET<Key> that) {
        if (that == null) {
            throw new IllegalArgumentException("called union() with a null argument");
        }
        SET<Key> c = new SET<>();
        for (Key x : this) {
            c.add(x);
        }
        for (Key x : that) {
            c.add(x);
        }
        return c;
    }

    /**
     * Returns the intersection of this set and that set.
     * <p>
     * 返回此集合与该集合的交集。
     *
     * @param that the other set
     * @return the intersection of this set and that set
     * @throws IllegalArgumentException if {@code that} is {@code null}
     */
    public SET<Key> intersects(SET<Key> that) {
        if (that == null) {
            throw new IllegalArgumentException("called intersects() with a null argument");
        }
        SET<Key> c = new SET<>();
        if (this.size() < that.size()) {
            for (Key x : this) {
                if (that.contains(x)) {
                    c.add(x);
                }
            }
        } else {
            for (Key x : that) {
                if (this.contains(x)) {
                    c.add(x);
                }
            }
        }
        return c;
    }

    /**
     * Compares this set to the specified set.
     * <p>
     * Note that this method declares two empty sets to be equal
     * even if they are parameterized by different generic types.
     * This is consistent with the behavior of {@code equals()}
     * within Java's Collections framework.
     * <p>
     * 将此集合与指定集合进行比较。
     * 请注意，即使它们由不同的泛型类型参数化，此方法也会声明两个空集相等。
     * 这与Java的Collections框架中equals（）的行为一致。
     *
     * @param other the other set
     * @return {@code true} if this set equals {@code other};
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        SET that = (SET) other;
        return this.set.equals(that.set);
    }

    /**
     * This operation is not supported because sets are mutable.
     *
     * @return does not return a value
     * @throws UnsupportedOperationException if called
     */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because sets are mutable");
    }

    /**
     * Returns a string representation of this set.
     *
     * @return a string representation of this set, enclosed in curly braces,
     * with adjacent keys separated by a comma and a space
     */
    @Override
    public String toString() {
        String s = set.toString();
        return "{ " + s.substring(1, s.length() - 1) + " }";
    }

    /**
     * Unit tests the {@code SET} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SET<String> set = new SET<>();
        StdOut.println("set = " + set);

        // insert some keys
        set.add("www.cs.princeton.edu");
        set.add("www.cs.princeton.edu");    // overwrite old value
        set.add("www.princeton.edu");
        set.add("www.math.princeton.edu");
        set.add("www.yale.edu");
        set.add("www.amazon.com");
        set.add("www.simpsons.com");
        set.add("www.stanford.edu");
        set.add("www.google.com");
        set.add("www.ibm.com");
        set.add("www.apple.com");
        set.add("www.slashdot.com");
        set.add("www.whitehouse.gov");
        set.add("www.espn.com");
        set.add("www.snopes.com");
        set.add("www.movies.com");
        set.add("www.cnn.com");
        set.add("www.iitb.ac.in");


        StdOut.println(set.contains("www.cs.princeton.edu"));
        StdOut.println(!set.contains("www.harvardsucks.com"));
        StdOut.println(set.contains("www.simpsons.com"));
        StdOut.println();

        StdOut.println("ceiling(www.simpsonr.com) = " + set.ceiling("www.simpsonr.com"));
        StdOut.println("ceiling(www.simpsons.com) = " + set.ceiling("www.simpsons.com"));
        StdOut.println("ceiling(www.simpsont.com) = " + set.ceiling("www.simpsont.com"));
        StdOut.println("floor(www.simpsonr.com)   = " + set.floor("www.simpsonr.com"));
        StdOut.println("floor(www.simpsons.com)   = " + set.floor("www.simpsons.com"));
        StdOut.println("floor(www.simpsont.com)   = " + set.floor("www.simpsont.com"));
        StdOut.println();

        StdOut.println("set = " + set);
        StdOut.println();

        // print out all keys in this set in lexicographic order
        for (String s : set) {
            StdOut.println(s);
        }

        StdOut.println();
        SET<String> set2 = new SET<String>(set);
        StdOut.println(set.equals(set2));
    }

}