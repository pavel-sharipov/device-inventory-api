package de.psharipov.deviceinventory.dto;

import de.psharipov.deviceinventory.entity.DeviceStatus;
import de.psharipov.deviceinventory.validation.annotation.ValidIPv4;
import de.psharipov.deviceinventory.validation.annotation.ValidIPv6;
import de.psharipov.deviceinventory.validation.annotation.ValidIpCombination;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ValidIpCombination
public record DeviceRequest(

        @NotBlank
        @Size(max = 100)
        String hostname,

        @ValidIPv4
        String ipv4Address,

        @ValidIPv6
        String ipv6Address,

        @NotNull
        DeviceStatus status,

        @NotBlank
        @Size(max = 100)
        String owner
) { }
