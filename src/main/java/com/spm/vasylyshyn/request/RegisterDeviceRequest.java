package com.spm.vasylyshyn.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDeviceRequest {
    private Long serialNumber;
    private String name;
}
