package com.spm.vasylyshyn.dto;


import lombok.Builder;
import lombok.Data;


@Data
public class UserDto {

    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
}

