package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.common.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.DisplayCount;
import com.spm.vasylyshyn.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
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


    @PostMapping("updateDevice/{deviceId}")
    public ResponseEntity<Device> updateDevice(@PathVariable("deviceId") Long deviceId, @RequestBody DeviceDto deviceDto){
        Device updatedDevice = deviceService.updateDevice(deviceId,deviceDto);
        return new ResponseEntity<>(updatedDevice,HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerDevice(@RequestBody DeviceDto deviceDto){
        deviceService.registerDevice(deviceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
