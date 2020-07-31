package com.eg.yafi.dto;

import java.util.List;

public class ReadPopularTopics {
    public List<ReadTopic> readTopics;

    public ReadPopularTopics(List<ReadTopic> readTopics) {
        this.readTopics = readTopics;
    }
}
