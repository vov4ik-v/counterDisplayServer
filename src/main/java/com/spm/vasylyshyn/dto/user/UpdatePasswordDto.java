package com.spm.vasylyshyn.dto.user;

import com.spm.vasylyshyn.annotations.ValidPhoneNumber;
import com.spm.vasylyshyn.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {
    private String newPassword;
    private String oldPassword;
}
