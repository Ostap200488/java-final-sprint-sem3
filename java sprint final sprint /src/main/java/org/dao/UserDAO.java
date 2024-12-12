package org.dao;



import org.models.User;
import org.utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for User entities.
 */
public class UserDAO {

    /**
     * Inserts a new user into the database.
     *
     * @param user User object to be inserted
     * @return Generated user ID
     * @throws SQLException
     */
    public int addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Password should be already hashed
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("User insertion failed, no ID obtained.");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Retrieves a user by username.
     *
     * @param username Username to search for
     * @return User object if found, null otherwise
     * @throws SQLException
     */
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id User ID to search for
     * @return User object if found, null otherwise
     * @throws SQLException
     */
    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return List of User objects
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }

        return users;
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user User object with updated information
     * @return true if update was successful, false otherwise
     * @throws SQLException
     */
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Password should be already hashed
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Deletes a user from the database by ID.
     *
     * @param id User ID to delete
     * @return true if deletion was successful, false otherwise
     * @throws SQLException
     */
    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Extracts a User object from a ResultSet.
     *
     * @param rs ResultSet containing user data
     * @return User object
     * @throws SQLException
     */
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
