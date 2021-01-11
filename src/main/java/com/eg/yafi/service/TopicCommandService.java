package com.eg.yafi.service;

import com.eg.yafi.dto.in.CreateTopic;
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

    public void createTopic(CreateTopic createTopic) {
        Topic t = dto2Entity.createTopic2Topic(createTopic);

        topicRepo.save(t);
    }
}
