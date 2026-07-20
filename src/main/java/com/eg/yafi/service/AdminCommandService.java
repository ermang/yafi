package com.eg.yafi.service;

import com.eg.yafi.entity.Thread;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@Service
public class AdminCommandService {

    private final ThreadRepo threadRepo;
    private final AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;

    public AdminCommandService(ThreadRepo threadRepo, AppUserThreadLikeRelRepo appUserThreadLikeRelRepo) {
        this.threadRepo = threadRepo;
        this.appUserThreadLikeRelRepo = appUserThreadLikeRelRepo;
    }

    public void deleteThread(long threadId) {

        Thread t = threadRepo.findById(threadId).orElseThrow(
                () -> new NoSuchElementException("service.validation.thread.not.exists"));

            appUserThreadLikeRelRepo.deleteAllByThreadId(threadId);
            threadRepo.deleteById(threadId);
    }
}
