package org;

import org.menus.AdminMenu;
import org.menus.BuyerMenu;
import org.menus.SellerMenu;
import org.models.User;
import org.services.UserService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Main class to initiate the application.
 */
public class Main {
    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Welcome to the E-commerce Application ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Handles user registration.
     */
    private static void register() {
        try {
            System.out.println("\n--- User Registration ---");
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            System.out.print("Enter role (Buyer/Seller/Admin): ");
            String role = scanner.nextLine().trim();

            if (!role.equalsIgnoreCase("Buyer") && !role.equalsIgnoreCase("Seller") && !role.equalsIgnoreCase("Admin")) {
                System.out.println("Invalid role. Please enter Buyer, Seller, or Admin.");
                return;
            }

            User user = userService.registerUser(username, password, email, role);
            System.out.println("Registration successful! Your User ID is: " + user.getId());
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    /**
     * Handles user login.
     */
    private static void login() {
        try {
            System.out.println("\n--- User Login ---");
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            User user = userService.authenticateUser(username, password);
            if (user != null) {
                System.out.println("Login successful! Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
                navigateMenu(user);
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    /**
     * Directs the user to their respective menu based on role.
     *
     * @param user Authenticated User object
     */
    private static void navigateMenu(User user) {
        switch (user.getRole().toLowerCase()) {
            case "admin":
                AdminMenu adminMenu = new AdminMenu(userService, scanner);
                adminMenu.showMenu();
                break;
            case "buyer":
                BuyerMenu buyerMenu = new BuyerMenu(userService, scanner);
                buyerMenu.showMenu();
                break;
            case "seller":
                SellerMenu sellerMenu = new SellerMenu(userService, scanner);
                sellerMenu.showMenu();
                break;
            default:
                System.out.println("Unknown role. Cannot navigate to menu.");
        }
    }

    /**
     * Exits the application gracefully.
     */
    private static void exitApplication() {
        System.out.println("Thank you for using the E-commerce Application. Goodbye!");
        try {
            org.utils.DBConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
        System.exit(0);
    }
}
