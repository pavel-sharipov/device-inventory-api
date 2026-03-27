package de.psharipov.deviceinventory.validation.annotation;

import de.psharipov.deviceinventory.validation.validator.IPv6Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPv6Validator.class)
public @interface ValidIPv6 {
    String message() default "Invalid IPv6 address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
