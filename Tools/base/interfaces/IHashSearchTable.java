package base.interfaces;

public interface IHashSearchTable<Key, Value> {
    void put(Key k, Value v);

    Value get(Key k);

    void delete(Key k);

    boolean contains(Key k);

    int size();

    boolean isEmpty();
}
