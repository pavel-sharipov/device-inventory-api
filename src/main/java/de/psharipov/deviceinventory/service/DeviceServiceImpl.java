package de.psharipov.deviceinventory.service;


import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.dto.DeviceResponse;
import de.psharipov.deviceinventory.entity.Device;
import de.psharipov.deviceinventory.exception.DuplicateResourceException;
import de.psharipov.deviceinventory.mapper.DeviceMapper;
import de.psharipov.deviceinventory.repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Override
    public DeviceResponse create(DeviceRequest request) {
        checkDuplicateIp(request);
        Device device = DeviceMapper.toEntity(request);
        Device saved = deviceRepository.save(device);
        return DeviceMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeviceResponse> findAll() {
        return deviceRepository.findAll()
                .stream()
                .map(DeviceMapper::toResponse)
                .toList();

    }

    @Override
    public DeviceResponse findById(Long id) {
        Device device = getDeviceOrThrow(id);
        return DeviceMapper.toResponse(device);
    }

    @Override
    public DeviceResponse update(Long id, DeviceRequest request) {
        Device device = getDeviceOrThrow(id);
        if (!device.getIpv4Address().equals(request.ipv4Address())
                && deviceRepository.existsByIpv4Address(request.ipv4Address())) {
            throw new DuplicateResourceException("IPv4 address already exists");
        }

        if (!device.getIpv6Address().equals(request.ipv6Address())
                && deviceRepository.existsByIpv6Address(request.ipv6Address())) {
            throw new DuplicateResourceException("IPv6 address already exists");
        }

        device.updateHostname(request.hostname());
        device.updateIpv4Address(request.ipv4Address());
        device.updateIpv6Address(request.ipv6Address());
        device.updateStatus(request.status());
        device.updateOwner(request.owner());

        Device updated = deviceRepository.save(device);

        return DeviceMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        Device device = getDeviceOrThrow(id);
        deviceRepository.delete(device);
    }

    private void checkDuplicateIp(DeviceRequest request) {
        if (deviceRepository.existsByIpv4Address(request.ipv4Address())) {
            throw new DuplicateResourceException("IPv4 address already exists");
        }

        if (deviceRepository.existsByIpv6Address(request.ipv6Address())) {
            throw new DuplicateResourceException("IPv6 address already exists");
        }
    }

    private Device getDeviceOrThrow(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found with id " + id));
    }

}
