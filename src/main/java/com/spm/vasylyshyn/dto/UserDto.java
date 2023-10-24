package com.spm.vasylyshyn.dto;


import lombok.Builder;
import lombok.Data;


@Data
public class UserDto {

    private Long id;
    private Integer avatarId;
    private String username;
}

