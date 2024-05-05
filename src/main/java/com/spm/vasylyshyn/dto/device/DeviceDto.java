package com.spm.vasylyshyn.dto.device;

import com.spm.vasylyshyn.enums.CounterType;
import com.spm.vasylyshyn.model.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
    private Long numberOfDevice;
    private String cantoraName;
    private List<Measurement> measurements;
    private String address;
    private Long frequency;
    private CounterType counterType;
    private Integer price;
    private LocalDateTime createdDate;
}