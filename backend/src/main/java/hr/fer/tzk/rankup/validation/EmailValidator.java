package hr.fer.tzk.rankup.validation;

import hr.fer.tzk.rankup.utils.EmailUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return EmailUtils.validateEmail(email);
    }
}
