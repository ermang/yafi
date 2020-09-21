package com.eg.yafi.dto.out;

public class ReadThread {
    public long id;
    public long topicId;
    public String topicName;
    public String content;
    public String username;
    public long likeCount;

    public ReadThread() {
    }

    public ReadThread(long id, long topicId, String topicName, String content, String username, long likeCount) {
        this.id = id;
        this.topicId = topicId;
        this.topicName = topicName;
        this.content = content;
        this.username = username;
        this.likeCount = likeCount;
    }
}
