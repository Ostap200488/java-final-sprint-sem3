-- Drop tables if they exist to start fresh
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       role VARCHAR(20) NOT NULL CHECK (role IN ('Buyer', 'Seller', 'Admin'))
);

-- Create products table
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                          quantity INTEGER NOT NULL CHECK (quantity >= 0),
                          seller_id INTEGER NOT NULL,
                          FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE
);
-- Drop tables if they exist to start fresh
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       role VARCHAR(20) NOT NULL CHECK (role IN ('Buyer', 'Seller', 'Admin'))
);

-- Create products table
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                          quantity INTEGER NOT NULL CHECK (quantity >= 0),
                          seller_id INTEGER NOT NULL,
                          FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE
);
