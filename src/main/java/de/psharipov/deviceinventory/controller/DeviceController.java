package de.psharipov.deviceinventory.controller;

import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.dto.DeviceResponse;
import de.psharipov.deviceinventory.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
@Tag(name = "Devices", description = "Device inventory management")
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new device")
    public DeviceResponse create(@Valid @RequestBody DeviceRequest request) {
        return deviceService.create(request);
    }

    @GetMapping
    @Operation(summary = "Get all devices")
    public List<DeviceResponse> findAll() {
        return deviceService.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get device by id")
    public DeviceResponse findById(@PathVariable Long id) {
        return deviceService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing device")
    public DeviceResponse update(@PathVariable Long id, @Valid @RequestBody DeviceRequest request) {
        return deviceService.update(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete device")
    public void delete(@PathVariable Long id) {
        deviceService.delete(id);
    }

}
