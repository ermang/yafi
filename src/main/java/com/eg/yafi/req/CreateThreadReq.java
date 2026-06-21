package com.eg.yafi.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateThreadReq {
    @NotBlank(message = "content can not be blank")
    public String content;
    @NotNull(message = "topicId can not be null")
    @Positive(message = "topicId must be positive")
    public Long topicId;
}
