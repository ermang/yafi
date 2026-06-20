package com.eg.yafi.dto.in;


import jakarta.validation.constraints.NotBlank;

public class CreateTopic {
    @NotBlank(message = "name can not be blank")
    public String name;
}
