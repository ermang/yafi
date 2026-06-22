package com.eg.yafi.service;

import com.eg.yafi.entity.Topic;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.servicereq.CreateTopicServiceReq;
import com.eg.yafi.util.ServiceReq2Entity;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TopicCommandService {

    private final AppUserRepo appUserRepo;
    private final TopicRepo topicRepo;
    private final ServiceReq2Entity serviceReq2Entity;

    public TopicCommandService(AppUserRepo appUserRepo, TopicRepo topicRepo, ServiceReq2Entity serviceReq2Entity) {
        this.appUserRepo = appUserRepo;
        this.topicRepo = topicRepo;
        this.serviceReq2Entity = serviceReq2Entity;
    }

    public void createTopic(CreateTopicServiceReq createTopicServiceReq) {

        if (topicRepo.existsByName(createTopicServiceReq.name))
            throw new EntityExistsException("topic already exists!");

        Topic t = serviceReq2Entity.createTopicServiceReq2Topic(createTopicServiceReq);

        topicRepo.save(t);
    }
}
