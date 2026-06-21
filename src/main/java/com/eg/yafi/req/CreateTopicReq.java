package com.eg.yafi.req;


import jakarta.validation.constraints.NotBlank;

public class CreateTopicReq {
    @NotBlank(message = "name can not be blank")
    public String name;
}
