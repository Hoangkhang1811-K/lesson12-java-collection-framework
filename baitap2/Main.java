package baitap2;


    public class Main {
        public static void main(String[] args) {
            BST<Integer> tree = new BST<>();

            // Tạo đúng cây trong hình
            int[] values = {27, 14, 35, 10, 19, 31, 42};
            for (int v : values) tree.insert(v);

            System.out.print("Postorder: ");
            tree.postorder(); // 10 19 14 31 42 35 27
        }
}
