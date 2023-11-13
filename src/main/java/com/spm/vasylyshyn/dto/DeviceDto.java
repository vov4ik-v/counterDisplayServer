package com.spm.vasylyshyn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spm.vasylyshyn.enums.CType;
import com.spm.vasylyshyn.model.DisplayCount;
import com.spm.vasylyshyn.model.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DeviceDto {
    private Long numberOfDevice;
    private String cantoraName;
    private List<DisplayCount> displayCounts;
    private String address;
    private Long frequency;
    private CType counterType;
    private Integer price;
    private String createdDate;

}