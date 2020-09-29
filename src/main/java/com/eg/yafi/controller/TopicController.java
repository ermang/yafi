package com.eg.yafi.controller;

import com.eg.yafi.dto.in.CreateTopic;
import com.eg.yafi.dto.out.ReadPopularTopics;
import com.eg.yafi.dto.out.ReadTopic;
import com.eg.yafi.service.MainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final MainService mainService;

    public TopicController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping()
    public void createTopic(@RequestBody CreateTopic createTopic){

        mainService.createTopic(createTopic);
    }

    @GetMapping("/{topicId}")
    public ReadTopic readTopic(@PathVariable long topicId){

        ReadTopic rt = mainService.readTopic(topicId);

        return rt;
    }

    @GetMapping()
    public Page<ReadTopic> searchTopicByName(@RequestParam String topicName, Pageable pageable){

        Page<ReadTopic> rt = mainService.searchTopicByName(topicName, pageable);

        return rt;
    }

    @GetMapping("/popular")
    public ReadPopularTopics readPopularTopics() {

        ReadPopularTopics rt = mainService.readPopularTopics();

        return rt;
    }
}
