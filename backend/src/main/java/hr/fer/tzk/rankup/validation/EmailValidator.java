package hr.fer.tzk.rankup.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    // Following RFC 5322 standard
    // Check link: https://datatracker.ietf.org/doc/html/rfc5322
    // Also check: https://www.baeldung.com/java-email-validation-regex for other regexes
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(email)
                .matches();
    }
}
