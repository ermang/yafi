package com.eg.yafi.repo;

import com.eg.yafi.dto.ReadThread;
import com.eg.yafi.entity.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThreadRepo extends JpaRepository<Thread, Long> {

    @Query(value = "SELECT new com.eg.yafi.dto.ReadThread(t.id AS id, t.topic.id AS topicId, t.content AS content, t.appUser.username AS username)" +
                   "    FROM Thread t" +
                   "    WHERE t.id = :threadId")
    ReadThread findByIdRO(@Param("threadId") long threadId);

    @Query(value = "SELECT new com.eg.yafi.dto.ReadThread(t.id AS id, t.topic.id AS topicId, t.content AS content, t.appUser.username AS username)" +
                   "    FROM Thread t WHERE t.appUser.id = :userId")
    Page<ReadThread> findThreadsByUserRO(@Param("userId") long userId, Pageable pageable);

    //SELECT temp.id, temp.content, temp.like_count, temp.topic_id, app_user.username
    // FROM (SELECT t.id, t.content, t.like_count, t.topic_id, t.app_user_id  FROM thread AS t WHERE t.topic_id = 1) AS temp
    // INNER JOIN app_user ON temp.app_user_id = app_user.id;
    @Query(value = "SELECT new com.eg.yafi.dto.ReadThread(t.id AS id, t.topic.id AS topicId, t.content AS content, t.appUser.username AS username)" +
                   "    FROM Thread t WHERE t.topic.id = :topicId")
    Page<ReadThread> findThreadsByTopicRO(@Param("topicId")long topicId, Pageable pageable);
}
