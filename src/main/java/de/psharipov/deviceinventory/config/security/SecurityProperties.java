package de.psharipov.deviceinventory.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.security")
public record SecurityProperties(
        String viewerUser,
        String viewerPassword,
        String adminUser,
        String adminPassword
) { }
