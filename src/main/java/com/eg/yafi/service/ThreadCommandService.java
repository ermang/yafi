package com.eg.yafi.service;

import com.eg.yafi.dto.in.CreateThread;
import com.eg.yafi.entity.AppUserThreadLikeRel;
import com.eg.yafi.entity.Thread;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.util.Dto2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ThreadCommandService {
    private final Dto2Entity dto2Entity;
    private final ThreadRepo threadRepo;
    private final AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;

    public ThreadCommandService(Dto2Entity dto2Entity, ThreadRepo threadRepo, AppUserThreadLikeRelRepo appUserThreadLikeRelRepo) {
        this.dto2Entity = dto2Entity;
        this.threadRepo = threadRepo;
        this.appUserThreadLikeRelRepo = appUserThreadLikeRelRepo;
    }

    public void createThread(CreateThread createThread) {
        Thread t = dto2Entity.createThread2Thread(createThread);

        threadRepo.save(t);
    }

    public void likeThread(long threadId) {
        Thread t = threadRepo.getOne(threadId);
        t.setLikeCount(t.getLikeCount() + 1);

        threadRepo.save(t);

        AppUserThreadLikeRel likeRel = new AppUserThreadLikeRel();
        likeRel.setThread(t);
        likeRel.setAppUser(t.getAppUser());

        appUserThreadLikeRelRepo.save(likeRel);
    }
}
