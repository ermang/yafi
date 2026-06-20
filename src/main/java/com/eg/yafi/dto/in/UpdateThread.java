package com.eg.yafi.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateThread {
    @NotNull(message = "id can not be null")
    @Positive(message = "id must be positive")
    public Long id;
    @NotBlank(message = "content can not be blank")
    public String content;
}
