package com.spm.vasylyshyn.dto.user;

import com.spm.vasylyshyn.annotations.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmailDto {
    @ValidEmail(message = "It should have email format")
    private String email;
}
