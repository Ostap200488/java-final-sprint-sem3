package org.utils;



import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for handling password hashing and verification.
 */
public class PasswordUtils {

    /**
     * Hashes a plain-text password using BCrypt.
     *
     * @param password Plain-text password
     * @return Hashed password
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Checks if a plain-text password matches the hashed password.
     *
     * @param passwordPlain Plain-text password
     * @param passwordHashed Hashed password
     * @return true if passwords match, false otherwise
     */
    public static boolean checkPassword(String passwordPlain, String passwordHashed) {
        if (passwordHashed == null || !passwordHashed.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hashed password format");
        }
        return BCrypt.checkpw(passwordPlain, passwordHashed);
    }
}
