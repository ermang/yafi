package com.eg.yafi.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateThread {
    @NotBlank(message = "content can not be blank")
    public String content;
    @NotNull(message = "topicId can not be null")
    @Positive(message = "topicId must be positive")
    public Long topicId;
}
