package com.spm.vasylyshyn.dto;


import com.spm.vasylyshyn.model.Address;
import lombok.Builder;
import lombok.Data;


@Data
public class UserDto {

    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
    private Address address;
}

