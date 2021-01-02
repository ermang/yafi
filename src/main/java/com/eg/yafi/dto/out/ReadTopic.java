package com.eg.yafi.dto.out;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadTopic readTopic = (ReadTopic) o;
        return id == readTopic.id &&
                name.equals(readTopic.name) &&
                username.equals(readTopic.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username);
    }
}
