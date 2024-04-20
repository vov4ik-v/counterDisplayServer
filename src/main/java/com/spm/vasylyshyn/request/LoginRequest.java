package com.spm.vasylyshyn.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "Username or email cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;

}
