package com.spm.vasylyshyn.facade;


import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceFacade {

    public DeviceDto deviceToDeviceDTO(Device device){
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setNumberOfDevice(device.getNumberOfDevice());
        deviceDto.setPrice(device.getPrice());
        deviceDto.setAddress(device.getAddress());
        deviceDto.setMeasurements(device.getMeasurements());
        deviceDto.setFrequency(device.getFrequency());
        deviceDto.setCounterType(device.getCounterType());
        deviceDto.setCreatedDate(device.getCreatedDate());
        deviceDto.setCantoraName(device.getCantoraName());


        return deviceDto;
    }
}
