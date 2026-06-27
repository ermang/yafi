package com.eg.yafi.service;

import com.eg.yafi.entity.AppUserThreadLikeRel;
import com.eg.yafi.entity.Thread;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.req.UpdateThreadReq;
import com.eg.yafi.servicereq.CreateThreadServiceReq;
import com.eg.yafi.servicereq.LikeThreadServiceReq;
import com.eg.yafi.util.ActiveUserResolver;
import com.eg.yafi.util.Constant;
import com.eg.yafi.util.ServiceReq2Entity;
import com.eg.yafi.util.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;



@Transactional
@Service
public class ThreadCommandService {

    private final TopicRepo topicRepo;
    private final ServiceReq2Entity serviceReq2Entity;
    Logger logger = LoggerFactory.getLogger(ThreadCommandService.class);
    private final AppUserRepo appUserRepo;
    private final ThreadRepo threadRepo;
    private final AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;
    private final ActiveUserResolver activeUserResolver;

    public ThreadCommandService(TopicRepo topicRepo, ServiceReq2Entity serviceReq2Entity, AppUserRepo appUserRepo,
                                ThreadRepo threadRepo, AppUserThreadLikeRelRepo appUserThreadLikeRelRepo,
                                ActiveUserResolver activeUserResolver) {
        this.topicRepo = topicRepo;
        this.serviceReq2Entity = serviceReq2Entity;
        this.appUserRepo = appUserRepo;
        this.threadRepo = threadRepo;
        this.appUserThreadLikeRelRepo = appUserThreadLikeRelRepo;
        this.activeUserResolver = activeUserResolver;
    }

    public void createThread(CreateThreadServiceReq createThreadServiceReq) {

        Thread t = serviceReq2Entity.createThreadServiceReq2Thread(createThreadServiceReq);
        threadRepo.save(t);
    }

    public void likeThread(LikeThreadServiceReq likeThreadServiceReq) {
        Thread t = threadRepo.findById(likeThreadServiceReq.threadId).orElseThrow(
                () -> new NoSuchElementException("service.validation.thread.not.exists"));

        t.setLikeCount(t.getLikeCount() + 1);

        threadRepo.save(t);

        AppUserThreadLikeRel likeRel = new AppUserThreadLikeRel();
        likeRel.setThread(t);
        likeRel.setAppUser(t.getAppUser());

        appUserThreadLikeRelRepo.save(likeRel);
    }

    public void updateThread(UpdateThreadReq updateThreadReq) {
        Long userId = activeUserResolver.getActiveUser().getUserId();

        Thread t = threadRepo.findById(updateThreadReq.id).orElseThrow(
                () -> new NoSuchElementException("service.validation.thread.not.exists"));

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
        Long userId = activeUserResolver.getActiveUser().getUserId();

        Thread t = threadRepo.findById(threadId).orElseThrow(
                () -> new NoSuchElementException("service.validation.thread.not.exists"));

        if (!t.getAppUser().getId().equals(userId)) {
            throw new UnAuthorizedException(Constant.USER_IS_NOT_AUTHORIZED_FOR_THIS_OPERATION);
        }
        else {
           appUserThreadLikeRelRepo.deleteAllByThreadId(threadId);
            threadRepo.deleteById(threadId);
        }
    }
}
