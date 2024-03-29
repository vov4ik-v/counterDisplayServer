package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.response.ApiResponse;
import com.spm.vasylyshyn.dto.MeasurementDto;
import com.spm.vasylyshyn.model.Measurement;
import com.spm.vasylyshyn.service.MeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/display_count/")
public class MeasurementController {
    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("getMeasurementsByDeviceNumber/{deviceNumber}")
    public ResponseEntity<List<MeasurementDto>> getMeasurementsByDeviceNumber(@PathVariable("deviceNumber") Long deviceNumber){
        List<MeasurementDto> measurementsDto = measurementService.getMeasurementsDtoByDeviceNumber(deviceNumber);
        return new ResponseEntity<>(measurementsDto,HttpStatus.OK);
    }


    @GetMapping("getLastCollectedMeasurement/{deviceNumber}")
    public ResponseEntity<MeasurementDto> getLastCollectedMeasurement(@PathVariable("deviceNumber") Long deviceNumber){
//        Measurement measurement = measurementService.getLastCollectedMeasurement(deviceNumber);
//        MeasurementDto measurementDto = MeasurementDto.builder().id(measurement.getId()).measurement(measurement.getmeasurement()).build();
        return new ResponseEntity<>(measurementService.getLastCollectedMeasurement(deviceNumber),HttpStatus.OK);
    }

    @GetMapping("getAllMeasurements")
    public ResponseEntity<List<Measurement>> getAllDevices(){
        List<Measurement> measurements = measurementService.getAllDisplays();
        return new ResponseEntity<>(measurements,HttpStatus.OK);

    }

    @PostMapping("addmeasurement")
    public ResponseEntity<ApiResponse> addmeasurement(@RequestBody MeasurementDto measurementDto, @RequestParam("device_id") Long deviceId){
        measurementService.addMeasurement(measurementDto,deviceId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @GetMapping("getInRange/{deviceNumber}")
    public ResponseEntity<List<Measurement>> getMeasurementInDateRange(@PathVariable("deviceNumber") Long deviceNumber,
                                                                          @RequestParam("startRange") LocalDateTime startRange,
                                                                          @RequestParam("endRange")LocalDateTime endRange
    ){
        List<Measurement> allMeasurementsInRange = measurementService.findAllmeasurementsInRange(deviceNumber,startRange,endRange);
        return new ResponseEntity<>(allMeasurementsInRange,HttpStatus.OK);
    }
}
