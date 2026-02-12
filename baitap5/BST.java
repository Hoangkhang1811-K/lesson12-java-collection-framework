package baitap5;

class BST<E extends Comparable<E>> implements Tree<E> {

    private static class Node<E> {
        E element;
        Node<E> left, right;

        Node(E element) {
            this.element = element;
        }
    }

    private Node<E> root;
    private int size = 0;

    @Override
    public boolean insert(E e) {
        if (e == null) throw new IllegalArgumentException("Element cannot be null");

        if (root == null) {
            root = new Node<>(e);
            size++;
            return true;
        }

        Node<E> parent = null;
        Node<E> current = root;

        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp == 0) return false;
            parent = current;
            current = (cmp < 0) ? current.left : current.right;
        }

        if (e.compareTo(parent.element) < 0) parent.left = new Node<>(e);
        else parent.right = new Node<>(e);

        size++;
        return true;
    }

    // ===== SEARCH theo mô tả bài =====
    public boolean search(E e) {
        if (e == null) return false;

        Node<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return true;
        }
        return false;
    }

    @Override
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<E> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.element + " ");
        inorder(node.right);
    }

    @Override
    public int getSize() {
        return size;
    }
}
