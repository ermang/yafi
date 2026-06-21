package com.eg.yafi.util;

import com.eg.yafi.req.CreateThreadReq;
import com.eg.yafi.req.CreateTopicReq;
import com.eg.yafi.req.CreateUserReq;
import com.eg.yafi.entity.AppUser;
import com.eg.yafi.entity.Thread;
import com.eg.yafi.entity.Topic;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.TopicRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Dto2Entity {
    private final AppUserRepo appUserRepo;
    private final TopicRepo topicRepo;
    private final ActiveUserResolver activeUserResolver;

    public Dto2Entity(AppUserRepo appUserRepo, TopicRepo topicRepo, ActiveUserResolver activeUserResolver) {
        this.appUserRepo = appUserRepo;
        this.topicRepo = topicRepo;
        this.activeUserResolver = activeUserResolver;
    }

    public Topic createTopic2Topic(CreateTopicReq createTopicReq) {
        Topic t = new Topic();
        t.setName(createTopicReq.name);
        Long userId = activeUserResolver.getActiveUser().getId();
        t.setAppUser(appUserRepo.getOne(userId));

        return t;
    }

    public AppUser createUser2AppUser(CreateUserReq createUserReq) {
        AppUser u = new AppUser();
        u.setUsername(createUserReq.username);
        u.setPassword(createUserReq.password);
        u.setRole(Constant.ROLE_USER);
        u.setEnabled(true);

        return u;
    }

    public Thread createThread2Thread(CreateThreadReq createThreadReq) {
        Thread t  = new Thread();
        t.setContent(createThreadReq.content);
        t.setTopic(topicRepo.getOne(createThreadReq.topicId));
        t.setCreatedOn(LocalDateTime.now());
        Long userId = null;//activeUserResolver.getActiveUser().getUserId();
        t.setAppUser(appUserRepo.getOne(userId));

        return t;
    }
}
