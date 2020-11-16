package com.eg.yafi.repo;

import com.eg.yafi.dto.out.ReadThread;
import com.eg.yafi.dto.out.ReadThreadExtended;
import com.eg.yafi.entity.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThreadRepo extends JpaRepository<Thread, Long> {

    @Query(value = "SELECT new com.eg.yafi.dto.out.ReadThread(t.id AS id, topic.id AS topicId, topic.name AS topicName, t.content AS content," +
                   "    appUser.username AS username, t.likeCount AS likeCount)" +
                   "    FROM Thread t" +
                   "    INNER JOIN Topic topic ON t.id = :threadId AND t.topic.id = topic.id" +
                   "    INNER JOIN AppUser appUser ON t.appUser.id = appUser.id")
    ReadThread findByIdRO(@Param("threadId") long threadId);

    @Query(value = "SELECT new com.eg.yafi.dto.out.ReadThread(t.id AS id, topic.id AS topicId, topic.name AS topicName, t.content AS content," +
                   "    appUser.username AS username, t.likeCount AS likeCount)" +
                   "    FROM Thread t" +
                   "    INNER JOIN AppUser appUser ON t.appUser.id = :userId AND t.appUser.id = appUser.id" +
                   "    INNER JOIN Topic topic ON t.topic.id = topic.id")
    Page<ReadThread> findThreadsByUserRO(@Param("userId") long userId, Pageable pageable);

    @Query(value = "SELECT new com.eg.yafi.dto.out.ReadThreadExtended(t.id AS id, topic.id AS topicId, topic.name AS topicName," +
                   "    t.content AS content, appUser.username AS username, t.likeCount AS likeCount, t.createdOn AS createdOn)" +
                   "    FROM Thread t INNER JOIN Topic topic ON t.topic.id = :topicId AND t.topic.id = topic.id" +
                   "    INNER JOIN AppUser appUser ON t.appUser.id = appUser.id")
    Page<ReadThreadExtended> findThreadsByTopicRO(@Param("topicId")long topicId, Pageable pageable);

    @Query(value = "SELECT new com.eg.yafi.dto.out.ReadThread(t.id AS id, t.topic.id AS topicId, topic.name AS topicName, t.content AS content," +
                   "    appUser.username AS username, t.likeCount AS likeCount)" +
                   "    FROM Thread t" +
                   "    INNER JOIN Topic topic ON t.topic.id = topic.id" +
                   "    INNER JOIN AppUser appUser ON t.appUser.id = appUser.id" +
                   "    ORDER BY t.likeCount DESC")
    Page<ReadThread> findMostLikedThreadsRO(Pageable pageable);

    @Query(value = "SELECT new com.eg.yafi.dto.out.ReadThreadExtended(t.id AS id, topic.id AS topicId, topic.name AS topicName," +
                   "    t.content AS content, appUser.username AS username, t.likeCount AS likeCount, t.createdOn AS createdOn)" +
                   "    FROM Thread t INNER JOIN Topic topic ON t.topic.id = topic.id" +
                   "    INNER JOIN AppUser appUser ON t.appUser.id = appUser.id" +
                   "    ORDER BY t.createdOn DESC")
    Page<ReadThreadExtended> findRecentThreadsRO(Pageable pageable);


}
