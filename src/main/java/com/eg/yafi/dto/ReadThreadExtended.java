package com.eg.yafi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReadThreadExtended {
    public long id;
    public long topicId;
    public String topicName;
    public String content;
    public String username;
    public long likeCount;
    public LocalDateTime createdOn;

    public ReadThreadExtended() {
    }

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
}
