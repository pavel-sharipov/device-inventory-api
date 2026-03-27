package de.psharipov.deviceinventory.validation.validator;

import de.psharipov.deviceinventory.validation.annotation.ValidIPv4;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.Inet4Address;
import java.net.InetAddress;

public class IPv4Validator implements ConstraintValidator<ValidIPv4, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) {
            return true;
        }

        try {
            InetAddress address = InetAddress.getByName(value);
            return address instanceof Inet4Address;
        } catch (Exception e) {
            return false;
        }
    }
}