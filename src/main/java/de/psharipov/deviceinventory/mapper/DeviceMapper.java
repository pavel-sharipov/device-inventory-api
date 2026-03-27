package de.psharipov.deviceinventory.mapper;

import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.dto.DeviceResponse;
import de.psharipov.deviceinventory.entity.Device;

public class DeviceMapper {
    
    public static Device toEntity(DeviceRequest request) {
        return Device.builder()
                .hostname(request.hostname())
                .ipv4Address(request.ipv4Address())
                .ipv6Address(request.ipv6Address())
                .status(request.status())
                .owner(request.owner())
                .build();
    }

    public static DeviceResponse toResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getHostname(),
                device.getIpv4Address(),
                device.getIpv6Address(),
                device.getStatus(),
                device.getOwner(),
                device.getCreatedAt()
        );
    }

    public static void updateEntity(Device device, DeviceRequest request) {
        device.updateHostname(request.hostname());
        device.updateIpv4Address(request.ipv4Address());
        device.updateIpv6Address(request.ipv6Address());
        device.updateStatus(request.status());
        device.updateOwner(request.owner());
    }
}
