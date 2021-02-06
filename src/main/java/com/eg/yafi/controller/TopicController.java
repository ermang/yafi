package com.eg.yafi.controller;

import com.eg.yafi.dto.in.CreateTopic;
import com.eg.yafi.projection.ReadPopularTopics;
import com.eg.yafi.projection.ReadTopic;
import com.eg.yafi.service.MainService;
import com.eg.yafi.service.TopicCommandService;
import com.eg.yafi.service.TopicQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicQueryService topicQueryService;
    private final TopicCommandService topicCommandService;

    public TopicController(TopicQueryService topicQueryService, TopicCommandService topicCommandService) {
        this.topicQueryService = topicQueryService;
        this.topicCommandService = topicCommandService;
    }

    @PostMapping()
    public void createTopic(@RequestBody @Valid CreateTopic createTopic){

        topicCommandService.createTopic(createTopic);
    }

    @GetMapping("/{topicId}")
    public ReadTopic readTopic(@PathVariable long topicId){

        ReadTopic rt = topicQueryService.readTopic(topicId);

        return rt;
    }

    @GetMapping()
    public Page<ReadTopic> searchTopicByName(@RequestParam String topicName, Pageable pageable){

        Page<ReadTopic> rt = topicQueryService.searchTopicByName(topicName, pageable);

        return rt;
    }

    @GetMapping("/popular")
    public ReadPopularTopics readPopularTopics() {

        ReadPopularTopics rt = topicQueryService.readPopularTopics();

        return rt;
    }
}
