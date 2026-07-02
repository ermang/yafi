package com.eg.yafi.projection;

public record ReadThread(
        long id,
        long topicId,
        String topicName,
        String content,
        String username,
        long likeCount
)
{}
