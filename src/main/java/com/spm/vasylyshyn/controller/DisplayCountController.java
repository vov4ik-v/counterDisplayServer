package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.common.ApiResponse;
import com.spm.vasylyshyn.dto.DeviceDto;
import com.spm.vasylyshyn.dto.DisplayCountDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.DisplayCount;
import com.spm.vasylyshyn.service.DisplayCountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/display_count/")
public class DisplayCountController {
    private final DisplayCountService displayCountService;

    public DisplayCountController(DisplayCountService displayCountService) {
        this.displayCountService = displayCountService;
    }

    @GetMapping("getDisplayCountsByDeviceNumber/{deviceNumber}")
    public ResponseEntity<List<DisplayCount>> getDeviceByNumber(@PathVariable("deviceNumber") Long deviceNumber){
        List<DisplayCount> displayCounts = displayCountService.getDisplayCountsByDeviceNumber(deviceNumber);
        return new ResponseEntity<>(displayCounts,HttpStatus.OK);

    }

    @GetMapping("getAllDisplayCounts")
    public ResponseEntity<List<DisplayCount>> getAllDevices(){
        List<DisplayCount> displayCounts = displayCountService.getAllDisplays();
        return new ResponseEntity<>(displayCounts,HttpStatus.OK);

    }

    @PostMapping("addDisplayCount")
    public ResponseEntity<ApiResponse> addDisplayCount(@RequestBody DisplayCountDto displayCountDto, @RequestParam("device_id") Long deviceId){
        displayCountService.addDisplayCount(displayCountDto,deviceId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
