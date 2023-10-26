package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    Optional<Device> findDeviceByNumberOfDevice(Long numberOfDevice);

}
