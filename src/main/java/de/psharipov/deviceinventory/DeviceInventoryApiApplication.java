package de.psharipov.deviceinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class DeviceInventoryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceInventoryApiApplication.class, args);
    }

}
