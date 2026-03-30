package de.psharipov.deviceinventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.psharipov.deviceinventory.dto.DeviceRequest;
import de.psharipov.deviceinventory.dto.DeviceResponse;
import de.psharipov.deviceinventory.entity.DeviceStatus;
import de.psharipov.deviceinventory.service.DeviceService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DeviceController.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeviceService deviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateDevice() throws Exception {

        DeviceRequest request = new DeviceRequest(
                "router01",
                "192.168.1.1",
                "2001:db8::1",
                DeviceStatus.IN_SERVICE,
                "Max Mustermann"
        );

        DeviceResponse response = new DeviceResponse(
                1L,
                "router01",
                "192.168.1.1",
                "2001:db8::1",
                DeviceStatus.IN_SERVICE,
                "Max Mustermann",
                LocalDateTime.now()
        );

        when(deviceService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.hostname").value("router01"));
    }

    @Test
    void shouldReturnBadRequestForInvalidIPv4() throws Exception {

        DeviceRequest request = new DeviceRequest(
                "router01",
                "999.999.999.999",
                "2001:db8::1",
                DeviceStatus.IN_SERVICE,
                "Max Mustermann"
        );

        mockMvc.perform(post("/api/v1/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundWhenDeviceDoesNotExist() throws Exception {

        when(deviceService.findById(999L))
                .thenThrow(new EntityNotFoundException("Device not found"));

        mockMvc.perform(get("/api/v1/devices/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Resource not found"));
    }
}
