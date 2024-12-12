package org.services;


import org.dao.UserDAO;
import org.models.User;
import org.utils.PasswordUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for handling user-related operations.
 */


import java.sql.SQLException;
import java.util.List;

/**
 * Service class for handling user-related operations.
 */
public class UserService {
    private UserDAO userDAO;

    /**
     * Constructor initializes the UserDAO.
     */
    public UserService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Registers a new user.
     *
     * @param username Username chosen by the user
     * @param password Plain-text password entered by the user
     * @param email    Email address of the user
     * @param role     Role of the user (Buyer, Seller, Admin)
     * @return The newly created User object with generated ID
     * @throws SQLException
     */
    public User registerUser(String username, String password, String email, String role) throws SQLException {
        if (userDAO.getUserByUsername(username) != null) {
            throw new SQLException("Username already exists.");
        }
        String hashedPassword = PasswordUtils.hashPassword(password);
        User user = new User(username, hashedPassword, email, role);
        int userId = userDAO.addUser(user);
        user.setId(userId);
        return user;
    }

    /**
     * Authenticates a user with given credentials.
     *
     * @param username Username entered by the user
     * @param password Plain-text password entered by the user
     * @return The authenticated User object if credentials are valid
     * @throws SQLException
     */
    public User authenticateUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && PasswordUtils.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    /**
     * Retrieves all users.
     *
     * @return List of User objects
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    /**
     * Deletes a user by ID.
     *
     * @param id User ID to delete
     * @return true if deletion was successful, false otherwise
     * @throws SQLException
     */
    public boolean deleteUser(int id) throws SQLException {
        return userDAO.deleteUser(id);
    }
}
