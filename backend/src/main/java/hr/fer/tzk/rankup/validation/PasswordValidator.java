package hr.fer.tzk.rankup.validation;

import hr.fer.tzk.rankup.utils.PasswordUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return PasswordUtils.validatePassword(password);
    }
}
