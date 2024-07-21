package hr.fer.tzk.rankup.validation;

import hr.fer.tzk.rankup.utils.JmbagUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JmbagValidator implements ConstraintValidator<ValidJmbag, String> {

    @Override
    public boolean isValid(String jmbag, ConstraintValidatorContext context) {
        return JmbagUtils.validateJmbag(jmbag);
    }
}
