package de.psharipov.deviceinventory.validation.validator;

import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.validation.annotation.ValidIpCombination;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IpCombinationValidator implements ConstraintValidator<ValidIpCombination, DeviceRequest> {

    @Override
    public boolean isValid(DeviceRequest value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return (value.ipv4Address() != null && !value.ipv4Address().isBlank())
                || (value.ipv6Address() != null && !value.ipv6Address().isBlank());
    }
}
