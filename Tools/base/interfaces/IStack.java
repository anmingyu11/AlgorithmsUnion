package base.interfaces;

public interface IStack<Item> {

    boolean isEmpty();

    Item peek();

    Item pop();

    void push(Item item);

    int size();

}
