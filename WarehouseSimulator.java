import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Product
class Product {
    String name;
    int quantity;
    int threshold;

    public Product(String name, int quantity, int threshold) {
        this.name = name;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public void restock(int amount) {
        this.quantity += amount;
    }

    public boolean isLowStock() {
        return quantity < threshold;
    }

    @Override
    public String toString() {
        return name + " | Qty: " + quantity + " | Threshold: " + threshold;
    }
}

// Class representing the Warehouse
class Warehouse {
    private ArrayList<Product> products = new ArrayList<>();

    // Add new product
    public void addProduct(String name, int quantity, int threshold) {
        products.add(new Product(name, quantity, threshold));
        System.out.println("✅ Product added successfully.");
    }

    // Display all products
    public void displayStock() {
        if (products.isEmpty()) {
            System.out.println("⚠️ No products in warehouse.");
            return;
        }
        System.out.println("\n--- Current Stock ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
    }

    // Display low-stock products
    public void checkLowStock() {
        System.out.println("\n--- Low Stock Items ---");
        boolean found = false;
        for (Product p : products) {
            if (p.isLowStock()) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("✅ All items are sufficiently stocked.");
        }
    }

    // Restock a product
    public void restockProduct(int index, int amount) {
        if (index >= 0 && index < products.size()) {
            products.get(index).restock(amount);
            System.out.println("✅ Restocked successfully.");
        } else {
            System.out.println("⚠️ Invalid product selection.");
        }
    }
}

// Main Class
public class WarehouseSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        int choice;

        do {
            System.out.println("\n====== Warehouse Stock Restocking Simulator ======");
            System.out.println("1. Add Product");
            System.out.println("2. View Current Stock");
            System.out.println("3. Check Low-Stock Items");
            System.out.println("4. Restock a Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    sc.nextLine(); // clear buffer
                    String name = sc.nextLine();
                    System.out.print("Enter initial quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter threshold level: ");
                    int th = sc.nextInt();
                    warehouse.addProduct(name, qty, th);
                    break;

                case 2:
                    warehouse.displayStock();
                    break;

                case 3:
                    warehouse.checkLowStock();
                    break;

                case 4:
                    warehouse.displayStock();
                    System.out.print("Select product number to restock: ");
                    int index = sc.nextInt() - 1;
                    System.out.print("Enter quantity to add: ");
                    int amount = sc.nextInt();
                    warehouse.restockProduct(index, amount);
                    break;

                case 5:
                    System.out.println("🚪 Exiting... Thank you!");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
