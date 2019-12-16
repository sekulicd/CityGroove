package com.sekulicd.citygroove.core.application.identity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank(message = "userName is mandatory")
    private String userName;
    @NotBlank(message = "password is mandatory")
    private String password;
}
