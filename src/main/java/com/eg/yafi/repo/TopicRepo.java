package com.eg.yafi.repo;

import com.eg.yafi.dto.ReadTopic;
import com.eg.yafi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicRepo extends JpaRepository<Topic, Long> {

    @Query(value = "SELECT new com.eg.yafi.dto.ReadTopic(t.id AS id, t.name AS name, t.appUser.username AS username)" +
                   "    FROM Topic t WHERE t.id = :topicId")
    ReadTopic findTopicByIdRO(@Param("topicId")long topicId);
}
