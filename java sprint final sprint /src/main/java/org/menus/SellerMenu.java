package org.menus;



import org.models.Product;
import org.services.ProductService;
import org.services.UserService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Menu for Seller users to manage their products.
 */
public class SellerMenu {
    private ProductService productService;
    private Scanner scanner;

    public SellerMenu(UserService userService, Scanner scanner) {
        this.productService = new ProductService();
        this.scanner = scanner;
    }

    /**
     * Displays the Seller menu and handles user input.
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Seller Menu ===");
            System.out.println("1. View My Products");
            System.out.println("2. Add a Product");
            System.out.println("3. Update a Product");
            System.out.println("4. Delete a Product");
            System.out.println("5. Logout");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewMyProducts();
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
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays products belonging to the seller.
     */
    private void viewMyProducts() {
        try {
            System.out.print("Enter your Seller ID: ");
            int sellerId = Integer.parseInt(scanner.nextLine());
            List<Product> products = productService.getProductsBySellerId(sellerId);
            if (products.isEmpty()) {
                System.out.println("You have no products listed.");
            } else {
                System.out.println("\n--- Your Products ---");
                for (Product product : products) {
                    System.out.println(product);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Seller ID.");
        } catch (SQLException e) {
            System.out.println("Error retrieving your products: " + e.getMessage());
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

            System.out.print("Enter your Seller ID: ");
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

            System.out.print("Enter new Seller ID (current: " + existingProduct.getSellerId() + "): ");
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
