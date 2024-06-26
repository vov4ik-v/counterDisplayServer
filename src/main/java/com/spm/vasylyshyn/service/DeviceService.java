package com.spm.vasylyshyn.service;

import com.spm.vasylyshyn.exeptions.DeviceAlreadyCreatedException;
import com.spm.vasylyshyn.exeptions.DeviceNotFoundException;
import com.spm.vasylyshyn.model.*;
import com.spm.vasylyshyn.repository.DeviceRepository;
import com.spm.vasylyshyn.repository.UserRepository;
import com.spm.vasylyshyn.request.NeededDataForSendMeasurementsRequest;
import com.spm.vasylyshyn.request.RegisterDeviceRequest;
import com.spm.vasylyshyn.request.StatisticSettingsRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public DeviceService(DeviceRepository deviceRepository, UserService userService, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Device registerDevice(RegisterDeviceRequest request, Principal principal) {
        Device device = new Device();
        Optional<Device> deviceBySerialNumber = deviceRepository.findDeviceBySerialNumber(request.getSerialNumber());
        if (deviceBySerialNumber.isPresent()){
            throw new DeviceAlreadyCreatedException("Device with this serial number already created");
        }
        User user = userService.getCurrentUser(principal);
        device.setSerialNumber(request.getSerialNumber());
        device.setName(request.getName());
        device.setIsCalibrated(false);
        user.getDeviceList().add(device);
        Device device1 = deviceRepository.save(device);
        userRepository.save(user);
        return device1;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    public Device getDeviceBySerialNumber(Long serialNumber) {
        return deviceRepository.findDeviceBySerialNumber(serialNumber).orElseThrow();
    }

    public void calibrateDevice(Long serialNumber) {
        Device device = deviceRepository.findDeviceBySerialNumber(serialNumber).orElseThrow(() -> new DeviceNotFoundException("Device with serial number: " + serialNumber + " not found" ));
        device.setIsCalibrated(true);
        deviceRepository.save(device);
    }

    public void setNeededDataForSendMeasurementsRequest(
            Long serialNumber,
            NeededDataForSendMeasurementsRequest neededDataForSendMeasurementsRequest
    ) {
        Device device = deviceRepository.findDeviceBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceNotFoundException("Device with serial number: " + serialNumber + " not found" ));
        NeededDataForSendMeasurement neededDataForSendMeasurement = new NeededDataForSendMeasurement();
        TelegramSentData telegramSentData = new TelegramSentData();
        PersonalAccount personalAccount =new PersonalAccount();
        telegramSentData.setTelegramChatId(neededDataForSendMeasurementsRequest.getTelegramChatId());
        telegramSentData.setIcon(neededDataForSendMeasurementsRequest.getIcon());
        telegramSentData.setDisplayName(neededDataForSendMeasurementsRequest.getDisplayName());
        personalAccount.setAddress(neededDataForSendMeasurementsRequest.getAddress());
        personalAccount.setPhoneNumber(neededDataForSendMeasurementsRequest.getPhoneNumber());
        neededDataForSendMeasurement.setTelegramSentData(telegramSentData);
        neededDataForSendMeasurement.setRegularity(neededDataForSendMeasurementsRequest.getRegularity());
        neededDataForSendMeasurement.setPersonalAccount(personalAccount);
        device.setNeededDataForSendMeasurement(neededDataForSendMeasurement);
        deviceRepository.save(device);

    }

    public void setStatisticSettings(
            Long serialNumber,
            StatisticSettingsRequest statisticSettingsRequest
    ) {
        Device device = deviceRepository.findDeviceBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceNotFoundException("Device with serial number: " + serialNumber + " not found" ));
        StatisticSettings statisticSettings = new StatisticSettings();
        statisticSettings.setStatisticFrequency(statisticSettingsRequest.getStatisticFrequency());
        statisticSettings.setTariff(statisticSettingsRequest.getTariff());
        device.setStatisticSettings(statisticSettings);
        deviceRepository.save(device);
    }
}
