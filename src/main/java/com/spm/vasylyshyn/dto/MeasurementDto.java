package com.spm.vasylyshyn.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class MeasurementDto {
    private Long id;
    private Long measurement;
    private Boolean isSubmitted;
    private LocalDateTime createdDate;
}
