package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.common.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.dto.DisplayCountDto;
import com.spm.vasylyshyn.service.DisplayCountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/display_count/")
public class DisplayCountController {
    private final DisplayCountService displayCountService;

    public DisplayCountController(DisplayCountService displayCountService) {
        this.displayCountService = displayCountService;
    }


    @PostMapping("add")
    public ResponseEntity<ApiResponse> registerDevice(@RequestBody DisplayCountDto displayCountDto, @RequestParam("device_id") Long deviceId){
        displayCountService.addDisplayCount(displayCountDto,deviceId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
