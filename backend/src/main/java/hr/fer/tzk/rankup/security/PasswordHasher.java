package hr.fer.tzk.rankup.security;

public abstract class PasswordHasher {

    protected int SALT_SIZE = 24;

    public abstract String hashPassword(String password, String salt);

    public abstract boolean checkPassword(String password, String salt, String hash);

    public abstract String getPrefix();

    public abstract String generateSalt();

    protected String addPrefix(String hash) {
        return getPrefix() + hash;
    }
}
