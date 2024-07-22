package hr.fer.tzk.rankup.utils;

import java.util.regex.Pattern;

public class EmailUtils {
    // Following RFC 5322 standard
    // Check link: https://datatracker.ietf.org/doc/html/rfc5322
    // Also check: https://www.baeldung.com/java-email-validation-regex for other regexes
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean validateEmail(String email) {
        return email == null ||  Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }
}
