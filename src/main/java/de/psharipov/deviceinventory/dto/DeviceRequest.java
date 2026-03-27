package de.psharipov.deviceinventory.dto;

import de.psharipov.deviceinventory.entity.DeviceStatus;
import de.psharipov.deviceinventory.validation.annotation.ValidIPv4;
import de.psharipov.deviceinventory.validation.annotation.ValidIPv6;
import de.psharipov.deviceinventory.validation.annotation.ValidIpCombination;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ValidIpCombination
public record DeviceRequest(

        @Schema(example = "router01")
        @NotBlank
        @Size(max = 100)
        String hostname,

        @Schema(example = "192.168.1.1")
        @ValidIPv4
        String ipv4Address,

        @Schema(example = "2001:db8::1")
        @ValidIPv6
        String ipv6Address,

        @Schema(example = "IN_SERVICE")
        @NotNull
        DeviceStatus status,

        @Schema(example = "Max Mustermann")
        @NotBlank
        @Size(max = 100)
        String owner
) { }
