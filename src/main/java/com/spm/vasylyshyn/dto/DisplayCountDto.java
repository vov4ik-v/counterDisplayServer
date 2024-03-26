package com.spm.vasylyshyn.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class DisplayCountDto {
    private Long id;
    private Long displayCount;
    private LocalDateTime createdDate;
}
