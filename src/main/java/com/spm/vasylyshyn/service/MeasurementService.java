package com.spm.vasylyshyn.service;

import com.spm.vasylyshyn.dto.MeasurementDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.Measurement;
import com.spm.vasylyshyn.repository.DeviceRepository;
import com.spm.vasylyshyn.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;

    public MeasurementService(MeasurementRepository measurementRepository, DeviceRepository deviceRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
    }

    public void addMeasurement(MeasurementDto measurementDto, Long deviceId){
        Measurement measurement = new Measurement();
        Device device = deviceRepository.findById(deviceId).orElse(null);
        measurement.setMeasurement(measurementDto.getMeasurement());
        measurement.setDevice(device);
        measurementRepository.save(measurement);
    }

    public List<MeasurementDto> getMeasurementsDtoByDeviceNumber(Long deviceNumber) {
        Device device = deviceRepository.findDeviceByNumberOfDevice(deviceNumber).orElse(null);
        return measurementRepository.findMeasurementsDtoByDevice(device).orElse(null);
    }

    public List<Measurement> getAllDisplays() {
        return measurementRepository.findAll();
    }

    public MeasurementDto getLastCollectedMeasurement(Long deviceNumber) {
        Device device = deviceRepository.findDeviceByNumberOfDevice(deviceNumber).orElseThrow();
        return measurementRepository.findDto(device).orElseThrow(()-> new RuntimeException(""));
    }

    public List<Measurement> findAllmeasurementsInRange(Long deviceNumber,LocalDateTime startRange,LocalDateTime endRange) {
        Device device = deviceRepository.findDeviceByNumberOfDevice(deviceNumber).orElseThrow();
        return measurementRepository.findMeasurementsByDeviceAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(device,startRange,endRange);
    }
}
