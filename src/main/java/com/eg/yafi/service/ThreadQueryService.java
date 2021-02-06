package com.eg.yafi.service;

import com.eg.yafi.projection.ReadThread;
import com.eg.yafi.projection.ReadThreadExtended;
import com.eg.yafi.repo.ThreadRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ThreadQueryService {
    private final ThreadRepo threadRepo;

    public ThreadQueryService(ThreadRepo threadRepo) {
        this.threadRepo = threadRepo;
    }

    public ReadThread readThread(long threadId) {
        ReadThread rt = threadRepo.findByIdRO(threadId);

        return rt;
    }

    public Page<ReadThread>  readThreadsByUser(long userId, Pageable pageable) {
        Page<ReadThread> pagedReadThread = threadRepo.findThreadsByUserRO(userId, pageable);

        return pagedReadThread;
    }

    public Page<ReadThreadExtended>  readThreadsByTopic(long topicId, Pageable pageable) {
        Page<ReadThreadExtended> pagedReadThread = threadRepo.findThreadsByTopicRO(topicId, pageable);

        return pagedReadThread;
    }

    public Page<ReadThread>  readMostLikedThreads(Pageable pageable) {
        Page<ReadThread> pagedReadThread = threadRepo.findMostLikedThreadsRO(pageable);

        return pagedReadThread;
    }

    public Page<ReadThreadExtended> readRecentThreads(Pageable pageable) {
        Page<ReadThreadExtended> pagedReadThread = threadRepo.findRecentThreadsRO(pageable);

        return pagedReadThread;
    }
}
