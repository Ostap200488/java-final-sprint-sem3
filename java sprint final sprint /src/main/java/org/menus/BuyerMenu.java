package org.menus;



import org.models.Product;
import org.services.ProductService;
import org.services.UserService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Menu for Buyer users to view products.
 */
public class BuyerMenu {
    private ProductService productService;
    private Scanner scanner;

    public BuyerMenu(UserService userService, Scanner scanner) {
        this.productService = new ProductService();
        this.scanner = scanner;
    }

    /**
     * Displays the Buyer menu and handles user input.
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Buyer Menu ===");
            System.out.println("1. View All Products");
            System.out.println("2. Search Products by Name");
            System.out.println("3. Logout");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllProducts();
                    break;
                case "2":
                    searchProducts();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays all available products.
     */
    private void viewAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            System.out.println("\n--- Available Products ---");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
    }

    /**
     * Searches for products by name.
     */
    private void searchProducts() {
        try {
            System.out.print("Enter product name to search: ");
            String name = scanner.nextLine().trim();
            List<Product> products = productService.searchProductsByName(name);
            if (products.isEmpty()) {
                System.out.println("No products found with the name: " + name);
            } else {
                System.out.println("\n--- Search Results ---");
                for (Product product : products) {
                    System.out.println(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching products: " + e.getMessage());
        }
    }
}
