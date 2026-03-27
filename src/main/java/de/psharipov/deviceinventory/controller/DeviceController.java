package de.psharipov.deviceinventory.controller;

import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.dto.DeviceResponse;
import de.psharipov.deviceinventory.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceResponse create(@Valid @RequestBody DeviceRequest request) {
        return deviceService.create(request);
    }

    @GetMapping
    public List<DeviceResponse> findAll() {
        return deviceService.findAll();
    }


    @GetMapping("/{id}")
    public DeviceResponse findById(@PathVariable Long id) {
        return deviceService.findById(id);
    }

    @PutMapping("/{id}")
    public DeviceResponse update(@PathVariable Long id, @Valid @RequestBody DeviceRequest request) {
        return deviceService.update(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deviceService.delete(id);
    }

}
