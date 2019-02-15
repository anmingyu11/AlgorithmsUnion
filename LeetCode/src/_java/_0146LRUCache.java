package _java;

public class _0146LRUCache {

    private abstract static class LRUCache {
        public LRUCache(int capacity) {

        }

        public abstract int get(int key);

        public abstract void put(int key, int value);
    }

    private static class LRUCache1 extends LRUCache {

        public LRUCache1(int capacity) {
            super(capacity);
        }

        @Override
        public int get(int key) {
            return 0;
        }

        @Override
        public void put(int key, int value) {

        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache1(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
