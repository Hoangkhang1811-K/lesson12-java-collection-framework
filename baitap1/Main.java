package baitap1;

import java.util.*;

public class Main {
     private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean useLinkedList = askUseLinkedList();
        ProductManager manager = new ProductManager(useLinkedList);

        while (true) {
            printMenu(useLinkedList);
            int choice = readInt("Chọn: ", 0, 7);

            switch (choice) {
                case 1:
                    add(manager);
                    break;
                case 2:
                    update(manager);
                    break;
                case 3:
                    remove(manager);
                    break;
                case 4:
                    display(manager.getAll());
                    break;
                case 5:
                    search(manager);
                    break;
                case 6:
                    manager.sortByPriceAsc();
                    System.out.println("Đã sắp xếp tăng dần theo giá.");
                    break;
                case 7:
                    manager.sortByPriceDesc();
                    System.out.println("Đã sắp xếp giảm dần theo giá.");
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static boolean askUseLinkedList() {
        System.out.println("Chọn cấu trúc lưu trữ:");
        System.out.println("1. ArrayList");
        System.out.println("2. LinkedList");
        int c = readInt("Chọn (1-2): ", 1, 2);
        return c == 2;
    }

    private static void printMenu(boolean useLinkedList) {
        System.out.println();
        System.out.println("===== QUẢN LÝ SẢN PHẨM (" + (useLinkedList ? "LinkedList" : "ArrayList") + ") =====");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Sửa sản phẩm theo ID");
        System.out.println("3. Xóa sản phẩm theo ID");
        System.out.println("4. Hiển thị danh sách sản phẩm");
        System.out.println("5. Tìm kiếm sản phẩm theo tên");
        System.out.println("6. Sắp xếp giá tăng dần");
        System.out.println("7. Sắp xếp giá giảm dần");
        System.out.println("0. Thoát");
    }

    private static void add(ProductManager manager) {
        int id = readInt("Nhập ID (số nguyên dương): ", 1, Integer.MAX_VALUE);
        if (manager.findById(id) != null) {
            System.out.println("ID đã tồn tại. Không thể thêm.");
            return;
        }
        String name = readNonEmpty("Nhập tên: ");
        double price = readDouble("Nhập giá (>= 0): ", 0, Double.MAX_VALUE);

        boolean ok = manager.addProduct(new Product(id, name, price));
        System.out.println(ok ? "Thêm thành công." : "Thêm thất bại.");
    }

    private static void update(ProductManager manager) {
        int id = readInt("Nhập ID cần sửa: ", 1, Integer.MAX_VALUE);
        Product p = manager.findById(id);
        if (p == null) {
            System.out.println("Không tìm thấy sản phẩm với ID này.");
            return;
        }
        String name = readNonEmpty("Nhập tên mới: ");
        double price = readDouble("Nhập giá mới (>= 0): ", 0, Double.MAX_VALUE);

        boolean ok = manager.updateProduct(id, name, price);
        System.out.println(ok ? "Cập nhật thành công." : "Cập nhật thất bại.");
    }

    private static void remove(ProductManager manager) {
        int id = readInt("Nhập ID cần xóa: ", 1, Integer.MAX_VALUE);
        boolean ok = manager.removeProduct(id);
        System.out.println(ok ? "Xóa thành công." : "Không tìm thấy sản phẩm để xóa.");
    }

    private static void search(ProductManager manager) {
        String kw = readNonEmpty("Nhập tên cần tìm: ");
        List<Product> rs = manager.searchByName(kw);
        if (rs.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm phù hợp.");
        } else {
            display(rs);
        }
    }

    private static void display(List<Product> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.println("----- DANH SÁCH SẢN PHẨM -----");
        for (Product p : list) {
            System.out.println(p);
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (s != null) {
                s = s.trim();
                if (!s.isEmpty()) return s;
            }
            System.out.println("Không được để trống.");
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v < min || v > max) {
                    System.out.println("Giá trị phải trong khoảng [" + min + ", " + max + "].");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                double v = Double.parseDouble(s);
                if (Double.isNaN(v) || Double.isInfinite(v) || v < min || v > max) {
                    System.out.println("Giá trị phải trong khoảng [" + min + ", " + max + "].");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 12.5).");
            }
        }
    }
}

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return String.format("ID: %d | Tên: %s | Giá: %.2f", id, name, price);
    }
}

class ProductManager {
    private final List<Product> products;

    public ProductManager(boolean useLinkedList) {
        this.products = useLinkedList ? new LinkedList<Product>() : new ArrayList<Product>();
    }

    public boolean addProduct(Product p) {
        if (p == null) return false;
        if (findById(p.getId()) != null) return false;
        products.add(p);
        return true;
    }

    public boolean updateProduct(int id, String newName, double newPrice) {
        Product existing = findById(id);
        if (existing == null) return false;
        existing.setName(newName);
        existing.setPrice(newPrice);
        return true;
    }

    public boolean removeProduct(int id) {
        Product existing = findById(id);
        if (existing == null) return false;
        return products.remove(existing);
    }

    public List<Product> getAll() {
        return new ArrayList<Product>(products);
    }

    public List<Product> searchByName(String keyword) {
        String kw = keyword == null ? "" : keyword.trim().toLowerCase();
        List<Product> result = new ArrayList<Product>();
        for (Product p : products) {
            if (p.getName() != null && p.getName().toLowerCase().contains(kw)) {
                result.add(p);
            }
        }
        return result;
    }

    public void sortByPriceAsc() {
        products.sort(Comparator.comparingDouble(Product::getPrice));
    }

    public void sortByPriceDesc() {
        products.sort(Comparator.comparingDouble(Product::getPrice).reversed());
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}
