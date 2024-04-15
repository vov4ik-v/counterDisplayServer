package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.response.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.request.UpdateDeviceRequest;
import com.spm.vasylyshyn.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/device/")
// TODO: Update all response from Device to DeviceDTO
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
    public ResponseEntity<Device> updateDevice(@PathVariable("deviceId") Long deviceId, @RequestBody UpdateDeviceRequest updateDeviceRequest){
        Device updatedDevice = deviceService.updateDevice(deviceId,updateDeviceRequest);
        return new ResponseEntity<>(updatedDevice,HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<Device> registerDevice(@RequestBody DeviceDto deviceDto){
        Device createdDevice = deviceService.registerDevice(deviceDto);
        return new ResponseEntity<>(createdDevice,HttpStatus.CREATED);
    }

}
