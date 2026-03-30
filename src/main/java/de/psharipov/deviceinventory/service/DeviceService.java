package de.psharipov.deviceinventory.service;

import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.dto.DeviceResponse;
import de.psharipov.deviceinventory.entity.DeviceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceService {

    DeviceResponse create(DeviceRequest request);

    Page<DeviceResponse> findAll(DeviceStatus status, Pageable pageable);

    DeviceResponse findById(Long id);

    DeviceResponse update(Long id, DeviceRequest request);

    void delete(Long id);

}
