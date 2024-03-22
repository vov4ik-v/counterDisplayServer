package com.spm.vasylyshyn.request;

import com.spm.vasylyshyn.enums.CType;
import com.spm.vasylyshyn.model.DisplayCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceRequest {
    private String cantoraName;
    private String address;
    private Long frequency;
    private CType counterType;
    private Integer price;
}
