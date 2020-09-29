package com.eg.yafi.dto.out;

import java.util.Objects;

public class ReadThread {
    public long id;
    public long topicId;
    public String topicName;
    public String content;
    public String username;
    public long likeCount;

    public ReadThread(long id, long topicId, String topicName, String content, String username, long likeCount) {
        this.id = id;
        this.topicId = topicId;
        this.topicName = topicName;
        this.content = content;
        this.username = username;
        this.likeCount = likeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadThread that = (ReadThread) o;
        return id == that.id &&
                topicId == that.topicId &&
                likeCount == that.likeCount &&
                topicName.equals(that.topicName) &&
                content.equals(that.content) &&
                username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicId, topicName, content, username, likeCount);
    }
}
