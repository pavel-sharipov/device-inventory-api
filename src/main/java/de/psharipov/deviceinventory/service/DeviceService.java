package de.psharipov.deviceinventory.service;

import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.dto.DeviceResponse;

import java.util.List;

public interface DeviceService {

    DeviceResponse create(DeviceRequest request);

    List<DeviceResponse> findAll();

    DeviceResponse findById(Long id);

    DeviceResponse update(Long id, DeviceRequest request);

    void delete(Long id);

}
