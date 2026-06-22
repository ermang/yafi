package com.eg.yafi.util;

import com.eg.yafi.entity.AppUser;
import com.eg.yafi.entity.Topic;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.servicereq.CreateThreadServiceReq;
import com.eg.yafi.servicereq.CreateTopicServiceReq;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.eg.yafi.entity.Thread;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Component
public class ServiceReq2Entity {

    private final AppUserRepo appUserRepo;
    private final TopicRepo topicRepo;

    public ServiceReq2Entity(AppUserRepo appUserRepo, TopicRepo topicRepo) {
        this.appUserRepo = appUserRepo;
        this.topicRepo = topicRepo;
    }

    public Topic createTopicServiceReq2Topic(CreateTopicServiceReq createTopicServiceReq) {

        Topic t = new Topic();

        t.setName(createTopicServiceReq.name);
        AppUser appUser = appUserRepo.findById(createTopicServiceReq.userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        t.setAppUser(appUser);

        return t;
    }

    public Thread createThreadServiceReq2Thread(CreateThreadServiceReq createThreadServiceReq) {
        AppUser appUser = appUserRepo.findById(createThreadServiceReq.userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Topic topic = topicRepo.findById(createThreadServiceReq.topicId)
                .orElseThrow(() -> new NoSuchElementException("Topic not found"));

        Thread t = new Thread();

        t.setContent(createThreadServiceReq.content);
        t.setAppUser(appUser);
        t.setTopic(topic);
        t.setLikeCount(0L);
        t.setCreatedOn(LocalDateTime.now());

        return t;
    }
}
