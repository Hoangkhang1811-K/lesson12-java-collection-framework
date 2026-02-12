package baitap2;

public interface Tree<E> {
    boolean insert(E e);
    void inorder();
    int getSize();
}
