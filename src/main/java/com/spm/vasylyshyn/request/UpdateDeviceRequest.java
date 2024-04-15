package com.spm.vasylyshyn.request;

import com.spm.vasylyshyn.enums.CounterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceRequest {
    private String cantoraName;
    private String address;
    private Long frequency;
    private CounterType counterType;
    private Integer price;
}
