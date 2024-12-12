package org.models;



import java.util.Objects;

/**
 * Represents a user in the e-commerce application.
 * Users can have roles such as Buyer, Seller, or Admin.
 */
public class User {
    private int id; // Unique identifier for the user
    private String username;
    private String password; // Stored securely using BCrypt
    private String email;
    private String role; // Roles: Buyer, Seller, Admin

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Parameterized constructor to initialize all fields except id.
     *
     * @param username Username of the user
     * @param password Password of the user (hashed)
     * @param email    Email address of the user
     * @param role     Role of the user (Buyer, Seller, Admin)
     */
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param id       Unique identifier for the user
     * @param username Username of the user
     * @param password Password of the user (hashed)
     * @param email    Email address of the user
     * @param role     Role of the user (Buyer, Seller, Admin)
     */
    public User(int id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters

    /**
     * Gets the user ID.
     *
     * @return User ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id User ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username.
     *
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the hashed password.
     *
     * @return Hashed password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the hashed password.
     *
     * @param password Hashed password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address.
     *
     * @return Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email Email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the user.
     *
     * @return Role (Buyer, Seller, Admin)
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role Role (Buyer, Seller, Admin)
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Overrides the default toString method to provide a string representation of the user.
     *
     * @return String representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * Overrides the equals method to compare users based on their fields.
     *
     * @param o Object to compare
     * @return true if users are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role);
    }

    /**
     * Overrides the hashCode method.
     *
     * @return Hash code of the user
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role);
    }
}
