package com.eg.yafi.service;

import com.eg.yafi.req.CreateThreadReq;
import com.eg.yafi.req.UpdateThreadReq;
import com.eg.yafi.entity.AppUserThreadLikeRel;
import com.eg.yafi.entity.Thread;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
//import com.eg.yafi.util.ActiveUserResolver;
import com.eg.yafi.util.Constant;
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
    //private final ActiveUserResolver activeUserResolver;

    public ThreadCommandService(Dto2Entity dto2Entity, ThreadRepo threadRepo, AppUserThreadLikeRelRepo appUserThreadLikeRelRepo) {
        this.dto2Entity = dto2Entity;
        this.threadRepo = threadRepo;
        this.appUserThreadLikeRelRepo = appUserThreadLikeRelRepo;
       // this.activeUserResolver = activeUserResolver;
    }

    public void createThread(CreateThreadReq createThreadReq) {
        Thread t = dto2Entity.createThread2Thread(createThreadReq);

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

    public void updateThread(UpdateThreadReq updateThreadReq) {
        Long userId = null;//activeUserResolver.getActiveUser().getUserId();

        Thread t = threadRepo.findById(updateThreadReq.id).orElseThrow(() -> {
            logger.error("Thread with id {} does not exist", updateThreadReq.id);
            return new NoSuchElementException("Thread does not exist");
        });

        if (!t.getAppUser().getId().equals(userId)) {
            logger.error("AppUser with id {} does not own thread with id {}", userId, t.getId());
            throw new UnAuthorizedException(Constant.USER_IS_NOT_AUTHORIZED_FOR_THIS_OPERATION);
        }
        else {
            t.setContent(updateThreadReq.content);
            threadRepo.save(t);
        }

    }

    public void deleteThread(long threadId) {
        Long userId = null;//activeUserResolver.getActiveUser().getUserId();

        Thread t = threadRepo.findById(threadId).orElseThrow(() -> {
            logger.error("Thread with id {} does not exist", threadId);
            return new NoSuchElementException("Thread does not exist");
        });

        if (!t.getAppUser().getId().equals(userId)) {
            logger.error("AppUser with id {} does not own thread with id {}", userId, t.getId());
            throw new UnAuthorizedException(Constant.USER_IS_NOT_AUTHORIZED_FOR_THIS_OPERATION);
        }
        else {
           appUserThreadLikeRelRepo.deleteAllByThreadId(threadId);
            threadRepo.deleteById(threadId);
        }
    }
}
