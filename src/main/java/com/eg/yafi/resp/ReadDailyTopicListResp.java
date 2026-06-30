package com.eg.yafi.resp;

import com.eg.yafi.projection.ReadDailyTopic;

import java.util.List;

public class ReadDailyTopicListResp {

    public final List<ReadDailyTopic> readDailyTopicList;

    public ReadDailyTopicListResp(List<ReadDailyTopic> readDailyTopicList) {
        this.readDailyTopicList = readDailyTopicList;
    }
}
