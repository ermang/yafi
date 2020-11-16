package com.eg.yafi.dto.in;

import javax.validation.constraints.NotBlank;

public class CreateUser {
    @NotBlank(message = "username can not be blank")
    public String username;
    @NotBlank(message = "username can not be blank")
    public String password;
}
