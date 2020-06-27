package com.eg.yafi.dto;

public class ReadThread {
    public long id;
    public long topicId;
    public String content;
    public String username;

    public ReadThread() {
    }

    public ReadThread(long id, long topicId, String content, String username) {
        this.id = id;
        this.topicId = topicId;
        this.content = content;
        this.username = username;
    }
}
