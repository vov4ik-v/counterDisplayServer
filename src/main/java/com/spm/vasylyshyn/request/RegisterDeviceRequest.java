package com.spm.vasylyshyn.request;

import com.spm.vasylyshyn.enums.CType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDeviceRequest {
    private Long deviceNumber;
    private CType deviceType;
    private String address;
    private String password;
}
