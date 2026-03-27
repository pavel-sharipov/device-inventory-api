package de.psharipov.deviceinventory.validation.annotation;

import de.psharipov.deviceinventory.validation.validator.IpCombinationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpCombinationValidator.class)
public @interface ValidIpCombination {

    String message() default "Either IPv4 or IPv6 address must be provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
