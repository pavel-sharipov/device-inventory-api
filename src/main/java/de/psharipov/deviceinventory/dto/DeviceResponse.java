package de.psharipov.deviceinventory.dto;

import de.psharipov.deviceinventory.entity.DeviceStatus;

import java.time.LocalDateTime;

public record DeviceResponse(
        Long id,
        String hostname,
        String ipv4Address,
        String ipv6Address,
        DeviceStatus status,
        String owner,
        LocalDateTime createdAt
) { }
