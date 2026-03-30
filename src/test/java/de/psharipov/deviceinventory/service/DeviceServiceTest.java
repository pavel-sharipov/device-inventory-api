package de.psharipov.deviceinventory.service;

import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.entity.Device;
import de.psharipov.deviceinventory.entity.DeviceStatus;
import de.psharipov.deviceinventory.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    void shouldCreateDevice() {
        DeviceRequest request = new DeviceRequest(
                "router01",
                "192.168.1.1",
                "2001:db8::1",
                DeviceStatus.IN_SERVICE,
                "Max Mustermann"
        );

        Device savedDevice = Device.builder()
                .id(1L)
                .hostname("router01")
                .ipv4Address("192.168.1.1")
                .ipv6Address("2001:db8::1")
                .status(DeviceStatus.IN_SERVICE)
                .owner("Max Mustermann")
                .build();

        when(deviceRepository.existsByIpv4Address(any())).thenReturn(false);
        when(deviceRepository.existsByIpv6Address(any())).thenReturn(false);
        when(deviceRepository.save(any())).thenReturn(savedDevice);

        var result = deviceService.create(request);

        assertEquals("router01", result.hostname());
        verify(deviceRepository, times(1)).save(any());
    }
}
