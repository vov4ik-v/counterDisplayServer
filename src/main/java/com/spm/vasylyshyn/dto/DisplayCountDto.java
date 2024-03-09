package com.spm.vasylyshyn.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DisplayCountDto {
    private Long id;
    private Long displayCount;
}
