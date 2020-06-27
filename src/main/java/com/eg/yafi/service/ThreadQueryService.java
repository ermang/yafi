package com.eg.yafi.service;

import com.eg.yafi.dto.ReadThread;
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

    public Page<ReadThread>  readThreadsByTopic(long topicId, Pageable pageable) {
        Page<ReadThread> pagedReadThread = threadRepo.findThreadsByTopicRO(topicId, pageable);

        return pagedReadThread;
    }
}
