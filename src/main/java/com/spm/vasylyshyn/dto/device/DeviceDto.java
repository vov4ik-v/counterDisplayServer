package com.spm.vasylyshyn.dto.device;

import com.spm.vasylyshyn.enums.CounterType;
import com.spm.vasylyshyn.model.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
    private Long serialNumber;
    private String name;
    private Boolean isCalibrated;
}