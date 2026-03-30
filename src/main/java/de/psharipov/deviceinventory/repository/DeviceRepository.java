package de.psharipov.deviceinventory.repository;

import de.psharipov.deviceinventory.entity.Device;
import de.psharipov.deviceinventory.entity.DeviceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Page<Device> findByStatus(DeviceStatus status, Pageable pageable);

    boolean existsByIpv4Address(String ipv4Address);

    boolean existsByIpv6Address(String ipv6Address);
}
