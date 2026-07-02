package com.eg.yafi.resp;

import com.eg.yafi.projection.ReadHotTopic;

import java.util.List;

public class ReadHotTopicListResp {

    public final List<ReadHotTopic> readHotTopicList;

    public ReadHotTopicListResp(List<ReadHotTopic> readHotTopicList) {
        this.readHotTopicList = readHotTopicList;
    }
}
