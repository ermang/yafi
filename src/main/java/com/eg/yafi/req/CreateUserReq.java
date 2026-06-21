package com.eg.yafi.req;

import jakarta.validation.constraints.NotBlank;

public class CreateUserReq {
    @NotBlank(message = "username can not be blank")
    public String username;
    @NotBlank(message = "password can not be blank")
    public String password;
}
