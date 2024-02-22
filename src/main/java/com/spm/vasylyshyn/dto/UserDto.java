package com.spm.vasylyshyn.dto;


import com.spm.vasylyshyn.annotations.ValidPhoneNumber;
import com.spm.vasylyshyn.model.Address;
import lombok.Builder;
import lombok.Data;


@Data
public class UserDto {

    private Long id;
    private String username;
    @ValidPhoneNumber
    private String phoneNumber;
    private String email;
    private Address address;
}

