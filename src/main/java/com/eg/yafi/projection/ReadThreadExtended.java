package com.eg.yafi.projection;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReadThreadExtended {
    public long id;
    public long topicId;
    public String topicName;
    public String content;
    public String username;
    public long likeCount;
    public LocalDateTime createdOn;

    public ReadThreadExtended(long id, long topicId, String topicName, String content, String username, long likeCount,
                              LocalDateTime createdOn) {
        this.id = id;
        this.topicId = topicId;
        this.topicName = topicName;
        this.content = content;
        this.username = username;
        this.likeCount = likeCount;
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadThreadExtended that = (ReadThreadExtended) o;
        return id == that.id &&
                topicId == that.topicId &&
                likeCount == that.likeCount &&
                topicName.equals(that.topicName) &&
                content.equals(that.content) &&
                username.equals(that.username) &&
                createdOn.equals(that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicId, topicName, content, username, likeCount, createdOn);
    }
}
