package org.services;



import org.dao.ProductDAO;
import org.models.Product;


import java.sql.SQLException;
import java.util.List;

/**
 * Service class for handling product-related operations.
 */
public class ProductService {
    private ProductDAO productDAO;

    /**
     * Constructor initializes the ProductDAO.
     */
    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Adds a new product.
     *
     * @param name      Name of the product
     * @param price     Price of the product
     * @param quantity  Quantity in stock
     * @param sellerId  ID of the seller
     * @return The newly created Product object with generated ID
     * @throws SQLException
     */
    public Product addProduct(String name, double price, int quantity, int sellerId) throws SQLException {
        Product product = new Product(name, price, quantity, sellerId);
        int productId = productDAO.addProduct(product);
        product.setId(productId);
        return product;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id Product ID
     * @return Product object if found, else null
     * @throws SQLException
     */
    public Product getProductById(int id) throws SQLException {
        return productDAO.getProductById(id);
    }

    /**
     * Retrieves all products.
     *
     * @return List of Product objects
     * @throws SQLException
     */
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    /**
     * Retrieves products by seller ID.
     *
     * @param sellerId Seller ID
     * @return List of Product objects
     * @throws SQLException
     */
    public List<Product> getProductsBySellerId(int sellerId) throws SQLException {
        return productDAO.getProductsBySellerId(sellerId);
    }

    /**
     * Updates an existing product.
     *
     * @param product Product object with updated details
     * @return true if update was successful, else false
     * @throws SQLException
     */
    public boolean updateProduct(Product product) throws SQLException {
        return productDAO.updateProduct(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id Product ID
     * @return true if deletion was successful, else false
     * @throws SQLException
     */
    public boolean deleteProduct(int id) throws SQLException {
        return productDAO.deleteProduct(id);
    }

    /**
     * Searches for products by name.
     *
     * @param name Name or partial name of the product
     * @return List of Product objects matching the search criteria
     * @throws SQLException
     */
    public List<Product> searchProductsByName(String name) throws SQLException {
        List<Product> allProducts = getAllProducts();
        List<Product> filteredProducts = new java.util.ArrayList<>();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
