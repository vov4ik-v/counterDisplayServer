package com.spm.vasylyshyn.service;

import com.spm.vasylyshyn.dto.DisplayCountDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.DisplayCount;
import com.spm.vasylyshyn.repository.DeviceRepository;
import com.spm.vasylyshyn.repository.DisplayCountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DisplayCountService {
    private final DisplayCountRepository displayCountRepository;
    private final DeviceRepository deviceRepository;

    public DisplayCountService(DisplayCountRepository displayCountRepository, DeviceRepository deviceRepository) {
        this.displayCountRepository = displayCountRepository;
        this.deviceRepository = deviceRepository;
    }

    public void addDisplayCount(DisplayCountDto displayCountDto, Long deviceId){
        DisplayCount displayCount = new DisplayCount();
        Device device = deviceRepository.findById(deviceId).orElse(null);
        displayCount.setDisplayCount(displayCountDto.getDisplayCount());
        displayCount.setDevice(device);
        displayCountRepository.save(displayCount);
    }

    public List<DisplayCountDto> getDisplayCountsDtoByDeviceNumber(Long deviceNumber) {
        Device device = deviceRepository.findDeviceByNumberOfDevice(deviceNumber).orElse(null);
        return displayCountRepository.findDisplayCountsDtoByDevice(device).orElse(null);
    }

    public List<DisplayCount> getAllDisplays() {
        return displayCountRepository.findAll();
    }

    public DisplayCountDto getLastCollectedDisplayCount(Long deviceNumber) {
        Device device = deviceRepository.findDeviceByNumberOfDevice(deviceNumber).orElseThrow();
        return displayCountRepository.findDto(device).orElseThrow(()-> new RuntimeException(""));
    }

    public List<DisplayCount> findAllDisplayCountsInRange(Long deviceNumber,LocalDateTime startRange,LocalDateTime endRange) {
        Device device = deviceRepository.findDeviceByNumberOfDevice(deviceNumber).orElseThrow();
        return displayCountRepository.findDisplayCountsByDeviceAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(device,startRange,endRange);
    }
}
