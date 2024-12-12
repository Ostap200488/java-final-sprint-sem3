package org.menus;



import org.models.Product;
import org.models.User;
import org.services.ProductService;
import org.services.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Menu for Admin users to manage users and products.
 */
public class AdminMenu {
    private UserService userService;
    private ProductService productService;
    private Scanner scanner;

    public AdminMenu(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.productService = new ProductService();
        this.scanner = scanner;
    }

    /**
     * Displays the Admin menu and handles user input.
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Products");
            System.out.println("3. Logout");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageUsers();
                    break;
                case "2":
                    manageProducts();
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
     * Handles user management operations.
     */
    private void manageUsers() {
        while (true) {
            System.out.println("\n--- Manage Users ---");
            System.out.println("1. View All Users");
            System.out.println("2. Delete a User");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllUsers();
                    break;
                case "2":
                    deleteUser();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays all registered users.
     */
    private void viewAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            System.out.println("\n--- List of Users ---");
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

    /**
     * Deletes a user based on user ID.
     */
    private void deleteUser() {
        try {
            System.out.print("Enter User ID to delete: ");
            int userId = Integer.parseInt(scanner.nextLine());
            if (userService.deleteUser(userId)) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found or could not be deleted.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid User ID.");
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    /**
     * Handles product management operations.
     */
    private void manageProducts() {
        while (true) {
            System.out.println("\n--- Manage Products ---");
            System.out.println("1. View All Products");
            System.out.println("2. Add a Product");
            System.out.println("3. Update a Product");
            System.out.println("4. Delete a Product");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllProducts();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    updateProduct();
                    break;
                case "4":
                    deleteProduct();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays all products.
     */
    private void viewAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            System.out.println("\n--- List of Products ---");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
    }

    /**
     * Adds a new product.
     */
    private void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter product price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter product quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter seller ID: ");
            int sellerId = Integer.parseInt(scanner.nextLine());

            Product product = productService.addProduct(name, price, quantity, sellerId);
            System.out.println("Product added successfully with ID: " + product.getId());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter correct numerical values.");
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    /**
     * Updates an existing product.
     */
    private void updateProduct() {
        try {
            System.out.print("Enter Product ID to update: ");
            int productId = Integer.parseInt(scanner.nextLine());

            Product existingProduct = productService.getProductById(productId);
            if (existingProduct == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.print("Enter new product name (current: " + existingProduct.getName() + "): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = existingProduct.getName();
            }

            System.out.print("Enter new product price (current: " + existingProduct.getPrice() + "): ");
            String priceInput = scanner.nextLine().trim();
            double price = priceInput.isEmpty() ? existingProduct.getPrice() : Double.parseDouble(priceInput);

            System.out.print("Enter new product quantity (current: " + existingProduct.getQuantity() + "): ");
            String quantityInput = scanner.nextLine().trim();
            int quantity = quantityInput.isEmpty() ? existingProduct.getQuantity() : Integer.parseInt(quantityInput);

            System.out.print("Enter new seller ID (current: " + existingProduct.getSellerId() + "): ");
            String sellerIdInput = scanner.nextLine().trim();
            int sellerId = sellerIdInput.isEmpty() ? existingProduct.getSellerId() : Integer.parseInt(sellerIdInput);

            existingProduct.setName(name);
            existingProduct.setPrice(price);
            existingProduct.setQuantity(quantity);
            existingProduct.setSellerId(sellerId);

            if (productService.updateProduct(existingProduct)) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product update failed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter correct numerical values.");
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    /**
     * Deletes a product based on product ID.
     */
    private void deleteProduct() {
        try {
            System.out.print("Enter Product ID to delete: ");
            int productId = Integer.parseInt(scanner.nextLine());
            if (productService.deleteProduct(productId)) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product not found or could not be deleted.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Product ID.");
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }
}
