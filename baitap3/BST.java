package baitap3;
interface Tree<E> {
    boolean insert(E e);
    void inorder();
    int getSize();
}
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
            if (cmp == 0) return false; // không cho trùng
            parent = current;
            current = (cmp < 0) ? current.left : current.right;
        }

        if (e.compareTo(parent.element) < 0) parent.left = new Node<>(e);
        else parent.right = new Node<>(e);

        size++;
        return true;
    }

    // ===== DELETE theo 2 trường hợp trong bài =====
    public boolean delete(E e) {
        if (e == null) return false;

        // 1) Tìm current (node cần xoá) và parent (cha của current)
        Node<E> parent = null;
        Node<E> current = root;

        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp == 0) break;
            parent = current;
            current = (cmp < 0) ? current.left : current.right;
        }

        if (current == null) return false; // không tìm thấy

        // 2) Case 1: current không có con trái
        if (current.left == null) {
            Node<E> child = current.right; // có thể null

            if (parent == null) {
                // current là root
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            size--;
            return true;
        }

        // 3) Case 2: current có con trái
        // Tìm rightMost trong cây con trái của current
        Node<E> parentOfRightMost = current;
        Node<E> rightMost = current.left;

        while (rightMost.right != null) {
            parentOfRightMost = rightMost;
            rightMost = rightMost.right;
        }

        // Copy giá trị rightMost lên current
        current.element = rightMost.element;

        // Xoá rightMost: rightMost không có con phải, chỉ có thể có con trái
        if (parentOfRightMost.right == rightMost) {
            parentOfRightMost.right = rightMost.left;
        } else {
            // Trường hợp đặc biệt: rightMost chính là current.left
            parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true;
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



