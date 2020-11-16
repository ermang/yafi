package com.eg.yafi.dto.in;

import javax.validation.constraints.NotBlank;

public class CreateTopic {
    @NotBlank(message = "name can not be blank")
    public String name;
}
