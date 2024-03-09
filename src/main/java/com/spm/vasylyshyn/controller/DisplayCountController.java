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

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/display_count/")
public class DisplayCountController {
    private final DisplayCountService displayCountService;

    public DisplayCountController(DisplayCountService displayCountService) {
        this.displayCountService = displayCountService;
    }

    @GetMapping("getDisplayCountsByDeviceNumber/{deviceNumber}")
    public ResponseEntity<List<DisplayCountDto>> getDisplayCountsByDeviceNumber(@PathVariable("deviceNumber") Long deviceNumber){
        List<DisplayCountDto> displayCountsDto = displayCountService.getDisplayCountsDtoByDeviceNumber(deviceNumber);
        return new ResponseEntity<>(displayCountsDto,HttpStatus.OK);
    }


    @GetMapping("getLastCollectedDisplayCount/{deviceNumber}")
    public ResponseEntity<DisplayCountDto> getLastCollectedDisplayCount(@PathVariable("deviceNumber") Long deviceNumber){
//        DisplayCount displayCount = displayCountService.getLastCollectedDisplayCount(deviceNumber);
//        DisplayCountDto displayCountDto = DisplayCountDto.builder().id(displayCount.getId()).displayCount(displayCount.getDisplayCount()).build();
        return new ResponseEntity<>(displayCountService.getLastCollectedDisplayCount(deviceNumber),HttpStatus.OK);
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



    @GetMapping("getInRange/{deviceNumber}")
    public ResponseEntity<List<DisplayCount>> getDisplayCountsInDateRange(@PathVariable("deviceNumber") Long deviceNumber,
                                                                          @RequestParam("startRange") LocalDateTime startRange,
                                                                          @RequestParam("endRange")LocalDateTime endRange
    ){
        List<DisplayCount> allDisplayCountsInRange = displayCountService.findAllDisplayCountsInRange(deviceNumber,startRange,endRange);
        return new ResponseEntity<>(allDisplayCountsInRange,HttpStatus.OK);
    }
}
