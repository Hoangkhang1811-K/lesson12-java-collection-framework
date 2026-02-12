package baitap5;

public class Main {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        int[] values = {27, 14, 35, 10, 19, 31, 42};
        for (int v : values) tree.insert(v);

        System.out.println(tree.search(19)); // true
        System.out.println(tree.search(100)); // false
    }
}
