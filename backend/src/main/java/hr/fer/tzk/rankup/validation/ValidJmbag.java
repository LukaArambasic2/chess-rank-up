package hr.fer.tzk.rankup.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = JmbagValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidJmbag {
    // Error message if JMBAG is valid
    String message() default "Invalid JMBAG";

    // No group for this annotation
    // It works by itself and itself only
    Class<?>[] groups() default {};

    // No additional information needed for payload
    Class<? extends Payload>[] payload() default {};
}
