package com.eg.yafi.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateThreadReq {
    @NotNull(message = "{request.validation.id.notNull}")
    @Positive(message = "{request.validation.id.notPositive}")
    public Long id;
    @NotBlank(message = "{request.validation.content.notBlank}")
    public String content;
}
