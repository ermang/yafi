package com.eg.yafi.repo;

import com.eg.yafi.projection.ReadTopic;
import com.eg.yafi.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepo extends JpaRepository<Topic, Long> {

    @Query(value = "SELECT new com.eg.yafi.projection.ReadTopic(t.id AS id, t.name AS name, a.username AS username)" +
                   "    FROM Topic t" +
                   "    INNER JOIN AppUser a ON t.id = :topicId AND t.appUser.id = a.id")
    ReadTopic findTopicByIdRO(@Param("topicId")long topicId);


    @Query(value = "SELECT t.id, t.name, a.username FROM" +
                   "    (SELECT COUNT(topic_id) AS count_topic_id, topic_id FROM yafi.thread" +
                   "        WHERE cast(created_on as date) = '2020-01-03'" +
                   "        GROUP BY topic_id" +
                   "        ORDER BY COUNT(topic_id) DESC )" +
                   "    AS temp" +
                   "    INNER JOIN topic t ON  temp.topic_id = t.id" +
                   "    INNER JOIN app_user a ON t.app_user_id = a.id" +
                   "    ORDER BY temp.count_topic_id DESC", nativeQuery = true)
    List<Object[]> findPopularTopicsRO();

    @Query(value = "SELECT new com.eg.yafi.projection.ReadTopic(t.id AS id, t.name AS name, a.username AS username)" +
                   "    FROM Topic t" +
                   "    INNER JOIN AppUser a ON t.name LIKE %:topicName% AND t.appUser.id = a.id" +
                   "    ORDER BY t.name ASC" )
    Page<ReadTopic> findTopicByNameRO(@Param("topicName")String topicName, Pageable pageable);
}
