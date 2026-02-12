package baitap3;

public class Main {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        // Ví dụ giống hình minh hoạ: 20, 10, 40, 16, 30, 80, 14, 27, 50
        int[] values = {20, 10, 40, 16, 30, 80, 14, 27, 50};
        for (int v : values) tree.insert(v);

        System.out.print("Inorder ban dau: ");
        tree.inorder();

        System.out.println("Delete 10: " + tree.delete(10)); // case 1 (10 không có con trái)
        System.out.print("Inorder sau khi xoá 10: ");
        tree.inorder();

        System.out.println("Delete 20: " + tree.delete(20)); // case 2 (20 có con trái)
        System.out.print("Inorder sau khi xoá 20: ");
        tree.inorder();

        System.out.println("Size: " + tree.getSize());
    }
}
