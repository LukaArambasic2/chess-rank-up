package hr.fer.tzk.rankup.utils;

import java.util.regex.Pattern;

public class EmailUtils {
    // Following RFC 5322 standard
    // Check link: https://datatracker.ietf.org/doc/html/rfc5322
    // Also check: https://www.baeldung.com/java-email-validation-regex for other regexes
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    /**
     * Validates an email address by RFC 5322 standard.
     * <p>
     * This method checks if the provided email address matches the RFC 5322 standard pattern.
     * It allows null email addresses, considering them as valid because it may not be available at the moment.
     * </p>
     *
     * @param email the email address to be validated
     * @return {@code true} if the email address is null or matches the RFC 5322 pattern, {@code false} otherwise
     */
    public static boolean validateEmail(String email) {
        return email == null ||  Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }
}
