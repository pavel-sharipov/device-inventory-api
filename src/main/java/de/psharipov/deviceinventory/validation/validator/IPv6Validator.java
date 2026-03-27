package de.psharipov.deviceinventory.validation.validator;

import de.psharipov.deviceinventory.validation.annotation.ValidIPv6;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.Inet6Address;
import java.net.InetAddress;

public class IPv6Validator implements ConstraintValidator<ValidIPv6, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) {
            return true;
        }

        try {
            InetAddress address = InetAddress.getByName(value);
            return address instanceof Inet6Address;
        } catch (Exception e) {
            return false;
        }
    }
}
