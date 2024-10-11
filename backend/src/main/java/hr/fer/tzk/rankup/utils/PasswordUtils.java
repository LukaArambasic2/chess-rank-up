package hr.fer.tzk.rankup.utils;

import java.util.regex.Pattern;

public class PasswordUtils {

    /**
     * Password can contain only: uppercase and lowercase letters, numbers and special characters '.', '?', '!', '_' and '-'
     */
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9!?._-]+$";

    public static boolean validatePassword(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }
}
