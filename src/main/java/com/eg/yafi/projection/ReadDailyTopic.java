package com.eg.yafi.projection;

public class ReadDailyTopic {
    public long id;
    public String name;

    public long threadCount;

    public ReadDailyTopic(long id, String name, long threadCount) {
        this.id = id;
        this.name = name;

        this.threadCount = threadCount;
    }
}
