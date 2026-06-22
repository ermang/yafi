package com.eg.yafi.req;


import jakarta.validation.constraints.NotBlank;

public class CreateTopicReq {
    @NotBlank(message = "{request.validation.name.notBlank}")
    public String name;
}
