package org.models;

import java.util.Objects;

/**
 * Represents a product in the e-commerce application.
 */
public class Product {
    private int id; // Unique identifier for the product
    private String name;
    private double price;
    private int quantity;
    private int sellerId; // References the User ID of the seller

    /**
     * Default constructor.
     */
    public Product() {
    }

    /**
     * Parameterized constructor to initialize all fields except id.
     *
     * @param name     Name of the product
     * @param price    Price of the product
     * @param quantity Quantity available
     * @param sellerId ID of the seller (User ID)
     */
    public Product(String name, double price, int quantity, int sellerId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param id       Unique identifier for the product
     * @param name     Name of the product
     * @param price    Price of the product
     * @param quantity Quantity available
     * @param sellerId ID of the seller (User ID)
     */
    public Product(int id, String name, double price, int quantity, int sellerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    // Getters and Setters

    /**
     * Gets the product ID.
     *
     * @return Product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the product ID.
     *
     * @param id Product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     *
     * @return Product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name Product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product price.
     *
     * @return Product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price Product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the product quantity.
     *
     * @return Product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the product quantity.
     *
     * @param quantity Product quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the seller ID.
     *
     * @return Seller ID (User ID)
     */
    public int getSellerId() {
        return sellerId;
    }

    /**
     * Sets the seller ID.
     *
     * @param sellerId Seller ID (User ID)
     */
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * Overrides the default toString method to provide a string representation of the product.
     *
     * @return String representation of the product
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sellerId=" + sellerId +
                '}';
    }

    /**
     * Overrides the equals method to compare products based on their fields.
     *
     * @param o Object to compare
     * @return true if products are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                sellerId == product.sellerId &&
                Objects.equals(name, product.name);
    }

    /**
     * Overrides the hashCode method.
     *
     * @return Hash code of the product
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity, sellerId);
    }
}