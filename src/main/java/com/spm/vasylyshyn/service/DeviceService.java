package com.spm.vasylyshyn.service;

import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.Measurement;
import com.spm.vasylyshyn.model.User;
import com.spm.vasylyshyn.repository.DeviceRepository;
import com.spm.vasylyshyn.request.UpdateDeviceRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device registerDevice(DeviceDto deviceDto) {
        Device device = new Device();
        device.setNumberOfDevice(deviceDto.getNumberOfDevice());
        device.setPrice(deviceDto.getPrice());
        device.setAddress(deviceDto.getAddress());
        device.setFrequency(deviceDto.getFrequency());
        device.setCantoraName(deviceDto.getCantoraName());
        device.setCounterType(deviceDto.getCounterType());
        return deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device updateDevice(Long deviceId, UpdateDeviceRequest updateDeviceRequest) {
        Device device = deviceRepository.findDeviceByNumberOfDevice(deviceId).orElse(null);
        device.setPrice(updateDeviceRequest.getPrice());
        device.setAddress(updateDeviceRequest.getAddress());
        device.setFrequency(updateDeviceRequest.getFrequency());
        device.setCantoraName(updateDeviceRequest.getCantoraName());
        device.setCounterType(updateDeviceRequest.getCounterType());
        deviceRepository.save(device);
        return device;
    }

    public Device getDeviceByNumber(Long deviceNumber) {
        return deviceRepository.findDeviceByNumberOfDevice(deviceNumber).orElseThrow();
    }
}
