package com.eg.yafi.controller;

import com.eg.yafi.dto.in.CreateTopic;
import com.eg.yafi.dto.out.ReadPopularTopics;
import com.eg.yafi.dto.out.ReadTopic;
import com.eg.yafi.service.MainService;
import com.eg.yafi.service.TopicQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final MainService mainService;
    private final TopicQueryService topicQueryService;

    public TopicController(MainService mainService, TopicQueryService topicQueryService) {
        this.mainService = mainService;
        this.topicQueryService = topicQueryService;
    }

    @PostMapping()
    public void createTopic(@RequestBody @Valid CreateTopic createTopic){

        mainService.createTopic(createTopic);
    }

    @GetMapping("/{topicId}")
    public ReadTopic readTopic(@PathVariable long topicId){

        ReadTopic rt = mainService.readTopic(topicId);

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
