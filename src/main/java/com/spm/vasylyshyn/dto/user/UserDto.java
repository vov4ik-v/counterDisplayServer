package com.spm.vasylyshyn.dto.user;


import com.spm.vasylyshyn.annotations.ValidPhoneNumber;
import com.spm.vasylyshyn.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    @ValidPhoneNumber
    private String phoneNumber;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private Address defaultAddressForNewDevices;

}

