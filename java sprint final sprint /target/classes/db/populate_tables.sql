-- Insert sample users
INSERT INTO users (username, password, email, role) VALUES
                                                        ('buyer1', '$2a$10$DowJonesExampleHash1', 'buyer1@example.com', 'Buyer'),
                                                        ('seller1', '$2a$10$DowJonesExampleHash2', 'seller1@example.com', 'Seller'),
                                                        ('admin1', '$2a$10$DowJonesExampleHash3', 'admin1@example.com', 'Admin');

-- Insert sample products
INSERT INTO products (name, price, quantity, seller_id) VALUES
                                                            ('Laptop', 999.99, 10, 2),
                                                            ('Smartphone', 499.49, 25, 2),
                                                            ('Headphones', 149.99, 50, 2);
