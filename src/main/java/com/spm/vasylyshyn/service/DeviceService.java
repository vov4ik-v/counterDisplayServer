package com.spm.vasylyshyn.service;

import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.User;
import com.spm.vasylyshyn.repository.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void registerDevice(DeviceDto deviceDto){
        Device device = new Device();
        device.setNumberOfDevice(deviceDto.getNumberOfDevice());
        device.setPrice(deviceDto.getPrice());
        device.setCantoraName(deviceDto.getCantoraName());
        device.setCounterType(deviceDto.getCounterType());
        deviceRepository.save(device);
    }
}
