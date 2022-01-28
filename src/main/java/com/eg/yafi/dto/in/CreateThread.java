package com.eg.yafi.dto.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateThread {
    @NotBlank(message = "content can not be blank")
    public String content;
    @NotNull(message = "topicId can not be null")
    @Positive(message = "topicId must be positive")
    public Long topicId;
}
