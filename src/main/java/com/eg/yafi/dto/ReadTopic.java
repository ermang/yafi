package com.eg.yafi.dto;

public class ReadTopic {
    public long id;
    public String name;
    public String username;

    public ReadTopic(){}

    public ReadTopic(long id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }
}
