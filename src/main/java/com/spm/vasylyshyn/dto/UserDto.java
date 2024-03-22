package com.spm.vasylyshyn.dto;


import com.spm.vasylyshyn.annotations.ValidPhoneNumber;
import com.spm.vasylyshyn.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    @ValidPhoneNumber
    private String phoneNumber;
    private String email;
    private Address address;
    private LocalDateTime createdDate;
}

