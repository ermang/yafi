package com.eg.yafi.req;

import jakarta.validation.constraints.NotBlank;

public class LoginReq {
    @NotBlank(message = "{request.validation.username.notBlank}}")
    public String username;
    @NotBlank(message = "{request.validation.password.notBlank}")
    public String password;
}
