package com.eg.yafi.dto.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UpdateThread {
    @NotNull(message = "id can not be null")
    @Positive(message = "id must be positive")
    public Long id;
    @NotBlank(message = "content can not be blank")
    public String content;
}
