package de.psharipov.deviceinventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hostname", nullable = false, length = 100)
    private String hostname;

    @Column(name = "ipv4_address", nullable = false, unique = true, length = 15)
    private String ipv4Address;

    @Column(name = "ipv6_address", nullable = false, unique = true, length = 45)
    private String ipv6Address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private DeviceStatus status;

    @Column(name = "owner", nullable = false, length = 100)
    private String owner;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public void updateHostname(String hostname) {
        this.hostname = hostname;
    }

    public void updateIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    public void updateIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public void updateStatus(DeviceStatus status) {
        this.status = status;
    }

    public void updateOwner(String owner) {
        this.owner = owner;
    }

}
