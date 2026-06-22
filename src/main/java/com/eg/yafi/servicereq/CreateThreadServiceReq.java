package com.eg.yafi.servicereq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateThreadServiceReq {
    public String content;
    public  Long topicId;
    public Long userId;
}
