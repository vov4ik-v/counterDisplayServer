package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.common.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device/")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerDevice(@RequestBody DeviceDto deviceDto){
        deviceService.registerDevice(deviceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
