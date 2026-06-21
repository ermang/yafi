package com.eg.yafi.service;

import com.eg.yafi.req.CreateTopicReq;
import com.eg.yafi.entity.Topic;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.util.Dto2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TopicCommandService {
    private final TopicRepo topicRepo;
    private final Dto2Entity dto2Entity;

    public TopicCommandService(TopicRepo topicRepo, Dto2Entity dto2Entity) {
        this.topicRepo = topicRepo;
        this.dto2Entity = dto2Entity;
    }

    public void createTopic(CreateTopicReq createTopicReq) {
        Topic t = dto2Entity.createTopic2Topic(createTopicReq);

        topicRepo.save(t);
    }
}
