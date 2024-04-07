package com.spm.vasylyshyn.request;


import com.spm.vasylyshyn.enums.CounterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDeviceRequest {
    private Long deviceNumber;
    private CounterType deviceType;
    private String address;
    private String password;
}
