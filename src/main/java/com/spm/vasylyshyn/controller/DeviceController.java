package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.common.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device/")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("getDeviceByNumber/{deviceNumber}")
    public ResponseEntity<Device> getDeviceByNumber(@PathVariable("deviceNumber") Long deviceNumber){
        Device device = deviceService.getDeviceByNumber(deviceNumber);
        return new ResponseEntity<>(device,HttpStatus.OK);

    }

    @GetMapping("getAllDevices")
    public ResponseEntity<List<Device>> getAllDevices(){
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices,HttpStatus.OK);

    }

    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerDevice(@RequestBody DeviceDto deviceDto){
        deviceService.registerDevice(deviceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
