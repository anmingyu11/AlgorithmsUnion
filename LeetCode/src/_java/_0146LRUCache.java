package _java;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import base.Base;


/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */

/**
 * LRUCache cache = new LRUCache( 2 capacity  );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class _0146LRUCache extends Base {

    private static abstract class LRUCache {

        public LRUCache() {
        }

        public LRUCache(int capacity) {

        }

        public abstract int get(int key);

        public abstract void put(int key, int value);

    }


    // Runtime: 57 ms, faster than 99.33% of Java online submissions for LRU Cache.
    // Memory Usage: 51.4 MB, less than 95.53% of Java online submissions for LRU Cache.
    private static class LRUCache1 extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache1(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    // Runtime: 210 ms, faster than 9.50% of Java online submissions for LRU Cache.
    // Memory Usage: 66.9 MB, less than 10.53% of Java online submissions for LRU Cache.
    private static class LRUCache2 extends LRUCache {

        private final int cap;
        private final Map<Integer, Integer> map;
        private final LinkedList<Long> doubleLinkedList;

        public LRUCache2(int capacity) {
            cap = capacity;
            map = new HashMap<>();
            doubleLinkedList = new LinkedList<>();
        }

        @Override
        public int get(int key) {
            Integer val = map.get(key);
            if (val == null) {
                return -1;
            }
            long hash = hash(key, val);
            doubleLinkedList.remove(hash);
            doubleLinkedList.addLast(hash);
            return val;
        }

        @Override
        public void put(int key, int val) {
            Integer valM = map.get(key);
            if (valM != null) {
                doubleLinkedList.remove(hash(key, valM));
                long hash = hash(key, val);
                doubleLinkedList.addLast(hash);
            } else {
                doubleLinkedList.addLast(hash(key, val));
            }
            map.put(key, val);
            if (doubleLinkedList.size() > cap) {
                Long hash = doubleLinkedList.removeFirst();
                int removeKey = (int) (hash >> 32);
                map.remove(removeKey);
            }
        }

        private Long hash(int key, int val) {
            long hash = key;
            hash <<= 32;
            hash ^= val;
            return hash;
        }

    }

    // Runtime: 57 ms, faster than 99.24% of Java online submissions for LRU Cache.
    // Memory Usage: 53.8 MB, less than 82.57% of Java online submissions for LRU Cache.
    // 答案给的
    public class LRUCache3 extends LRUCache {

        private class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }

        private void addNode(DLinkedNode node) {
            /**
             * Always add the new node right after head.
             */
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            /**
             * Remove an existing node from the linked list.
             */
            DLinkedNode prev = node.prev;
            DLinkedNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        private void moveToHead(DLinkedNode node) {
            /**
             * Move certain node in between to the head.
             */
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail() {
            /**
             * Pop the current tail.
             */
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        private HashMap<Integer, DLinkedNode> cache =
                new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache3(int capacity) {
            this.size = 0;
            this.capacity = capacity;

            head = new DLinkedNode();
            // head.prev = null;

            tail = new DLinkedNode();
            // tail.next = null;

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;

            // move the accessed node to the head;
            moveToHead(node);

            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);

            if (node == null) {
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                cache.put(key, newNode);
                addNode(newNode);

                ++size;

                if (size > capacity) {
                    // pop the tail
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // update the value.
                node.value = value;
                moveToHead(node);
            }
        }
    }

    public static void main(String[] args) {
        test1(new LRUCache2(2));
        test2(new LRUCache2(2));
    }

    private static void test1(LRUCache cache) {
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        println(cache.get(1));
        println(cache.get(2));
    }

    private static void test2(LRUCache cache) {
        cache.put(1, 1);
        cache.put(2, 2);
        println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        println(cache.get(1));       // returns -1 (not found)
        println(cache.get(3));       // returns 3
        println(cache.get(4));       // returns 4
    }
}
