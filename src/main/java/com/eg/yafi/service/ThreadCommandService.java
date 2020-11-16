package com.eg.yafi.service;

import com.eg.yafi.dto.in.CreateThread;
import com.eg.yafi.dto.in.UpdateThread;
import com.eg.yafi.entity.AppUserThreadLikeRel;
import com.eg.yafi.entity.Thread;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.util.ActiveUserResolver;
import com.eg.yafi.util.Dto2Entity;
import com.eg.yafi.util.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@Service
public class ThreadCommandService {
    Logger logger = LoggerFactory.getLogger(ThreadCommandService.class);
    private final Dto2Entity dto2Entity;
    private final ThreadRepo threadRepo;
    private final AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;
    private final ActiveUserResolver activeUserResolver;

    public ThreadCommandService(Dto2Entity dto2Entity, ThreadRepo threadRepo, AppUserThreadLikeRelRepo appUserThreadLikeRelRepo, ActiveUserResolver activeUserResolver) {
        this.dto2Entity = dto2Entity;
        this.threadRepo = threadRepo;
        this.appUserThreadLikeRelRepo = appUserThreadLikeRelRepo;
        this.activeUserResolver = activeUserResolver;
    }

    public void createThread(CreateThread createThread) {
        Thread t = dto2Entity.createThread2Thread(createThread);

        threadRepo.save(t);
    }

    public void likeThread(long threadId) {
        Thread t = threadRepo.findById(threadId).orElseThrow(() -> {
            logger.error("Thread with id {} does not exist", threadId);
            return new NoSuchElementException("Thread does not exist");
        });

        t.setLikeCount(t.getLikeCount() + 1);

        threadRepo.save(t);

        AppUserThreadLikeRel likeRel = new AppUserThreadLikeRel();
        likeRel.setThread(t);
        likeRel.setAppUser(t.getAppUser());

        appUserThreadLikeRelRepo.save(likeRel);
    }

    public void updateThread(UpdateThread updateThread) {
        Long userId = activeUserResolver.getActiveUser().getUserId();

        Thread t = threadRepo.findById(updateThread.id).orElseThrow(() -> {
            logger.error("Thread with id {} does not exist", updateThread.id);
            return new NoSuchElementException("Thread does not exist");
        });

        if (!t.getAppUser().getId().equals(userId)) {
            logger.error("AppUser with id {} does not own thread with id {}", userId, t.getId());
            throw new UnAuthorizedException("User is not authorized for this operation");
        }
        else {
            t.setContent(updateThread.content);
            threadRepo.save(t);
        }

    }

    public void deleteThread(long threadId) {
        Long userId = activeUserResolver.getActiveUser().getUserId();

        Thread t = threadRepo.findById(threadId).orElseThrow(() -> {
            logger.error("Thread with id {} does not exist", threadId);
            return new NoSuchElementException("Thread does not exist");
        });

        if (!t.getAppUser().getId().equals(userId)) {
            logger.error("AppUser with id {} does not own thread with id {}", userId, t.getId());
            throw new UnAuthorizedException("User is not authorized for this operation");
        }
        else {
           appUserThreadLikeRelRepo.deleteAllByThreadId(threadId);
            threadRepo.deleteById(threadId);
        }
    }
}
