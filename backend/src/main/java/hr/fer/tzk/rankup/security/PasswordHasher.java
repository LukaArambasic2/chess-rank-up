package hr.fer.tzk.rankup.security;

/**
 * Abstract class for password hashing. This class provides a blueprint for implementing various
 * password hashing algorithms with methods to hash a password, check a password against a hash,
 * generate a salt, and add a prefix to the hash.
 */
public abstract class PasswordHasher {

    /**
     * Size of the salt in bytes.
     */
    protected int SALT_SIZE = 24;

    /**
     * Hashes the given password with the provided salt.
     *
     * @param password The password to be hashed.
     * @param salt The salt to be used in the hashing process.
     * @return The hashed password.
     */
    public abstract String hashPassword(String password, String salt);

    /**
     * Checks if the given password, when hashed with the provided salt, matches the given hash.
     *
     * @param password The password to check.
     * @param salt The salt used in the hashing process.
     * @param hash The expected hash to check against.
     * @return True if the password matches the hash, false otherwise.
     */
    public abstract boolean checkPassword(String password, String salt, String hash);

    /**
     * Returns the prefix that indicates which hashing algorithm was used.
     * For example, if the prefix is "{argon2id}", it means the argon2id algorithm was used.
     *
     * @return The prefix indicating the hashing algorithm used.
     */
    public abstract String getPrefix();

    /**
     * Generates a new salt for hashing.
     *
     * @return The generated salt.
     */
    public abstract String generateSalt();

    /**
     * Adds the appropriate prefix to the given hash to indicate which hashing algorithm was used.
     *
     * @param hash The hash to which the prefix will be added.
     * @return The hash with the added prefix.
     */
    protected String addPrefix(String hash) {
        return getPrefix() + hash;
    }
}
