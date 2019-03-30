package base.interfaces;

public interface IQueue<Item> {
    boolean isEmpty();

    int size();

    Item peek();

    void enqueue(Item item);

    Item dequeue();

}
