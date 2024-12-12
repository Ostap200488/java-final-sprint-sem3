package org.dao;

import org.models.Product;




import org.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Product entities.
 */
public class ProductDAO {

    /**
     * Inserts a new product into the database.
     *
     * @param product Product object to be inserted
     * @return Generated product ID
     * @throws SQLException
     */
    public int addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setInt(4, product.getSellerId());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Product insertion failed, no ID obtained.");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Retrieves a product by ID.
     *
     * @param id Product ID to search for
     * @return Product object if found, null otherwise
     * @throws SQLException
     */
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractProductFromResultSet(rs);
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Retrieves all products from the database.
     *
     * @return List of Product objects
     * @throws SQLException
     */
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }

        return products;
    }

    /**
     * Retrieves all products for a specific seller.
     *
     * @param sellerId Seller ID to filter products
     * @return List of Product objects
     * @throws SQLException
     */
    public List<Product> getProductsBySellerId(int sellerId) throws SQLException {
        String sql = "SELECT * FROM products WHERE seller_id = ?";
        List<Product> products = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sellerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }

        return products;
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product Product object with updated information
     * @return true if update was successful, false otherwise
     * @throws SQLException
     */
    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ?, seller_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setInt(4, product.getSellerId());
            pstmt.setInt(5, product.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Deletes a product from the database by ID.
     *
     * @param id Product ID to delete
     * @return true if deletion was successful, false otherwise
     * @throws SQLException
     */
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
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
     * Extracts a Product object from a ResultSet.
     *
     * @param rs ResultSet containing product data
     * @return Product object
     * @throws SQLException
     */
    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setSellerId(rs.getInt("seller_id"));
        return product;
    }
}
