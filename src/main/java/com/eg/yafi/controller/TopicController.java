package com.eg.yafi.controller;

import com.eg.yafi.req.CreateTopicReq;
import com.eg.yafi.projection.ReadPopularTopics;
import com.eg.yafi.projection.ReadTopic;
import com.eg.yafi.service.TopicCommandService;
import com.eg.yafi.service.TopicQueryService;
import com.eg.yafi.servicereq.CreateTopicServiceReq;
import com.eg.yafi.util.Req2ServiceReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final Req2ServiceReq req2ServiceReq;
    private final TopicQueryService topicQueryService;
    private final TopicCommandService topicCommandService;

    public TopicController(Req2ServiceReq req2ServiceReq, TopicQueryService topicQueryService, TopicCommandService topicCommandService) {
        this.req2ServiceReq = req2ServiceReq;
        this.topicQueryService = topicQueryService;
        this.topicCommandService = topicCommandService;
    }

    @PostMapping()
    public void createTopic(@RequestBody @Valid CreateTopicReq createTopicReq){

        CreateTopicServiceReq serviceReq = req2ServiceReq.createTopicReq2CreateTopicServiceReq(createTopicReq);
        topicCommandService.createTopic(serviceReq);
    }

    @GetMapping()
    public Page<ReadTopic> searchTopicByName(@RequestParam String topicName, Pageable pageable){

        Page<ReadTopic> rt = topicQueryService.searchTopicByName(topicName, pageable);

        return rt;
    }




}
