package baitap4;

public class Main {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        // Tạo đúng cây trong hình
        int[] values = {27, 10, 14, 15, 19, 31, 42};
        for (int v : values) tree.insert(v);

        System.out.print("Preorder: ");
        tree.preorder();
    }
}
