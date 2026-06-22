package com.eg.yafi.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateThreadReq {
    @NotBlank(message = "request.validation.content.notBlank")
    public String content;
    @NotNull(message = "{request.validation.topicId.notNull}")
    @Positive(message = "{request.validation.topicId.notPositive}")
    public Long topicId;
}
