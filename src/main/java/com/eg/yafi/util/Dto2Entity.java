package com.eg.yafi.util;

import com.eg.yafi.dto.CreateThread;
import com.eg.yafi.dto.CreateTopic;
import com.eg.yafi.dto.CreateUser;
import com.eg.yafi.entity.AppUser;
import com.eg.yafi.entity.Thread;
import com.eg.yafi.entity.Topic;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.TopicRepo;
import org.springframework.stereotype.Service;

@Service
public class Dto2Entity {
    private final AppUserRepo appUserRepo;
    private final TopicRepo topicRepo;

    public Dto2Entity(AppUserRepo appUserRepo, TopicRepo topicRepo) {
        this.appUserRepo = appUserRepo;
        this.topicRepo = topicRepo;
    }

    public Topic createTopic2Topic(CreateTopic createTopic) {
        Topic t = new Topic();
        t.setName(createTopic.name);
        t.setAppUser(appUserRepo.getOne(createTopic.userId));

        return t;
    }

    public AppUser createUser2AppUser(CreateUser createUser) {
        AppUser u = new AppUser();
        u.setUsername(createUser.username);
        u.setPassword(createUser.password);
        u.setEnabled(true);

        return u;
    }

    public Thread createThread2Thread(CreateThread createThread) {
        Thread t  = new Thread();
        t.setContent(createThread.content);
        t.setTopic(topicRepo.getOne(createThread.topicId));
        t.setAppUser(appUserRepo.getOne(createThread.userId));

        return t;
    }
}